package app.pages.auth.register.state

import utils.Constants.ConstValues.OTP_LENGTH

data class PasswordValidation(
    val length: Boolean = false,
    val uppercase: Boolean = false,
    val lowercase: Boolean = false,
    val digit: Boolean = false,
    val specialCharacter: Boolean = false,
) {
    val isValid: Boolean
        get() = length && uppercase && lowercase && digit && specialCharacter
}

data class RegisterState(
    val useEmailOrPhoneState: Boolean = true,
    val isUsingPhoneNumber: Boolean = true,
    val nameValue: String = "",
    val phoneEmailValue: String = "",
    val dobValue: String = "",
    val dobValueTimestamp: Long = 0,
    val datePickerVisible: Boolean = true,
    val isNameValid: Boolean = false,
    val isPhoneEmailValid: Boolean = false,
    val showPhoneNumberError: Boolean = false,
    val showLoading: Boolean = false,
    val errorMessage: String? = null,
    val showOtpOption: Boolean = false,
    val otpValue: String = "",
    val showPasswordOption: Boolean = false,
    val passwordValue: String = "",
    val confirmPasswordValue: String = "",
    val showConfirmPassword: Boolean = false,
    val passwordValidation: PasswordValidation = PasswordValidation()
) {
    val enableNextButton: Boolean
        get() = if (showOtpOption) otpValue.isNotBlank() && otpValue.length == OTP_LENGTH
        else if (showPasswordOption) passwordValue.isNotBlank() && confirmPasswordValue.isNotBlank() && passwordValue == confirmPasswordValue && passwordValidation.isValid
        else nameValue.isNotBlank() && isNameValid && phoneEmailValue.isNotBlank() && isPhoneEmailValid && dobValue.isNotBlank()
}
