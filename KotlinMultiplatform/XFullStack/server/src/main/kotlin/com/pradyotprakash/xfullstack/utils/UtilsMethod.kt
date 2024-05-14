package com.pradyotprakash.xfullstack.utils

import com.pradyotprakash.xfullstack.core.exception.InvalidParameter
import com.pradyotprakash.xfullstack.utils.Constants.ConstValues.USERNAME_LENGTH

object UtilsMethod {
    private fun maxUsernameLengthValid(username: String) = username.length > USERNAME_LENGTH

    private fun validUsername(username: String) = username.matches(Regex("[^\\n]{5,}"))

    fun isValidUserName(username: String) {
        if (!maxUsernameLengthValid(username)) {
            throw InvalidParameter(message = "")
        }
    }
}