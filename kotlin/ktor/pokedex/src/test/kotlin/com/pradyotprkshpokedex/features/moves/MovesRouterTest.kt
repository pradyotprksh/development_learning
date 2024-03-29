package com.pradyotprkshpokedex.features.moves

import com.pradyotprkshpokedex.utils.testBuilder
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlin.test.Test
import kotlin.test.assertEquals

class MovesRouterTest {
    @Test
    fun testMoveRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/move/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/move/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/move/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/move/0").status)
        }
    }

    @Test
    fun testAlinmentRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/move/ailment/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/move/ailment/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/move/ailment/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/move/ailment/0").status)
        }
    }

    @Test
    fun testBattleStyleRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/move/battle-style/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/move/battle-style/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/move/battle-style/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/move/battle-style/0").status)
        }
    }

    @Test
    fun testCategoryRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/move/category/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/move/category/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/move/category/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/move/category/0").status)
        }
    }

    @Test
    fun testDamageClassRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/move/damage-class/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/move/damage-class/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/move/damage-class/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/move/damage-class/0").status)
        }
    }

    @Test
    fun testLearnMethodRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/move/learn-method/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/move/learn-method/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/move/learn-method/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/move/learn-method/0").status)
        }
    }

    @Test
    fun testTargetRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/move/target/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/move/target/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/move/target/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/move/target/0").status)
        }
    }
}
