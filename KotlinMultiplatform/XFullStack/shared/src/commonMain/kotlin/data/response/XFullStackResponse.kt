package data.response

import kotlinx.serialization.Serializable
import utils.ResponseStatus

@Serializable
data class XFullStackResponse<T>(
    val status: ResponseStatus,
    val errorCode: String?,
    val message: String,
    val data: T?,
)