package com.pradyotprkshpokedex.features.contests

import com.pradyotprkshpokedex.utils.testBuilder
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlin.test.Test
import kotlin.test.assertEquals

class ContestsRouterTest {
    @Test
    fun testTypeRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/contest/type/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/contest/type/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/contest/type/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/contest/type/0").status)
        }
    }

    @Test
    fun testEffectTypeRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/contest/effect/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/contest/effect/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/contest/effect/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/contest/effect/0").status)
        }
    }

    @Test
    fun testSuperContestEffectTypeRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/contest/super-contest-effect/paginate?offset=1&limit=1").status)
            assertEquals(
                HttpStatusCode.BadRequest,
                get("/contest/super-contest-effect/paginate?offset=-11&limit=1").status
            )

            assertEquals(HttpStatusCode.OK, get("/contest/super-contest-effect/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/contest/super-contest-effect/0").status)
        }
    }
}
