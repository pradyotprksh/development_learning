package app.pages.auth.register.state

data class RegisterState(
    val useEmailOrPhoneState: Boolean = true,
    val isUsingPhoneNumber: Boolean = true,
    val nameValue: String = "",
    val phoneEmailValue: String = "",
    val dobValue: String = "",
    val dobValueLong: Long = 0,
    val datePickerVisible: Boolean = true,
) {
    val enableNextButton =
        nameValue.isNotBlank() && phoneEmailValue.isNotBlank() && dobValue.isNotBlank()
}
