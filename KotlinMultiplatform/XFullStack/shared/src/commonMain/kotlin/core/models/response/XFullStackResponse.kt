package core.models.response

import kotlinx.serialization.Serializable
import utils.XFullStackResponseStatus

@Serializable
data class XFullStackResponse<T>(
    val status: XFullStackResponseStatus,
    val code: String?,
    val message: String?,
    val data: T?,
)