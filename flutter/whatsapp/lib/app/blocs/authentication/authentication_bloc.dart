import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class AuthenticationBloc
    extends Bloc<AuthenticationEvent, AuthenticationState> {
  AuthenticationBloc(
    this._firebaseAuthService,
  ) : super(const AuthenticationState()) {
    on<CheckForRemoteConfigs>(_checkForRemoteConfigs);
    on<CheckForAuthenticationStatus>(_checkForAuthenticationStatus);
    on<UserAuthenticatedEvent>(_userAuthenticatedEvent);
  }

  final FirebaseAuthService _firebaseAuthService;

  void _checkForAuthenticationStatus(
    CheckForAuthenticationStatus event,
    Emitter<AuthenticationState> emit,
  ) {
    if (_firebaseAuthService.isUserLoggedIn()) {
      emit(
        AuthenticationState(
          authenticationState: AuthenticationStatus.authenticated,
          firebaseUserDetails: _firebaseAuthService.getUserDetails(),
        ),
      );
    } else {
      emit(
        const AuthenticationState(
          authenticationState: AuthenticationStatus.unauthenticated,
        ),
      );
    }
  }

  void _userAuthenticatedEvent(
    UserAuthenticatedEvent event,
    Emitter<AuthenticationState> emit,
  ) {
    emit(
      AuthenticationState(
        authenticationState: AuthenticationStatus.authenticated,
        firebaseUserDetails: event.firebaseUserDetails,
      ),
    );
  }

  void _checkForRemoteConfigs(
    CheckForRemoteConfigs event,
    Emitter<AuthenticationState> emit,
  ) {
    if (FirebaseRemoteConfigService.isApplicationDown()) {
      emit(
        state.copyWith(
          authenticationState: AuthenticationStatus.applicationDown,
        ),
      );
    } else {
      add(const CheckForAuthenticationStatus());
    }
  }
}
