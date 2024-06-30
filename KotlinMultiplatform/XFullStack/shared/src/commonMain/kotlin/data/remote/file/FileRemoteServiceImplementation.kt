package data.remote.file

import core.models.request.XFullStackClientRequestDetails
import core.models.response.XFullStackResponse
import core.network.NetworkClient
import domain.services.file.FileRemoteService
import utils.Constants.Keys.FILE
import utils.Constants.Paths.File.UPLOAD

class FileRemoteServiceImplementation(
    private val networkClient: NetworkClient,
) : FileRemoteService {
    override suspend fun uploadFile(
        bucket: String,
        name: String, extension: String,
        byteArray: ByteArray,
        uploadStatus: suspend (Long, Long) -> Unit,
    ): XFullStackResponse<String> {
        val response = networkClient.uploadFile<XFullStackResponse<String>>(
            bucket = bucket,
            name = name,
            extension = extension,
            byteArray = byteArray,
            details = XFullStackClientRequestDetails(
                endpoint = "$FILE$UPLOAD"
            ),
            uploadStatus = uploadStatus,
        )

        return response.getOrThrow()
    }
}