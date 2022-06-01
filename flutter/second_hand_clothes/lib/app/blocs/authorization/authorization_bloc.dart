import 'dart:async';

import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:second_hand_clothes/app/app.dart';
import 'package:second_hand_clothes/domain/domain.dart';

/// A bloc class for splash, which will listen to all the events related
/// to splash and update the state of the splash journey.
class AuthorizationBloc extends Bloc<AuthorizationEvent, AuthorizationState> {
  AuthorizationBloc(this._authService, this._firebaseDB)
      : super(const AuthorizationState()) {
    on<CheckAuthorizationEvent>(_checkAuthorizationEvent);
    on<FoundProfileDetailsAuthorizationEvent>(
      _foundProfileDetailsAuthorizationEvent,
    );

    _firebaseDB.getCurrentUserDetails().listen(
      (userDetails) {
        add(FoundProfileDetailsAuthorizationEvent(userDetails));
      },
    );
  }

  final ServicesFirebaseAuth _authService;
  final ServicesFirebaseDB _firebaseDB;

  /// Whenever [CheckAuthorizationEvent] is sent this method will be called.
  void _checkAuthorizationEvent(
    CheckAuthorizationEvent event,
    Emitter<AuthorizationState> emit,
  ) async {
    await Future.delayed(
      const Duration(seconds: 5),
      () {
        if (_authService.isUserLoggedIn()) {
          emit(
            state.copyWith(
              authenticationStatus: UtilsAuthenticationStatus.authenticated,
            ),
          );
        } else {
          emit(
            state.copyWith(
              authenticationStatus: UtilsAuthenticationStatus.unauthenticated,
            ),
          );
        }
      },
    );
  }

  @override
  Future<void> close() {
    _authService.dispose();
    _firebaseDB.dispose();
    return super.close();
  }

  // TODO: Instead of getting user details from AuthorizationBloc use the UserBloc, whose lifecycle starts from home screen
  /// Whenever [FoundProfileDetailsAuthorizationEvent] is sent this method will be called.
  void _foundProfileDetailsAuthorizationEvent(
    FoundProfileDetailsAuthorizationEvent event,
    Emitter<AuthorizationState> emit,
  ) {
    emit(
      state.copyWith(
        userDetails: event.userDetails,
        noUserDetails: event.userDetails != null,
      ),
    );
  }
}
