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
        val response = client.get("/berry/paginate?offset=1&limit=1")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testBerryId() = testBuilder {
        val response = client.get("/berry/1")
        assertEquals(HttpStatusCode.OK, response.status)

        val responseError = client.get("/berry/0")
        assertEquals(HttpStatusCode.BadRequest, responseError.status)
    }

    @Test
    fun testAllBerryFirmness() = testBuilder {
        val response = client.get("/berry/firmness")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testBerryFirmnessPagination() = testBuilder {
        val response = client.get("/berry/firmness/all?offset=1&limit=1")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testBerryFirmnessId() = testBuilder {
        val response = client.get("/berry/firmness/1")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testAllBerryFlavor() = testBuilder {
        val response = client.get("/berry/flavor")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testBerryFlavorPagination() = testBuilder {
        val response = client.get("/berry/flavor/all?offset=1&limit=1")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testBerryFlavorId() = testBuilder {
        val response = client.get("/berry/flavor/1")
        assertEquals(HttpStatusCode.OK, response.status)
    }
}
