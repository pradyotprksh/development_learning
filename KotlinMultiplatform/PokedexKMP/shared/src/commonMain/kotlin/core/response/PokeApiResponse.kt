package core.response

import core.exception.PokeApiException

sealed interface PokeApiResponse<R> {

    data class Success<T>(val data: T) : PokeApiResponse<T>

    data class Error(val exception: PokeApiException) : PokeApiResponse<Nothing>

    data object Loading : PokeApiResponse<Nothing>

    data object Idle : PokeApiResponse<Nothing>
}

