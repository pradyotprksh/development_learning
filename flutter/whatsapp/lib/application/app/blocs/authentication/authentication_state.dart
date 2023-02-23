import 'package:equatable/equatable.dart';
import 'package:firebase_auth/firebase_auth.dart';

enum AuthenticationStatus {
  unknown,
  authenticated,
  unauthenticated,
  applicationDown,
}

class AuthenticationState extends Equatable {
  const AuthenticationState({
    this.authenticationState = AuthenticationStatus.unauthenticated,
    this.firebaseUserDetails,
  });

  final AuthenticationStatus authenticationState;
  final User? firebaseUserDetails;

  AuthenticationState copyWith({
    AuthenticationStatus? authenticationState,
    User? firebaseUserDetails,
  }) =>
      AuthenticationState(
        authenticationState: authenticationState ?? this.authenticationState,
        firebaseUserDetails: firebaseUserDetails ?? this.firebaseUserDetails,
      );

  @override
  List<Object?> get props => [
        authenticationState,
        firebaseUserDetails,
      ];
}
