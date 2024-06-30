package core.network

import core.models.request.XFullStackClientRequestDetails
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.onUpload
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.forms.FormPart
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.flow
import utils.Constants.ErrorCode.DEFAULT_ERROR_CODE
import utils.Constants.Keys.FILE
import utils.Constants.Keys.FILE_UPLOAD_BOUNDARY
import utils.Constants.Keys.UPLOAD_BUCKET
import utils.Constants.Paths.Websockets.WEBSOCKETS
import utils.Logger
import utils.LoggerLevel

class NetworkClient(
    val httpClient: HttpClient,
) {
    suspend inline fun <reified T> get(details: XFullStackClientRequestDetails): Result<T> {
        return try {
            val data = httpClient.get(details.endpoint) {
                details.queries.forEach {
                    parameter(it.key, it.value)
                }
            }
            val result = data.body<T>()
            return Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend inline fun <reified T> post(details: XFullStackClientRequestDetails): Result<T> {
        return try {
            val data = httpClient.post(details.endpoint) {
                details.queries.forEach {
                    parameter(it.key, it.value)
                }
                details.body?.let {
                    setBody(it)
                }
            }
            val result = data.body<T>()
            return Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun websocket() = flow {
        try {
            httpClient.webSocket(WEBSOCKETS) {
                try {
                    while (true) {
                        val incomingMessage = (incoming.receive() as? Frame.Text)?.readText()
                        emit(incomingMessage)
                    }
                } catch (e: Exception) {
                    Logger.log(LoggerLevel.Error, e.message ?: "")
                    emit(DEFAULT_ERROR_CODE)
                }
            }
        } catch (e: Exception) {
            emit(DEFAULT_ERROR_CODE)
        }
    }

    suspend inline fun <reified T> uploadFile(
        bucket: String,
        name: String, extension: String,
        byteArray: ByteArray,
        details: XFullStackClientRequestDetails,
        crossinline uploadStatus: suspend (Long, Long) -> Unit,
    ): Result<T> {
        return try {
            val data = httpClient.post(details.endpoint) {
                setBody(
                    MultiPartFormDataContent(
                        formData {
                            append(FormPart(UPLOAD_BUCKET, bucket))
                            append(
                                FILE, byteArray,
                                Headers.build {
                                    append(HttpHeaders.ContentType, "*/$extension")
                                    append(
                                        HttpHeaders.ContentDisposition,
                                        "filename=\"$name\""
                                    )
                                },
                            )
                        }, boundary = FILE_UPLOAD_BOUNDARY
                    ),
                )
                onUpload { bytesSentTotal, contentLength ->
                    uploadStatus(bytesSentTotal, contentLength)
                }
            }
            val result = data.body<T>()
            return Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}