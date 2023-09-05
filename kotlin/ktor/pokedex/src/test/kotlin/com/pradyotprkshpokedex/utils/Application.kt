package com.pradyotprkshpokedex.utils

import com.pradyotprkshpokedex.config.*
import io.ktor.server.testing.*

fun testBuilder(block: suspend ApplicationTestBuilder.() -> Unit) = testApplication {
    application {
        mainModule()
    }

    block(this)
}