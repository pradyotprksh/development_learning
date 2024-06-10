package core.network

import di.SharedModulesDi
import domain.repositories.user.current.CurrentUserRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.kodein.di.instance
import org.mongodb.kbson.BsonObjectId
import utils.Constants.ConstValues.APPLICATION_JSON
import utils.Constants.ConstValues.BASE_URL
import utils.Constants.ConstValues.BEARER
import utils.Constants.ConstValues.TIMEOUT_VALUE
import utils.Constants.Keys.AUTHORIZATION
import utils.Constants.Keys.CONTENT_TYPE
import utils.Constants.Keys.REQUEST_IDENTIFIER
import utils.Constants.Keys.REQUEST_TIMESTAMP
import utils.Constants.Keys.USER_ID_HEADER
import utils.UtilsMethod

object XFullStackHttpClient {
    private val currentUserRepository: CurrentUserRepository by SharedModulesDi.di.instance()

    fun createHttpClient() = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging)
        install(DefaultRequest)
        install(WebSockets)
        install(HttpTimeout) {
            requestTimeoutMillis = TIMEOUT_VALUE
            connectTimeoutMillis = TIMEOUT_VALUE
            socketTimeoutMillis = TIMEOUT_VALUE
        }

        defaultRequest {
            url("$BASE_URL/")

            header(CONTENT_TYPE, APPLICATION_JSON)
            header(REQUEST_IDENTIFIER, BsonObjectId().toHexString())
            header(REQUEST_TIMESTAMP, UtilsMethod.Dates.getCurrentTimeStamp())

            currentUserRepository.getUserId()?.let { userId ->
                header(USER_ID_HEADER, userId)
                currentUserRepository.getToken(userId)?.let { token ->
                    header(AUTHORIZATION, "$BEARER $token")
                }
            }
        }
    }
}