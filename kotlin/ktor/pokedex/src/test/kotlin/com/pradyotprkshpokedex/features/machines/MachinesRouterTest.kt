package com.pradyotprkshpokedex.features.machines

import com.pradyotprkshpokedex.utils.testBuilder
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlin.test.Test
import kotlin.test.assertEquals

class MachinesRouterTest {
    @Test
    fun testMachinePagination() = testBuilder {
        val response = client.get("/machine/paginate?offset=1&limit=1")
        assertEquals(HttpStatusCode.OK, response.status)

        val responseError = client.get("/machine/paginate?offset=-11&limit=1")
        assertEquals(HttpStatusCode.BadRequest, responseError.status)
    }

    @Test
    fun testMachineId() = testBuilder {
        val response = client.get("/machine/1")
        assertEquals(HttpStatusCode.OK, response.status)

        val responseError = client.get("/machine/0")
        assertEquals(HttpStatusCode.BadRequest, responseError.status)
    }
}