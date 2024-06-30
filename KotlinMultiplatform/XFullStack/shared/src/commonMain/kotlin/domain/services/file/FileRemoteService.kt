package domain.services.file

import core.models.response.XFullStackResponse

interface FileRemoteService {
    suspend fun uploadFile(
        bucket: String,
        name: String, extension: String,
        byteArray: ByteArray,
        uploadStatus: suspend (Long, Long) -> Unit,
    ): XFullStackResponse<String>
}