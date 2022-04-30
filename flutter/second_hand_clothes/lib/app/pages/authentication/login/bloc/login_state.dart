import 'package:equatable/equatable.dart';
import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A login state of the login form, like we will be checking
/// the form details and when the submission is done and also the
/// result of the submission.
///
/// All these details will be save to this state and accordingly the UI will
/// be updated.
class LoginState extends Equatable {
  const LoginState({
    this.formStatus = FormzStatus.pure,
    this.errorMessage,
    this.emailAddress = const EmailAddressModels.pure(),
    this.password = const PasswordModels.pure(),
  });

  final FormzStatus formStatus;
  final EmailAddressModels emailAddress;
  final PasswordModels password;
  final String? errorMessage;

  LoginState copyWith({
    FormzStatus? formStatus,
    EmailAddressModels? emailAddress,
    PasswordModels? password,
    String? errorMessage,
  }) =>
      LoginState(
        formStatus: formStatus ?? this.formStatus,
        emailAddress: emailAddress ?? this.emailAddress,
        password: password ?? this.password,
        errorMessage: errorMessage ?? this.errorMessage,
      );

  @override
  List<Object?> get props => [formStatus, emailAddress, password];
}
