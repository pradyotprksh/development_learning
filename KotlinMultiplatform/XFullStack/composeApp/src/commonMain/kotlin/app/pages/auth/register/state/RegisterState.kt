package app.pages.auth.register.state

data class RegisterState(
    val useEmailOrPhoneState: Boolean = true,
    val isUsingPhoneNumber: Boolean = true,
    val nameValue: String = "",
    val phoneEmailValue: String = "",
    val dobValue: String = "",
    val dobValueLong: Long = 0,
    val datePickerVisible: Boolean = true,
    val isNameValid: Boolean = false,
    val isPhoneEmailValid: Boolean = false,
    val showPhoneNumberError: Boolean = false,
) {
    val enableNextButton =
        nameValue.isNotBlank() && isNameValid && phoneEmailValue.isNotBlank() && isPhoneEmailValid && dobValue.isNotBlank()
}
