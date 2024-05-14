package core.utils

import core.exception.InvalidParameter
import core.utils.Constants.ConstValues.PASSWORD_LENGTH
import core.utils.Constants.ConstValues.USERNAME_LENGTH

object UtilsMethod {
    private fun maxUsernameLengthValid(username: String) = username.length >= USERNAME_LENGTH

    private fun validUsername(username: String) = username.matches(Regex("[^\\n]{5,}"))

    fun isValidUserName(username: String): Boolean {
        if (!maxUsernameLengthValid(username)) {
            throw InvalidParameter(message = Localization.USERNAME_LENGTH_ERROR)
        }

        if (!validUsername(username)) {
            throw InvalidParameter(message = Localization.INVALID_USERNAME)
        }

        return true
    }

    private fun maxPasswordLengthValid(password: String) = password.length >= PASSWORD_LENGTH

    private fun passwordContainsAtLeastOneUpperCase(password: String) =
        password.contains(Regex("[A-Z]"))

    private fun passwordContainsAtLeastOneLowerCase(password: String) =
        password.contains(Regex("[a-z]"))

    private fun passwordContainsAtLeastOneDigit(password: String) =
        password.contains(Regex("[0-9]"))

    private fun passwordContainsAtLeastOneSpecialCharacters(password: String) =
        password.contains(Regex("[^A-Za-z0-9]"))

    fun isValidPassword(password: String): Boolean {
        if (!maxPasswordLengthValid(password)) {
            throw InvalidParameter(message = Localization.PASSWORD_LENGTH_ERROR)
        }

        if (!passwordContainsAtLeastOneUpperCase(password)) {
            throw InvalidParameter(message = Localization.NO_UPPERCASE_ERROR)
        }

        if (!passwordContainsAtLeastOneLowerCase(password)) {
            throw InvalidParameter(message = Localization.NO_LOWERCASE_ERROR)
        }

        if (!passwordContainsAtLeastOneDigit(password)) {
            throw InvalidParameter(message = Localization.NO_DIGIT_ERROR)
        }

        if (!passwordContainsAtLeastOneSpecialCharacters(password)) {
            throw InvalidParameter(message = Localization.NO_SPECIAL_CHARACTER_ERROR)
        }

        return true
    }

    fun isValidEmail(email: String): Boolean {
        if (!email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"))) {
            throw InvalidParameter(message = Localization.INVALID_EMAIL)
        }

        return true
    }

    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        if (phoneNumber.matches(Regex("^(\\+\\d{1,3}[- ]?)?\\d{10}$"))) {
            throw InvalidParameter(message = Localization.INVALID_PHONE_NUMBER)
        }

        return true
    }

    fun isValidLink(link: String): Boolean {
        if (link.matches(Regex("^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?$"))) {
            throw InvalidParameter(message = Localization.INVALID_LINK)
        }

        return true
    }

    fun isValidDate(date: String): Boolean {
        return true
    }
}