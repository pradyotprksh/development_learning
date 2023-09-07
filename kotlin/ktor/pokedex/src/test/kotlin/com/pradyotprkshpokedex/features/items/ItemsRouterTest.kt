package com.pradyotprkshpokedex.features.items

import com.pradyotprkshpokedex.utils.testBuilder
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ItemsRouterTest {
    @Test
    fun testItemPagination() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/item/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/item/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/item/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/item/0").status)
        }
    }

    @Test
    fun testAttributePagination() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/item/attribute/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/item/attribute/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/item/attribute/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/item/attribute/0").status)
        }
    }

    @Test
    fun testCategoryPagination() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/item/category/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/item/category/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/item/category/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/item/category/0").status)
        }
    }

    @Test
    fun testFilingEffectPagination() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/item/fling-effect/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/item/fling-effect/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/item/fling-effect/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/item/fling-effect/0").status)
        }
    }

    @Test
    fun testPocketsPagination() = testBuilder {
        client.apply {
            assertEquals(HttpStatusCode.OK, get("/item/pockets/paginate?offset=1&limit=1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/item/pockets/paginate?offset=-11&limit=1").status)

            assertEquals(HttpStatusCode.OK, get("/item/pockets/1").status)
            assertEquals(HttpStatusCode.BadRequest, get("/item/pockets/0").status)
        }
    }
}
