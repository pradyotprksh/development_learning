import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:second_hand_clothes/app/app.dart';
import 'package:second_hand_clothes/domain/domain.dart';

// TODO: Do all the authorization related work here, like sign in, register, etc.

/// A bloc class for splash, which will listen to all the events related
/// to splash and update the state of the splash journey.
class AuthorizationBloc extends Bloc<AuthorizationEvent, AuthorizationState> {
  AuthorizationBloc(this._firebaseAuth) : super(const AuthorizationState.unknown()) {
    on<CheckAuthorizationEvent>(_checkAuthorizationEvent);
  }

  final ServicesFirebaseAuth _firebaseAuth;

  /// Whenever [CheckAuthorizationEvent] is sent this method will be called.
  void _checkAuthorizationEvent(
    CheckAuthorizationEvent event,
    Emitter<AuthorizationState> emit,
  ) async {
    await Future.delayed(
      const Duration(seconds: 5),
      () {
        if (_firebaseAuth.isUserLoggedIn()) {
          emit(const AuthorizationState.authenticated());
        } else {
          emit(const AuthorizationState.unauthenticated());
        }
      },
    );
  }
}
