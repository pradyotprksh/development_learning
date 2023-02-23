import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/domain/domain.dart';

class ProfileBloc extends Bloc<ProfileEvent, ProfileState> {
  ProfileBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
    this._firebaseStorageService,
    this._deviceDetails,
  ) : super(const ProfileState()) {
    on<UpdateUserProfileImage>(_updateUserProfileImage);
    on<EnableDisableUserNameForm>(_enableUsernameEditOperation);
    on<UpdateUserName>(_updateUsernameImage);
    on<UpdateCurrentMood>(_updateUserMood);
    on<UseAvatarAsProfileImage>(_useAvatarAsProfileImage);
    on<DoNotUseAvatarAsProfileImage>(_doNotUseAvatarAsProfileImage);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;
  final FirebaseStorageService _firebaseStorageService;
  final DeviceDetails _deviceDetails;

  void _updateUsernameImage(
    UpdateUserName event,
    Emitter<ProfileState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      final deviceDetails = await _deviceDetails.getDeviceDetails();
      await _firebaseFirestoreService.updateUserDetails(
        userId,
        {
          FirestoreItemKey.name: event.username,
          FirestoreItemKey.userDeviceDetails: deviceDetails.toMap(),
        },
      );
      add(
        const EnableDisableUserNameForm(),
      );
    }
  }

  void _enableUsernameEditOperation(
    EnableDisableUserNameForm event,
    Emitter<ProfileState> emit,
  ) {
    emit(state.copyWith(enableUsernameEdit: !state.enableUsernameEdit));
  }

  void _updateUserProfileImage(
    UpdateUserProfileImage event,
    Emitter<ProfileState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      emit(
        state.copyWith(
          imageUploadStatus: ImageUploadStatus.uploading,
        ),
      );

      try {
        final firestorePath = CoreConstants.userProfileImage.replaceAll(
          CoreConstants.userIdPlaceholder,
          userId,
        );
        final deviceDetails = await _deviceDetails.getDeviceDetails();
        final imageUrl = await _firebaseStorageService.uploadFile(
          event.path,
          firestorePath,
          {
            FirestoreItemKey.userId: userId,
            ...deviceDetails.toStringMap(),
          },
        );
        await _firebaseFirestoreService.updateUserDetails(
          userId,
          {
            FirestoreItemKey.profileImage: imageUrl,
            FirestoreItemKey.firestoreFilePath: firestorePath,
            FirestoreItemKey.useAvatarAsProfile: false,
            FirestoreItemKey.userDeviceDetails: deviceDetails.toMap(),
          },
        );

        emit(
          state.copyWith(
            imageUploadStatus: ImageUploadStatus.uploaded,
          ),
        );
      } catch (e) {
        FirebaseUtils.recordFlutterError(e);
        emit(
          state.copyWith(
            imageUploadStatus: ImageUploadStatus.error,
          ),
        );
      }
    }
  }

  void _updateUserMood(
    UpdateCurrentMood event,
    Emitter<ProfileState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      final deviceDetails = await _deviceDetails.getDeviceDetails();
      await _firebaseFirestoreService.updateUserDetails(
        userId,
        {
          FirestoreItemKey.currentMood: event.mood,
          FirestoreItemKey.userDeviceDetails: deviceDetails.toMap(),
        },
      );
      add(
        const EnableDisableUserNameForm(),
      );
    }
  }

  void _useAvatarAsProfileImage(
    UseAvatarAsProfileImage event,
    Emitter<ProfileState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      final deviceDetails = await _deviceDetails.getDeviceDetails();
      await _firebaseFirestoreService.updateUserDetails(
        userId,
        {
          FirestoreItemKey.useAvatarAsProfile: true,
          FirestoreItemKey.userDeviceDetails: deviceDetails.toMap(),
        },
      );
    }
  }

  void _doNotUseAvatarAsProfileImage(
    DoNotUseAvatarAsProfileImage event,
    Emitter<ProfileState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      final deviceDetails = await _deviceDetails.getDeviceDetails();
      await _firebaseFirestoreService.updateUserDetails(
        userId,
        {
          FirestoreItemKey.useAvatarAsProfile: false,
          FirestoreItemKey.userDeviceDetails: deviceDetails.toMap(),
        },
      );
    }
  }
}
