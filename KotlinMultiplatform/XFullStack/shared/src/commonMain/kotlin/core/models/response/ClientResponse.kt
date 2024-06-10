package core.models.response

sealed class ClientResponse<R> {
    data class Success<T>(val data: T) : ClientResponse<T>()

    data class Error(
        val errorCode: String,
        val message: String,
    ) : ClientResponse<Nothing>()

    data object Loading : ClientResponse<Nothing>()

    data object Idle : ClientResponse<Nothing>()
}