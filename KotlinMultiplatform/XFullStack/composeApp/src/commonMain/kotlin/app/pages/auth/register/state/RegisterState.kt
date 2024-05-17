package app.pages.auth.register.state

import utils.Constants.ConstValues.OTP_LENGTH

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
) {
    val enableNextButton = if (showOtpOption) otpValue.isNotBlank() && otpValue.length == OTP_LENGTH
    else nameValue.isNotBlank() && isNameValid && phoneEmailValue.isNotBlank() && isPhoneEmailValid && dobValue.isNotBlank()
}
