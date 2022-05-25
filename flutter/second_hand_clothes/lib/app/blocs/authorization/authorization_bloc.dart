import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:second_hand_clothes/app/app.dart';

// TODO: Do all the authorization related work here, like sign in, register, etc.

/// A bloc class for splash, which will listen to all the events related
/// to splash and update the state of the splash journey.
class AuthorizationBloc extends Bloc<AuthorizationEvent, AuthorizationState> {
  AuthorizationBloc() : super(const AuthorizationState.unknown()) {
    on<CheckAuthorizationEvent>(_checkAuthorizationEvent);
  }

  /// Whenever [CheckAuthorizationEvent] is sent this method will be called.
  void _checkAuthorizationEvent(
    CheckAuthorizationEvent event,
    Emitter<AuthorizationState> emit,
  ) async {
    await Future.delayed(
      const Duration(seconds: 5),
      () {
        emit(const AuthorizationState.unauthenticated());
      },
    );
  }
}
