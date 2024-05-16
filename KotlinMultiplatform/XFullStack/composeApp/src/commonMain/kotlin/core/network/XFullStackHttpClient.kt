package core.network

import core.utils.Constants.ConstValues.APPLICATION_JSON
import core.utils.Constants.ConstValues.BASE_URL
import core.utils.Constants.ConstValues.BEARER
import core.utils.Constants.Keys.AUTHORIZATION
import core.utils.Constants.Keys.CONTENT_TYPE
import core.utils.Constants.Keys.REQUEST_IDENTIFIER
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
                header(AUTHORIZATION, "$BEARER ${currentUserRepository.getToken(userId)}")
            }
        }
    }
}