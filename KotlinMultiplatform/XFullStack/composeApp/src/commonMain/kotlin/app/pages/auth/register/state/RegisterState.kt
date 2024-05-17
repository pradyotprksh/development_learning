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
    val passwordValidation: PasswordValidation = PasswordValidation(),
    val profileImage: String? = null,
    val username: String = "",
    val isUsernameValid: Boolean = false,
    val showUsernameProfileImage: Boolean = false,
) {
    val userDetailsForm: Boolean
        get() = !showOtpOption && !showPasswordOption && !showUsernameProfileImage

    val otpForm: Boolean
        get() = showOtpOption && !showPasswordOption && !showUsernameProfileImage

    val passwordForm: Boolean
        get() = !showOtpOption && showPasswordOption && !showUsernameProfileImage

    val usernameProfileImageForm: Boolean
        get() = !showOtpOption && !showPasswordOption && showUsernameProfileImage

    val passwordValid: Boolean
        get() = passwordValue.isNotBlank() && passwordValidation.isValid

    val confirmPasswordValid: Boolean
        get() = confirmPasswordValue.isNotBlank() && confirmPasswordValue == passwordValue

    val enableNextButton: Boolean
        get() = if (otpForm) otpValue.isNotBlank() && otpValue.length == OTP_LENGTH
        else if (passwordForm) passwordValid && confirmPasswordValid
        else if (usernameProfileImageForm) isUsernameValid
        else nameValue.isNotBlank() && isNameValid && phoneEmailValue.isNotBlank() && isPhoneEmailValid && dobValue.isNotBlank()
}
