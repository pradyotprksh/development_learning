import 'package:equatable/equatable.dart';

abstract class AuthenticationEvent extends Equatable {
  const AuthenticationEvent();

  @override
  List<Object?> get props => [];
}

class CheckForAuthenticationStatus extends AuthenticationEvent {
  const CheckForAuthenticationStatus();
}
