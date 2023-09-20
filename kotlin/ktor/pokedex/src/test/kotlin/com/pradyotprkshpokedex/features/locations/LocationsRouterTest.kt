package com.pradyotprkshpokedex.features.locations

import com.pradyotprkshpokedex.utils.testBuilder
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlin.test.Test
import kotlin.test.assertEquals

class LocationsRouterTest {
    @Test
    fun testLocationRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/location/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/location/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/location/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/location/0").status)
        }
    }

    @Test
    fun testAreaRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/location/region/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/location/region/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/location/region/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/location/region/0").status)
        }
    }

    @Test
    fun testPalPakAreaRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/location/pal-park-area/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/location/pal-park-area/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/location/pal-park-area/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/location/pal-park-area/0").status)
        }
    }

    @Test
    fun testRegionRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/location/region/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/location/region/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/location/region/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/location/region/0").status)
        }
    }
}
