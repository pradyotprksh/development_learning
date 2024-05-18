package core.network

import di.ModulesDi
import domain.repositories.user.current.CurrentUserRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.kodein.di.instance
import org.mongodb.kbson.BsonObjectId
import utils.Constants.ConstValues.APPLICATION_JSON
import utils.Constants.ConstValues.BASE_URL
import utils.Constants.ConstValues.BEARER
import utils.Constants.Keys.AUTHORIZATION
import utils.Constants.Keys.CONTENT_TYPE
import utils.Constants.Keys.REQUEST_IDENTIFIER

object XFullStackHttpClient {
    private val currentUserRepository: CurrentUserRepository by ModulesDi.di.instance()

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

        defaultRequest {
            url("$BASE_URL/")

            header(CONTENT_TYPE, APPLICATION_JSON)
            header(REQUEST_IDENTIFIER, BsonObjectId().toHexString())

            currentUserRepository.getUserId()?.let { userId ->
                currentUserRepository.getToken(userId)?.let { token ->
                    header(AUTHORIZATION, "$BEARER $token")
                }
            }
        }
    }
}