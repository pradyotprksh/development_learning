import 'package:equatable/equatable.dart';
import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A authentication state of the authentication form, like we will be checking
/// the form details and when the submission is done and also the
/// result of the submission.
///
/// All these details will be save to this state and accordingly the UI will
/// be updated.
class AuthenticationState extends Equatable {
  const AuthenticationState({
    this.formStatus = FormzStatus.pure,
    this.emailAddress = const EmailAddressModels.pure(),
    this.password = const PasswordModels.pure(),
  });

  final FormzStatus formStatus;
  final EmailAddressModels emailAddress;
  final PasswordModels password;

  AuthenticationState copyWith({
    FormzStatus? formStatus,
    EmailAddressModels? emailAddress,
    PasswordModels? password,
  }) =>
      AuthenticationState(
        formStatus: formStatus ?? this.formStatus,
        emailAddress: emailAddress ?? this.emailAddress,
        password: password ?? this.password,
      );

  @override
  List<Object?> get props => [formStatus, emailAddress, password];
}
