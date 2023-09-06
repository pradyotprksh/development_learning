package com.pradyotprkshpokedex.features.evolution

import com.pradyotprkshpokedex.utils.testBuilder
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.test.Test
import kotlin.test.assertEquals

class EvolutionRouterTest {
    @Test
    fun testEvolutionChainPagination() = testBuilder {
        val response = client.get("/evolution/chain/paginate?offset=1&limit=1")
        assertEquals(HttpStatusCode.OK, response.status)

        val responseError = client.get("/evolution/chain/paginate?offset=-11&limit=1")
        assertEquals(HttpStatusCode.BadRequest, responseError.status)
    }

    @Test
    fun testEvolutionChainId() = testBuilder {
        val response = client.get("/evolution/chain/1")
        assertEquals(HttpStatusCode.OK, response.status)

        val responseError = client.get("/evolution/chain/0")
        assertEquals(HttpStatusCode.BadRequest, responseError.status)
    }

    @Test
    fun testEvolutionTriggerPagination() = testBuilder {
        val response = client.get("/evolution/trigger/paginate?offset=1&limit=1")
        assertEquals(HttpStatusCode.OK, response.status)

        val responseError = client.get("/evolution/trigger/paginate?offset=-11&limit=1")
        assertEquals(HttpStatusCode.BadRequest, responseError.status)
    }

    @Test
    fun testEvolutionTriggerId() = testBuilder {
        val response = client.get("/evolution/trigger/1")
        assertEquals(HttpStatusCode.OK, response.status)

        val responseError = client.get("/evolution/trigger/0")
        assertEquals(HttpStatusCode.BadRequest, responseError.status)
    }
}
