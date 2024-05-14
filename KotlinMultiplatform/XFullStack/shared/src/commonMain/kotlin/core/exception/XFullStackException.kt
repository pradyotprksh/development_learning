package core.exception

sealed class XFullStackException(
    message: String?
) : Throwable(message = message)

data class InvalidParameter(override val message: String) : XFullStackException(message = message)