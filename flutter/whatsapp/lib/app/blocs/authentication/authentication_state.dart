import 'package:equatable/equatable.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:whatsapp/app/app.dart';

class AuthenticationState extends Equatable {
  const AuthenticationState({
    this.authenticationState = AuthenticationStatus.unauthenticated,
    this.firebaseUserDetails,
  });

  final AuthenticationStatus authenticationState;
  final User? firebaseUserDetails;

  @override
  List<Object?> get props => [
        authenticationState,
        firebaseUserDetails,
      ];
}
