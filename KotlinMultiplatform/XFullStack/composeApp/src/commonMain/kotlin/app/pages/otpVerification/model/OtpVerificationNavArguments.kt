package app.pages.otpVerification.model

data class OtpVerificationNavArguments(
    val emailPhone: String = "",
    val name: String = "",
    val dob: String = "",
    val dobTimeStamp: Long = 0
)
