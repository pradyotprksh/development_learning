import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A password form model for validation the inputs or string, so that we know
/// a valid password was got before its been used.
class PasswordModels
    extends FormzInput<String, PasswordValidationError> {
  const PasswordModels.pure() : super.dirty('');

  const PasswordModels.dirty([super.value = '']) : super.dirty();

  @override
  PasswordValidationError? validator(String? value) {
    if (value != null && value.isNotEmpty) {
      if (value.isPasswordTooWeak()) {
        return PasswordValidationError.tooWeak;
      }
    }
    return null;
  }
}
