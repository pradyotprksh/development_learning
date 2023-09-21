package core.request

data class PokeApiRequestDetails(
    val baseUrl: String = "http://0.0.0.0:8080",
    val endpoint: String,
    val queries: Map<String, Any> = emptyMap(),
    val fullPath: String? = null,
) {
    val path: String
        get() = fullPath ?: "$baseUrl/$endpoint"
}
