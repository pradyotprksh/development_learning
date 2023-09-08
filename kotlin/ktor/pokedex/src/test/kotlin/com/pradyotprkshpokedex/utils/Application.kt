package com.pradyotprkshpokedex.utils

import com.pradyotprkshpokedex.config.mainModule
import io.ktor.server.testing.ApplicationTestBuilder
import io.ktor.server.testing.testApplication

fun testBuilder(block: suspend ApplicationTestBuilder.() -> Unit) = testApplication {
    application {
        mainModule()
    }

    block(this)
}