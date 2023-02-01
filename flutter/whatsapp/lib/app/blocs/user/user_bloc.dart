import 'dart:async';

import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

class UserBloc extends Bloc<UserEvent, UserState> {
  UserBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
    this._firebaseStorageService,
  ) : super(const FetchingUserDetails()) {
    on<FetchUserDetails>(_fetchUserDetailsEvent);
    on<UpdateUserProfileImage>(_updateUserProfileImage);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;
  final FirebaseStorageService _firebaseStorageService;

  Future<void> _fetchUserDetailsEvent(
    FetchUserDetails event,
    Emitter<UserState> emit,
  ) async {
    emit(const FetchingUserDetails());
    var userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      await emit.forEach(
        _firebaseFirestoreService.getUserDetails(userId).stream,
        onData: (userDetails) {
          if (userDetails != null && userDetails.allDetailsAvailable) {
            return UserDetailsAvailable(userDetails);
          } else {
            return const UserDataNotAvailable();
          }
        },
      );
    }
  }

  void _updateUserProfileImage(
    UpdateUserProfileImage event,
    Emitter<UserState> emit,
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
        final imageUrl = await _firebaseStorageService.uploadFile(
          event.path,
          firestorePath,
        );

        await _firebaseFirestoreService.updateUserDetails(
          userId,
          {
            FirestoreItemKey.profileImage: imageUrl,
            FirestoreItemKey.firestoreFilePath: firestorePath,
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
}
