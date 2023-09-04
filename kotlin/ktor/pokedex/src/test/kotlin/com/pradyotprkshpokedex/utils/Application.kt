package com.pradyotprkshpokedex.utils

import com.pradyotprkshpokedex.config.configureResource
import com.pradyotprkshpokedex.config.configureRouting
import com.pradyotprkshpokedex.config.configureSerialization
import io.ktor.server.testing.*

fun testBuilder(block: suspend ApplicationTestBuilder.() -> Unit) = testApplication {
    application {
        configureResource()
        configureRouting()
        configureSerialization()
    }

    block(this)
}