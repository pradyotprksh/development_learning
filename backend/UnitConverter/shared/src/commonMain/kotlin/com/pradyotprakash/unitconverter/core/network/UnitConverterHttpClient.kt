package com.pradyotprakash.unitconverter.core.network

import com.pradyotprakash.unitconverter.utils.Constants.ConstValues.BASE_URL
import com.pradyotprakash.unitconverter.utils.Constants.ConstValues.TIMEOUT_VALUE
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object UnitConverterHttpClient {
    fun createHttpClient() = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging)
        install(DefaultRequest)
        install(HttpTimeout) {
            requestTimeoutMillis = TIMEOUT_VALUE
            connectTimeoutMillis = TIMEOUT_VALUE
            socketTimeoutMillis = TIMEOUT_VALUE
        }

        defaultRequest {
            url("$BASE_URL/")
        }
    }
}