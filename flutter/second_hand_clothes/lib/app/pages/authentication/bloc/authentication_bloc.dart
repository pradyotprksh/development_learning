import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A bloc class for authentication screen which will listen to any event
/// related to authentication and update the state of the authentication
/// screen.
class AuthenticationBloc
    extends Bloc<AuthenticationEvent, AuthenticationState> {
  AuthenticationBloc() : super(const AuthenticationState()) {
    on<EmailAddressChangeEvent>(_onEmailAddressChangeEvent);
    on<PasswordChangeEvent>(_onPasswordChangeEvent);
  }

  /// Whenever [EmailAddressChangeEvent] is triggered then this method will
  /// be called
  void _onEmailAddressChangeEvent(
    EmailAddressChangeEvent event,
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

  /// Whenever [PasswordChangeEvent] is triggered then this method will
  /// be called
  void _onPasswordChangeEvent(
    PasswordChangeEvent event,
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
}
