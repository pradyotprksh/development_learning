package com.pradyotprkshpokedex.features.utility

import com.pradyotprkshpokedex.utils.testBuilder
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlin.test.Test
import kotlin.test.assertEquals

class UtilityRouterTest {
    @Test
    fun testAllPokemonImagesRoutes() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/utility/all-pokemon-images").status)
        }
    }

}
