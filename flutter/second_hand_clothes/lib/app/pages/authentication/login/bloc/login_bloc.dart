import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart';
import 'package:second_hand_clothes/domain/domain.dart';

/// A bloc class for login screen which will listen to any event
/// related to login and update the state of the login
/// screen.
class LoginBloc
    extends Bloc<LoginEvent, LoginState> {
  LoginBloc(this._firebaseAuth) : super(const LoginState()) {
    on<EmailAddressChangeLoginEvent>(_onEmailAddressChangeEvent);
    on<PasswordChangeLoginEvent>(_onPasswordChangeEvent);
    on<SubmitFormLoginEvent>(_onSubmitFormEvent);
  }

  final ServicesFirebaseAuth _firebaseAuth;

  /// Whenever [EmailAddressChangeLoginEvent] is triggered then this
  /// method will be called
  void _onEmailAddressChangeEvent(
    EmailAddressChangeLoginEvent event,
    Emitter<LoginState> emit,
  ) {
    final emailAddress = EmailAddressModels.dirty(event.emailAddress);
    emit(
      state.copyWith(
        emailAddress: emailAddress,
        formStatus: Formz.validate(
          [
            state.password,
            emailAddress,
          ],
        ),
      ),
    );
  }

  /// Whenever [PasswordChangeLoginEvent] is triggered then this method
  /// will be called
  void _onPasswordChangeEvent(
    PasswordChangeLoginEvent event,
    Emitter<LoginState> emit,
  ) {
    final password = PasswordModels.dirty(event.password);
    emit(
      state.copyWith(
        password: password,
        formStatus: Formz.validate(
          [
            password,
            state.emailAddress,
          ],
        ),
      ),
    );
  }

  /// Whenever [SubmitFormLoginEvent] is triggered then this method will
  /// be called
  void _onSubmitFormEvent(
    SubmitFormLoginEvent event,
    Emitter<LoginState> emit,
  ) async {
    try {
      emit(state.copyWith(formStatus: FormzStatus.submissionInProgress));
      await _firebaseAuth.authenticateUser(
        email: state.emailAddress.value,
        password: state.password.value,
        authType: AuthType.login,
      );
      emit(state.copyWith(formStatus: FormzStatus.submissionSuccess));
    } catch (e) {
      UtilsLogger().log('error', e);
      if (e is DomainException) {
        emit(
          state.copyWith(
            formStatus: FormzStatus.submissionFailure,
            errorMessage: e.message,
          ),
        );
      } else {
        emit(state.copyWith(formStatus: FormzStatus.submissionFailure));
      }
    }
  }
}
