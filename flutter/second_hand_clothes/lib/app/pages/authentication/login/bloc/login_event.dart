import 'package:equatable/equatable.dart';

/// An authentication event for the authentication screen which will be used
/// to trigger an event and update the states if required.
abstract class LoginEvent extends Equatable {
  const LoginEvent();

  @override
  List<Object?> get props => [];
}

/// A child of [LoginEvent] which will be called whenever there is
/// a change in email text field.
class EmailAddressChangeLoginEvent extends LoginEvent {
  const EmailAddressChangeLoginEvent(this.emailAddress);

  final String emailAddress;

  @override
  List<Object?> get props => [emailAddress];
}

/// A child of [LoginEvent] which will be called whenever there
/// is a change in password text field.
class PasswordChangeLoginEvent extends LoginEvent {
  const PasswordChangeLoginEvent(this.password);

  final String password;

  @override
  List<Object?> get props => [password];
}

/// A child of [LoginEvent] which will be called whenever the
/// authentication form is submitted.
class SubmitFormLoginEvent extends LoginEvent {
  const SubmitFormLoginEvent();
}
