package utils

import core.exception.InvalidParameter
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import utils.Constants.ConstValues.BIO_MAX_LENGTH
import utils.Constants.ConstValues.NAME_MAX_LENGTH
import utils.Constants.ConstValues.NAME_MIN_LENGTH
import utils.Constants.ConstValues.PASSWORD_MIN_LENGTH
import utils.Constants.ConstValues.USERNAME_MIN_LENGTH
import utils.Constants.ErrorCode.BIO_VALIDITY_ERROR_CODE
import utils.Constants.ErrorCode.EMAIL_VALIDITY_ERROR_CODE
import utils.Constants.ErrorCode.NAME_VALIDITY_ERROR_CODE
import utils.Constants.ErrorCode.PASSWORD_VALIDITY_ERROR_CODE
import utils.Constants.ErrorCode.PHONE_NUMBER_VALIDITY_ERROR_CODE
import utils.Constants.ErrorCode.USERNAME_VALIDITY_ERROR_CODE
import kotlin.math.absoluteValue

object UtilsMethod {
    object Validation {
        fun isValidBio(bio: String): Boolean {
            if (bio.length !in 0..BIO_MAX_LENGTH) {
                throw InvalidParameter(
                    message = Localization.BIO_MAX_LENGTH_ERROR, errorCode = BIO_VALIDITY_ERROR_CODE
                )
            }

            return true
        }

        private fun maxNameLengthValid(username: String) =
            username.length in NAME_MIN_LENGTH..NAME_MAX_LENGTH

        fun isValidName(name: String): Boolean {
            if (!maxNameLengthValid(name)) {
                throw InvalidParameter(
                    message = Localization.NAME_LENGTH_ERROR, errorCode = NAME_VALIDITY_ERROR_CODE
                )
            }

            return true
        }

        private fun maxUsernameLengthValid(username: String) =
            username.length >= USERNAME_MIN_LENGTH

        private fun validUsername(username: String) = username.matches(Regex("[^\\n]{5,}"))

        fun isValidUserName(username: String): Boolean {
            if (!maxUsernameLengthValid(username)) {
                throw InvalidParameter(
                    message = Localization.USERNAME_LENGTH_ERROR,
                    errorCode = USERNAME_VALIDITY_ERROR_CODE
                )
            }

            if (!validUsername(username)) {
                throw InvalidParameter(
                    message = Localization.INVALID_USERNAME,
                    errorCode = USERNAME_VALIDITY_ERROR_CODE
                )
            }

            return true
        }

        fun minPasswordLengthValid(password: String) = password.length >= PASSWORD_MIN_LENGTH

        fun passwordContainsAtLeastOneUpperCase(password: String) =
            password.contains(Regex("[A-Z]"))

        fun passwordContainsAtLeastOneLowerCase(password: String) =
            password.contains(Regex("[a-z]"))

        fun passwordContainsAtLeastOneDigit(password: String) =
            password.contains(Regex("[0-9]"))

        fun passwordContainsAtLeastOneSpecialCharacters(password: String) =
            password.contains(Regex("[^A-Za-z0-9]"))

        fun isValidPassword(password: String): Boolean {
            if (!minPasswordLengthValid(password)) {
                throw InvalidParameter(
                    message = Localization.PASSWORD_LENGTH_ERROR,
                    errorCode = PASSWORD_VALIDITY_ERROR_CODE
                )
            }

            if (!passwordContainsAtLeastOneUpperCase(password)) {
                throw InvalidParameter(
                    message = Localization.NO_UPPERCASE_ERROR,
                    errorCode = PASSWORD_VALIDITY_ERROR_CODE
                )
            }

            if (!passwordContainsAtLeastOneLowerCase(password)) {
                throw InvalidParameter(
                    message = Localization.NO_LOWERCASE_ERROR,
                    errorCode = PASSWORD_VALIDITY_ERROR_CODE
                )
            }

            if (!passwordContainsAtLeastOneDigit(password)) {
                throw InvalidParameter(
                    message = Localization.NO_DIGIT_ERROR, errorCode = PASSWORD_VALIDITY_ERROR_CODE
                )
            }

            if (!passwordContainsAtLeastOneSpecialCharacters(password)) {
                throw InvalidParameter(
                    message = Localization.NO_SPECIAL_CHARACTER_ERROR,
                    errorCode = PASSWORD_VALIDITY_ERROR_CODE
                )
            }

            return true
        }

        fun isValidEmail(email: String): Boolean {
            if (!email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"))) {
                throw InvalidParameter(
                    message = Localization.INVALID_EMAIL, errorCode = EMAIL_VALIDITY_ERROR_CODE
                )
            }

            return true
        }

        fun isValidPhoneNumber(phoneNumber: String): Boolean {
            if (!phoneNumber.matches(Regex("^(\\+\\d{1,3}[- ]?)?\\d{10}$"))) {
                throw InvalidParameter(
                    message = Localization.INVALID_PHONE_NUMBER,
                    errorCode = PHONE_NUMBER_VALIDITY_ERROR_CODE
                )
            }

            return true
        }

        fun isValidLink(link: String, errorCode: String): Boolean {
            if (!link.matches(Regex("^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?$"))) {
                throw InvalidParameter(message = Localization.INVALID_LINK, errorCode = errorCode)
            }

            return true
        }

        fun isValidDate(date: Long): Boolean {
            return Dates.convertLongToReadableDate(date).isNotBlank()
        }
    }

    object Conversion {
        fun getIntegerValue(value: String, length: Int): String {
            val absHashCode = value.hashCode().absoluteValue
            val numberStr = absHashCode.toString()
            val numDigits = numberStr.length
            if (numDigits < length) {
                val padding = "0".repeat(length - numDigits)
                return padding + numberStr
            } else {
                return numberStr.substring(0, 6)
            }
        }
    }

    object Dates {
        fun convertLongToReadableDate(date: Long): String {
            val localDate = Instant.fromEpochMilliseconds(date)
                .toLocalDateTime(TimeZone.currentSystemDefault()).date
            val monthName = localDate.month.name.lowercase().replaceFirstChar { it.uppercase() }
            return "${localDate.dayOfMonth} $monthName ${localDate.year}"
        }

        fun getCurrentTimeStamp(): Long = Clock.System.now().epochSeconds
    }
}