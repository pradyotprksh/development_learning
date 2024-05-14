package core.exception

import core.utils.Localization

sealed class XFullStackException(
    message: String
) : Throwable(message = message)

data class InvalidParameter(override val message: String) : XFullStackException(message = message)

data object UnauthorizedAccess : XFullStackException(message = Localization.UNAUTHORIZED_ACCESS)

data object UserDetailsNotFound : XFullStackException(message = Localization.USER_DETAILS_NOT_FOUND)

data object UserAuthDetailsError :
    XFullStackException(message = Localization.USER_AUTH_DETAILS_ERROR)