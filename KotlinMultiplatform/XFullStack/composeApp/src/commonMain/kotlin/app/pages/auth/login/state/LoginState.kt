package app.pages.auth.login.state

import utils.PasswordValidation

data class LoginState(
    val usernamePhoneEmailValue: String = "",
    val passwordValidation: PasswordValidation = PasswordValidation(),
    val passwordValue: String = "",
    val isUsernamePhoneEmailValid: Boolean = false,
    val showLoading: Boolean = false,
    val snackBarMessage: String? = null,
    val isUsernameValue: Boolean = false,
    val isPhoneNumberValue: Boolean = false,
    val isEmailValue: Boolean = false,
) {
    val passwordValid: Boolean
        get() = passwordValue.isNotBlank() && passwordValidation.isValid

    val showUsernamePhoneEmailError: Boolean
        get() = !isUsernamePhoneEmailValid && usernamePhoneEmailValue.isNotBlank()
}
