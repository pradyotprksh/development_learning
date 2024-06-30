package domain.repositories.file

import core.models.response.ClientResponse
import core.models.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow

interface FileRepository {
    suspend fun uploadFile(
        bucket: String,
        name: String, extension: String,
        byteArray: ByteArray,
    ): Flow<ClientResponse<out XFullStackResponse<Pair<Long, String>>>>
}