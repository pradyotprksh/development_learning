package core.utils

import core.exception.InvalidParameter
import core.utils.Constants.ConstValues.USERNAME_LENGTH

object UtilsMethod {
    private fun maxUsernameLengthValid(username: String) = username.length > USERNAME_LENGTH

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
}