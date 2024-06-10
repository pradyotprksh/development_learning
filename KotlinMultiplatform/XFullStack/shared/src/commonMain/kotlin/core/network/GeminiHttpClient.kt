package core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import utils.Constants.ConstValues.APPLICATION_JSON
import utils.Constants.ConstValues.GEMINI_BASE_URL
import utils.Constants.Keys.CONTENT_TYPE

object GeminiHttpClient {

    fun createHttpClient() = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                },
            )
        }
        install(Logging)
        install(DefaultRequest)

        defaultRequest {
            url(GEMINI_BASE_URL)

            header(CONTENT_TYPE, APPLICATION_JSON)
        }
    }
}