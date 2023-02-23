import 'dart:async';

import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/domain/domain.dart';

class UserBloc extends Bloc<UserEvent, UserState> {
  UserBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
    this._firebaseStorageService,
    this._deviceDetails,
  ) : super(const FetchingUserDetails()) {
    on<FetchUserDetails>(_fetchUserDetailsEvent);
    on<SaveAvatar>(_saveAvatar);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;
  final FirebaseStorageService _firebaseStorageService;
  final DeviceDetails _deviceDetails;

  Future<void> _fetchUserDetailsEvent(
    FetchUserDetails event,
    Emitter<UserState> emit,
  ) async {
    emit(const FetchingUserDetails());
    var userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      final storageReference =
          await _firebaseFirestoreService.deleteStatusOnTimeCompletion(userId);
      if (storageReference.isNotEmpty) {
        await _firebaseStorageService.deleteFiles(storageReference);
      }
      await emit.forEach(
        _firebaseFirestoreService.getUserDetails(userId),
        onData: (userDetails) {
          if (userDetails != null && userDetails.allDetailsAvailable) {
            _firebaseAuthService.updateUserDetails(userDetails);
            return UserDetailsAvailable(userDetails);
          } else {
            return const UserDataNotAvailable();
          }
        },
      );
    }
  }

  void _saveAvatar(
    SaveAvatar event,
    Emitter<UserState> emit,
  ) async {
    var userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      final deviceDetails = await _deviceDetails.getDeviceDetails();
      await _firebaseFirestoreService.updateUserDetails(
        userId,
        {
          FirestoreItemKey.avatarDetails: event.avatarDetails,
          FirestoreItemKey.userDeviceDetails: deviceDetails.toMap(),
        },
      );
    }
  }
}
