import 'package:equatable/equatable.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:whatsapp/app/app.dart';

abstract class AuthenticationEvent extends Equatable {
  const AuthenticationEvent();

  @override
  List<Object?> get props => [];
}

class CheckForRemoteConfigs extends AuthenticationEvent {
  const CheckForRemoteConfigs();
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

class UnAuthenticateUserEvent extends AuthenticationEvent {
  const UnAuthenticateUserEvent();
}

class ListenToAuthStateChange extends AuthenticationEvent {
  const ListenToAuthStateChange();
}

class CheckForBiometric extends AuthenticationEvent {
  const CheckForBiometric();
}

class EnableBiometric extends AuthenticationEvent {
  const EnableBiometric(this.message);

  final String message;
}

class DisableBiometric extends AuthenticationEvent {
  const DisableBiometric();
}

class ChangeLocalAuthTime extends AuthenticationEvent {
  const ChangeLocalAuthTime(this.authTime);

  final LocalAuthTime authTime;
}

class ToggleMessageVisibilityInNotification extends AuthenticationEvent {
  const ToggleMessageVisibilityInNotification();
}
