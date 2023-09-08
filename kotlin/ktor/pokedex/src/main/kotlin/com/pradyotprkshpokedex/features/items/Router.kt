package com.pradyotprkshpokedex.features.items

import com.pradyotprkshpokedex.features.items.resource.ItemsResource
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing

fun Routing.items() {
    get<ItemsResource> { call.respond(status = HttpStatusCode.OK, "") }
    get<ItemsResource.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<ItemsResource.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<ItemsResource.Attribute> { call.respond(status = HttpStatusCode.OK, "") }
    get<ItemsResource.Attribute.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<ItemsResource.Attribute.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<ItemsResource.Category> { call.respond(status = HttpStatusCode.OK, "") }
    get<ItemsResource.Category.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<ItemsResource.Category.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<ItemsResource.FilingEffect> { call.respond(status = HttpStatusCode.OK, "") }
    get<ItemsResource.FilingEffect.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<ItemsResource.FilingEffect.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<ItemsResource.Pockets> { call.respond(status = HttpStatusCode.OK, "") }
    get<ItemsResource.Pockets.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<ItemsResource.Pockets.Pagination> { call.respond(status = HttpStatusCode.OK, "") }
}