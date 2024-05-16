package app.pages.auth.register.state

data class RegisterState(
    val useEmailOrPhoneState: Boolean = true,
    val isUsingPhoneNumber: Boolean = true,
)
