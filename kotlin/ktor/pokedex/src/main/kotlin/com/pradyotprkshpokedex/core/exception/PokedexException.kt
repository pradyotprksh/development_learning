package com.pradyotprkshpokedex.core.exception

sealed class PokedexException : Throwable()

class ParametersInvalidException(private val invalidParameters: List<String>) : PokedexException() {
    override val message: String
        get() = "Please check all the parameters. Issue found with parameters: [${invalidParameters.joinToString(",")}]"
}

class PokeApiException(private val error: String): PokedexException() {
    override val message: String
        get() = error
}