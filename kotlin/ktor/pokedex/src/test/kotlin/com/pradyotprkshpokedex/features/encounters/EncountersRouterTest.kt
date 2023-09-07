package com.pradyotprkshpokedex.features.encounters

import com.pradyotprkshpokedex.utils.testBuilder
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.test.Test
import kotlin.test.assertEquals

class EncountersRouterTest {
    @Test
    fun testMethodRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/encounter/method/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/encounter/method/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/encounter/method/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/encounter/method/0").status)
        }
    }

    @Test
    fun testConditionRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/encounter/condition/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/encounter/condition/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/encounter/condition/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/encounter/condition/0").status)
        }
    }

    @Test
    fun testConditionValueRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/encounter/condition-value/paginate?offset=1&limit=1").status)
            assertEquals(
                HttpStatusCode.BadRequest,
                get("/encounter/condition-value/paginate?offset=-11&limit=1").status
            )

            assertEquals(HttpStatusCode.OK, get("/encounter/condition-value/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/encounter/condition-value/0").status)
        }
    }
}
