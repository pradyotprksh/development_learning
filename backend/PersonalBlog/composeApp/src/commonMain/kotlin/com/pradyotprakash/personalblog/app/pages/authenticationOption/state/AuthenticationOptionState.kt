package com.pradyotprakash.personalblog.app.pages.authenticationOption.state

data class AuthenticationOptionState(
    val isAdmin: Boolean = false,
    val username: String = "",
    val password: String = "",
    val isAdminError: Boolean = false,
) {
    val isFieldsValid: Boolean
        get() = isAdmin && username.isNotBlank() && password.isNotBlank()
}
