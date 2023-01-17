import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';
import 'package:whatsapp/domain/domain.dart';

class UserDetailsBloc extends Bloc<UserDetailsEvent, UserDetailsState> {
  UserDetailsBloc(
    this._firebaseAuthService,
    this._firebaseStorageService,
    this._firebaseFirestoreService,
    this._deviceDetails,
  ) : super(const UserDetailsState()) {
    on<FetchFirebaseUserDetails>(_fetchUserDetailsFromFirebase);
    on<UserDetailsFormEvent>(_userDetailsFormEvent);
    on<UploadProfileImage>(_uploadProfileImage);
  }

  final FirebaseAuthService _firebaseAuthService;
  final FirebaseStorageService _firebaseStorageService;
  final FirebaseFirestoreService _firebaseFirestoreService;
  final DeviceDetails _deviceDetails;

  void _fetchUserDetailsFromFirebase(
    FetchFirebaseUserDetails event,
    Emitter<UserDetailsState> emit,
  ) {
    final userDetails = _firebaseAuthService.getUserDetails();
    if (userDetails != null) {
      final isEmailAddressAvailable =
          userDetails.email != null && userDetails.email != '';
      final isPhoneNumberAvailable =
          userDetails.phoneNumber != null && userDetails.phoneNumber != '';

      emit(
        UserDetailsState(
          username: state.username,
          emailAddress: userDetails.email ?? state.emailAddress,
          phoneNumber: userDetails.phoneNumber ?? state.phoneNumber,
          isEmailAddressAvailable: isEmailAddressAvailable,
          isPhoneNumberAvailable: isPhoneNumberAvailable,
        ),
      );
    }
  }

  void _userDetailsFormEvent(
    UserDetailsFormEvent event,
    Emitter<UserDetailsState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null && event.pin != null) {
      emit(
        state.copyWith(
          pageState: PageState.loading,
        ),
      );

      try {
        final deviceDetails = await _deviceDetails.getDeviceDetails();
        await _firebaseFirestoreService.setUserDetails(
          userId,
          UserDetails(
            name: event.userName,
            emailId: event.emailAddress,
            phoneNumber: event.phoneNumber,
            profileImage: event.profilePic,
            pin: event.pin!,
            userId: userId,
            allDetailsAvailable: true,
            userDeviceDetails: deviceDetails,
            createdOnTimeStamp: UtilsMethods.getCurrentTimeStamp(),
            updatedOnTimeStamp: UtilsMethods.getCurrentTimeStamp(),
          ),
        );

        emit(
          state.copyWith(
            pageState: PageState.success,
          ),
        );
      } on Exception catch (e) {
        UtilsLogger.debugLog(e);

        emit(
          state.copyWith(
            pageState: PageState.error,
          ),
        );
      }
    }
  }

  void _uploadProfileImage(
    UploadProfileImage event,
    Emitter<UserDetailsState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      emit(
        state.copyWith(
          imageUploadStatus: ImageUploadStatus.uploading,
        ),
      );

      try {
        final imageUrl = await _firebaseStorageService.uploadImage(
          event.imagePath,
          CoreConstants.userProfileImage.replaceAll(
            CoreConstants.userIdPlaceholder,
            userId,
          ),
        );

        emit(
          state.copyWith(
            imageUploadStatus: ImageUploadStatus.uploaded,
            profilePicImage: imageUrl,
          ),
        );
      } on Exception catch (e) {
        UtilsLogger.debugLog(e);

        emit(
          state.copyWith(
            imageUploadStatus: ImageUploadStatus.error,
          ),
        );
      }
    }
  }
}
