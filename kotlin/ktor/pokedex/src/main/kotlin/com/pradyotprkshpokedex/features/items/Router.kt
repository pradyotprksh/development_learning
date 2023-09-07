package com.pradyotprkshpokedex.features.items

import com.pradyotprkshpokedex.features.items.resource.ItemsResource
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Routing.items() {
    get<ItemsResource> { }
    get<ItemsResource.Id> { }
    get<ItemsResource.Pagination> { }

    get<ItemsResource.Attribute> { }
    get<ItemsResource.Attribute.Id> { }
    get<ItemsResource.Attribute.Pagination> { }

    get<ItemsResource.Category> { }
    get<ItemsResource.Category.Id> { }
    get<ItemsResource.Category.Pagination> { }

    get<ItemsResource.FilingEffect> { }
    get<ItemsResource.FilingEffect.Id> { }
    get<ItemsResource.FilingEffect.Pagination> { }

    get<ItemsResource.Pockets> { }
    get<ItemsResource.Pockets.Id> { }
    get<ItemsResource.Pockets.Pagination> { }
}