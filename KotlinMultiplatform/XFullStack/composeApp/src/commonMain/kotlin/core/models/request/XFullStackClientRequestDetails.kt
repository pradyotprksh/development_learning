package core.models.request

data class XFullStackClientRequestDetails(
    val endpoint: String,
    val queries: Map<String, Any> = emptyMap(),
    val body: Any? = null,
)
