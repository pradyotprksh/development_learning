package domain.repositories.file

import core.models.response.ClientResponse
import core.models.response.XFullStackResponse
import domain.services.file.FileRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import utils.Constants.ErrorCode.DEFAULT_ERROR_CODE
import utils.Localization
import utils.XFullStackResponseStatus

class FileRepositoryImplementation(
    private val fileRemoteService: FileRemoteService,
) : FileRepository {
    override suspend fun uploadFile(
        bucket: String,
        name: String, extension: String,
        byteArray: ByteArray,
    ): Flow<ClientResponse<out XFullStackResponse<Pair<Long, String>>>> = channelFlow {
        try {
            val response = fileRemoteService.uploadFile(
                bucket = bucket,
                name = name,
                extension = extension,
                byteArray = byteArray,
                uploadStatus = { bytesSentTotal, contentLength ->
                    send(
                        ClientResponse.Success(
                            XFullStackResponse(
                                status = XFullStackResponseStatus.Success,
                                message = null,
                                code = null,
                                data = Pair(bytesSentTotal / contentLength, ""),
                            )
                        )
                    )
                },
            )
            if (response.status == XFullStackResponseStatus.Success) {
                send(
                    ClientResponse.Success(
                        XFullStackResponse(
                            status = response.status,
                            message = response.message,
                            code = response.code,
                            data = Pair(100, response.data ?: ""),
                        )
                    )
                )
            } else {
                send(
                    ClientResponse.Error(
                        message = response.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                        errorCode = response.code ?: DEFAULT_ERROR_CODE,
                    ),
                )
            }
        } catch (e: Exception) {
            send(
                ClientResponse.Error(
                    message = e.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                    errorCode = DEFAULT_ERROR_CODE,
                ),
            )
        }
    }
}