import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart';
import 'package:second_hand_clothes/domain/domain.dart';

/// A bloc class for authentication screen which will listen to any event
/// related to authentication and update the state of the authentication
/// screen.
class AuthenticationBloc
    extends Bloc<AuthenticationEvent, AuthenticationState> {
  AuthenticationBloc(this._firebaseAuth) : super(const AuthenticationState()) {
    on<EmailAddressChangeAuthenticateEvent>(_onEmailAddressChangeEvent);
    on<PasswordChangeAuthenticateEvent>(_onPasswordChangeEvent);
    on<SubmitFormAuthenticateEvent>(_onSubmitFormEvent);
  }

  final ServicesFirebaseAuth _firebaseAuth;

  /// Whenever [EmailAddressChangeAuthenticateEvent] is triggered then this
  /// method will be called
  void _onEmailAddressChangeEvent(
    EmailAddressChangeAuthenticateEvent event,
    Emitter<AuthenticationState> emit,
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

  /// Whenever [PasswordChangeAuthenticateEvent] is triggered then this method
  /// will be called
  void _onPasswordChangeEvent(
    PasswordChangeAuthenticateEvent event,
    Emitter<AuthenticationState> emit,
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

  /// Whenever [SubmitFormAuthenticateEvent] is triggered then this method will
  /// be called
  void _onSubmitFormEvent(
    SubmitFormAuthenticateEvent event,
    Emitter<AuthenticationState> emit,
  ) async {
    try {
      await _firebaseAuth.authenticateUser(
        email: state.emailAddress.value,
        password: state.password.value,
        authType: AuthType.login,
      );
    } catch(e) {
      UtilsLogger().log('error', e);
    }
  }
}
