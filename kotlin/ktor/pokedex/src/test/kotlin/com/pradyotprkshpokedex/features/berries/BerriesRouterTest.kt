package com.pradyotprkshpokedex.features.berries

import com.pradyotprkshpokedex.utils.testBuilder
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.test.Test
import kotlin.test.assertEquals

class BerriesRouterTest {
    @Test
    fun testBerryRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/berry/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/berry/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/berry/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/berry/0").status)
        }
    }

    @Test
    fun testBerryFirmnessRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/berry/firmness/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/berry/firmness/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/berry/firmness/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/berry/firmness/0").status)
        }
    }

    @Test
    fun testBerryFlavourRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/berry/flavor/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/berry/flavor/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/berry/flavor/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/berry/flavor/0").status)
        }
    }
}
