import 'package:equatable/equatable.dart';
import 'package:firebase_auth/firebase_auth.dart';

abstract class AuthenticationEvent extends Equatable {
  const AuthenticationEvent();

  @override
  List<Object?> get props => [];
}

class CheckForAuthenticationStatus extends AuthenticationEvent {
  const CheckForAuthenticationStatus();
}

class UserAuthenticatedEvent extends AuthenticationEvent {
  const UserAuthenticatedEvent({
    this.firebaseUserDetails,
  });

  final User? firebaseUserDetails;

  @override
  List<Object?> get props => [firebaseUserDetails];
}
