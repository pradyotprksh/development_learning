package core.exception

data class PokeApiException(
    val code: Int? = null,
    override val message: String = "",
) : Exception(message)