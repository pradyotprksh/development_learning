import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class AuthenticationBloc
    extends Bloc<AuthenticationEvent, AuthenticationState> {
  AuthenticationBloc() : super(const AuthenticationState()) {
    on<CheckForAuthenticationStatus>(_checkForAuthenticationStatus);
  }

  void _checkForAuthenticationStatus(
    CheckForAuthenticationStatus event,
    Emitter<AuthenticationState> emit,
  ) {
    emit(
      const AuthenticationState(
        authenticationState: AuthenticationStatus.unauthenticated,
      ),
    );
  }
}
