package com.pradyotprkshpokedex.features.pokemon

import com.pradyotprkshpokedex.utils.testBuilder
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlin.test.Test
import kotlin.test.assertEquals

class PokemonRouterTest {
    @Test
    fun testAbilityRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/pokemon/ability/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/ability/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/pokemon/ability/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/ability/0").status)
        }
    }

    @Test
    fun testCharacteristicsRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/pokemon/characteristic/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/characteristic/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/pokemon/characteristic/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/characteristic/0").status)
        }
    }

    @Test
    fun testEggGroupRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/pokemon/egg-group/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/egg-group/paginate?offset=11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/pokemon/egg-group/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/egg-group/0").status)
        }
    }

    @Test
    fun testGenderRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/pokemon/gender/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/gender/paginate?offset=11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/pokemon/gender/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/gender/0").status)
        }
    }

    @Test
    fun testGrowthRateRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/pokemon/growth-rate/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/growth-rate/paginate?offset=11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/pokemon/growth-rate/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/growth-rate/0").status)
        }
    }

    @Test
    fun testNatureRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/pokemon/nature/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/nature/paginate?offset=11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/pokemon/nature/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/nature/0").status)
        }
    }

    @Test
    fun testPokemonStatRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/pokemon/pokeathlon-stat/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/pokeathlon-stat/paginate?offset=11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/pokemon/pokeathlon-stat/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/pokeathlon-stat/0").status)
        }
    }

    @Test
    fun testPokemonRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/pokemon/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/paginate?offset=11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/pokemon/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/0").status)
        }
    }

    @Test
    fun testEncountersRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/pokemon/encounters/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/encounters/paginate?offset=11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/pokemon/encounters/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/encounters/0").status)
        }
    }

    @Test
    fun testColorRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/pokemon/color/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/color/paginate?offset=11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/pokemon/color/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/color/0").status)
        }
    }

    @Test
    fun testFormRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/pokemon/form/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/form/paginate?offset=11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/pokemon/form/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/form/0").status)
        }
    }

    @Test
    fun testHabitatClassRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/pokemon/habitat/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/habitat/paginate?offset=11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/pokemon/habitat/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/habitat/0").status)
        }
    }

    @Test
    fun testShapeRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/pokemon/shape/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/shape/paginate?offset=11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/pokemon/shape/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/shape/0").status)
        }
    }

    @Test
    fun testSpeciesRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/pokemon/species/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/species/paginate?offset=11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/pokemon/species/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/species/0").status)
        }
    }

    @Test
    fun testStatRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/pokemon/stat/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/stat/paginate?offset=11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/pokemon/stat/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/stat/0").status)
        }
    }

    @Test
    fun testTypeRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/pokemon/type/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/type/paginate?offset=11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/pokemon/type/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/pokemon/type/0").status)
        }
    }


}
