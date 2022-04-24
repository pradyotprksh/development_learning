import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A email form model for validation the inputs or string, so that we know
/// a valid email was got before its been used.
class EmailAddressModels
    extends FormzInput<String, EmailAddressValidationError> {
  const EmailAddressModels.pure() : super.dirty('');

  const EmailAddressModels.dirty([String value = '']) : super.dirty(value);

  @override
  EmailAddressValidationError? validator(String? value) {
    if (value != null) {
      if (value.isEmpty) {
        return EmailAddressValidationError.empty;
      }
      if (!value.isValidEmail()) {
        return EmailAddressValidationError.invalid;
      }
    }
    return null;
  }
}
