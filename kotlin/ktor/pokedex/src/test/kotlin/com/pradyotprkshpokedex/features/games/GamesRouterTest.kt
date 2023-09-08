package com.pradyotprkshpokedex.features.games

import com.pradyotprkshpokedex.utils.testBuilder
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlin.test.Test
import kotlin.test.assertEquals

class GamesRouterTest {
    @Test
    fun testGenerationPagination() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/game/generation/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/game/generation/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/game/generation/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/game/generation/0").status)
        }
    }

    @Test
    fun testPokedexPagination() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/game/pokedex/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/game/pokedex/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/game/pokedex/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/game/pokedex/0").status)
        }
    }

    @Test
    fun testVersionPagination() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/game/version/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/game/version/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/game/version/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/game/version/0").status)
        }
    }

    @Test
    fun testVersionGroupPagination() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/game/version-group/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/game/version-group/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/game/version-group/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/game/version-group/0").status)
        }
    }
}
