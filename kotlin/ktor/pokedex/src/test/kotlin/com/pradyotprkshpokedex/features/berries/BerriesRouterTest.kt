package com.pradyotprkshpokedex.features.berries

import com.pradyotprkshpokedex.utils.testBuilder
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.test.Test
import kotlin.test.assertEquals

class BerriesRouterTest {
    @Test
    fun testAllBerry() = testBuilder {
        val response = client.get("/berry")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testBerryPagination() = testBuilder {
        val response = client.get("/berry/all?offset=1&limit=1")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testBerryId() = testBuilder {
        val response = client.get("/berry/1")
        assertEquals(HttpStatusCode.OK, response.status)
    }
}
