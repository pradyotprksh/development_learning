package utils

enum class Tags(
    val key: String,
    val value: String,
) {
    Terms(
        key = "terms",
        value = ""
    ),
    PrivacyPolicy(
        key = "privacy_policy",
        value = ""
    ),
    CookieUse(
        key = "cookie_use",
        value = ""
    ),
    LogIn(
        key = "log_in",
        value = ""
    ),
}