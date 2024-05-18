package utils

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