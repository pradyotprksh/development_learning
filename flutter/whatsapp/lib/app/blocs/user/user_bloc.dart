import 'dart:async';

import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';

class UserBloc extends Bloc<UserEvent, UserState> {
  UserBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
  ) : super(const FetchingUserDetails()) {
    on<FetchUserDetails>(_fetchUserDetailsEvent);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;

  Future<void> _fetchUserDetailsEvent(
    FetchUserDetails event,
    Emitter<UserState> emit,
  ) async {
    emit(const FetchingUserDetails());
    var userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      await _firebaseFirestoreService.deleteStatusOnTimeCompletion(userId);
      await emit.forEach(
        _firebaseFirestoreService.getUserDetails(userId),
        onData: (userDetails) {
          if (userDetails != null && userDetails.allDetailsAvailable) {
            _firebaseAuthService.updateUserDetails(userDetails);
            if (DeviceUtilsMethods.getTimeDifferenceInDays(
                    userDetails.lastPinConfirmationTimeStamp) >
                FirebaseRemoteConfigService.pinConfirmationTimeValue()) {
              return AskForPinConfirmation(userDetails);
            } else {
              return UserDetailsAvailable(userDetails);
            }
          } else {
            return const UserDataNotAvailable();
          }
        },
      );
    }
  }
}
