package com.pradyotprkshpokedex.features.contests

import com.pradyotprkshpokedex.utils.testBuilder
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ContestsRouterTest {
    @Test
    fun testTypeRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/contests/type/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/contests/type/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/contests/type/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/contests/type/0").status)
        }
    }

    @Test
    fun testEffectTypeRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/contests/effect/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/contests/effect/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/contests/effect/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/contests/effect/0").status)
        }
    }

    @Test
    fun testSuperContestEffectTypeRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/contests/super-contest-effect/paginate?offset=1&limit=1").status)
            assertEquals(
                HttpStatusCode.BadRequest,
                get("/contests/super-contest-effect/paginate?offset=-11&limit=1").status
            )

            assertEquals(HttpStatusCode.OK, get("/contests/super-contest-effect/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/contests/super-contest-effect/0").status)
        }
    }
}
