package core.exception

import core.utils.Constants.ErrorCode.DB_WRITE_ERROR_CODE
import core.utils.Constants.ErrorCode.UNAUTHORIZED_ERROR_CODE
import core.utils.Constants.ErrorCode.USER_AUTH_DETAILS_ERROR_CODE
import core.utils.Constants.ErrorCode.USER_DETAILS_NOT_FOUND_CODE
import core.utils.Localization

sealed class XFullStackException(
    message: String
) : Throwable(message = message)

data class InvalidParameter(override val message: String, val errorCode: String) :
    XFullStackException(message = message)

data class UnauthorizedAccess(val errorCode: String = UNAUTHORIZED_ERROR_CODE) :
    XFullStackException(message = Localization.UNAUTHORIZED_ACCESS)

data class UserDetailsNotFound(val errorCode: String = USER_DETAILS_NOT_FOUND_CODE) :
    XFullStackException(message = Localization.USER_DETAILS_NOT_FOUND)

data class UserAuthDetailsError(val errorCode: String = USER_AUTH_DETAILS_ERROR_CODE) :
    XFullStackException(message = Localization.USER_AUTH_DETAILS_ERROR)

data class DBWriteError(val errorCode: String = DB_WRITE_ERROR_CODE) :
    XFullStackException(message = Localization.DB_WRITE_ERROR)