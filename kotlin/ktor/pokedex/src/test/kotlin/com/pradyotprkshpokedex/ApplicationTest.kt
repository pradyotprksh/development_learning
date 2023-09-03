package com.pradyotprkshpokedex

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello, world!", bodyAsText())
        }
    }
}
