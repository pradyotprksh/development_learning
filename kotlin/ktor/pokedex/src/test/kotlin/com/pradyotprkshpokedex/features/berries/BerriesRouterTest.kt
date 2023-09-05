package com.pradyotprkshpokedex.features.berries

import com.pradyotprkshpokedex.utils.testBuilder
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.test.Test
import kotlin.test.assertEquals

class BerriesRouterTest {
    @Test
    fun testBerryPagination() = testBuilder {
        val response = client.get("/berry/paginate?offset=1&limit=1")
        assertEquals(HttpStatusCode.OK, response.status)

        val responseError = client.get("/berry/paginate?offset=-11&limit=1")
        assertEquals(HttpStatusCode.BadRequest, responseError.status)
    }

    @Test
    fun testBerryId() = testBuilder {
        val response = client.get("/berry/1")
        assertEquals(HttpStatusCode.OK, response.status)

        val responseError = client.get("/berry/0")
        assertEquals(HttpStatusCode.BadRequest, responseError.status)
    }

    @Test
    fun testBerryFirmnessPagination() = testBuilder {
        val response = client.get("/berry/firmness/paginate?offset=1&limit=1")
        assertEquals(HttpStatusCode.OK, response.status)

        val responseError = client.get("/berry/firmness/paginate?offset=-11&limit=1")
        assertEquals(HttpStatusCode.BadRequest, responseError.status)
    }

    @Test
    fun testBerryFirmnessId() = testBuilder {
        val response = client.get("/berry/firmness/1")
        assertEquals(HttpStatusCode.OK, response.status)

        val responseError = client.get("/berry/firmness/0")
        assertEquals(HttpStatusCode.BadRequest, responseError.status)
    }

    @Test
    fun testBerryFlavorPagination() = testBuilder {
        val response = client.get("/berry/flavor/paginate?offset=1&limit=1")
        assertEquals(HttpStatusCode.OK, response.status)

        val responseError = client.get("/berry/flavor/paginate?offset=-11&limit=1")
        assertEquals(HttpStatusCode.BadRequest, responseError.status)
    }

    @Test
    fun testBerryFlavorId() = testBuilder {
        val response = client.get("/berry/flavor/1")
        assertEquals(HttpStatusCode.OK, response.status)

        val responseError = client.get("/berry/flavor/0")
        assertEquals(HttpStatusCode.BadRequest, responseError.status)
    }
}
