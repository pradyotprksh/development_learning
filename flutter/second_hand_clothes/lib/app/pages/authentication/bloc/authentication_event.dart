import 'package:equatable/equatable.dart';

/// An authentication event for the authentication screen which will be used
/// to trigger an event and update the states if required.
abstract class AuthenticationEvent extends Equatable {
  const AuthenticationEvent();

  @override
  List<Object?> get props => [];
}

/// A child of [AuthenticationEvent] which will be called whenever there is
/// a change in email text field.
class EmailAddressChangeEvent extends AuthenticationEvent {
  const EmailAddressChangeEvent(this.emailAddress);

  final String emailAddress;

  @override
  List<Object?> get props => [emailAddress];
}

/// A child of [AuthenticationEvent] which will be called whenever there
/// is a change in password text field.
class PasswordChangeEvent extends AuthenticationEvent {
  const PasswordChangeEvent(this.password);

  final String password;

  @override
  List<Object?> get props => [password];
}
