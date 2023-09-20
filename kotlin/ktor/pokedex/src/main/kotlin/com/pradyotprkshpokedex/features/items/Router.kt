package com.pradyotprkshpokedex.features.items

import com.pradyotprkshpokedex.features.items.controllers.ItemsController
import com.pradyotprkshpokedex.features.items.resource.ItemsResource
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.items(itemsController: ItemsController) {
    get<ItemsResource> { itemsController.getAll(this.context, it) }
    get<ItemsResource.Id> { itemsController.getDetails(this.context, it) }
    get<ItemsResource.Pagination> { itemsController.getByPagination(this.context, it) }

    get<ItemsResource.Attribute> { itemsController.getAll(this.context, it) }
    get<ItemsResource.Attribute.Id> { itemsController.getDetails(this.context, it) }
    get<ItemsResource.Attribute.Pagination> { itemsController.getByPagination(this.context, it) }

    get<ItemsResource.Category> { itemsController.getAll(this.context, it) }
    get<ItemsResource.Category.Id> { itemsController.getDetails(this.context, it) }
    get<ItemsResource.Category.Pagination> { itemsController.getByPagination(this.context, it) }

    get<ItemsResource.FilingEffect> { itemsController.getAll(this.context, it) }
    get<ItemsResource.FilingEffect.Id> { itemsController.getDetails(this.context, it) }
    get<ItemsResource.FilingEffect.Pagination> { itemsController.getByPagination(this.context, it) }

    get<ItemsResource.Pockets> { itemsController.getAll(this.context, it) }
    get<ItemsResource.Pockets.Id> { itemsController.getDetails(this.context, it) }
    get<ItemsResource.Pockets.Pagination> { itemsController.getByPagination(this.context, it) }
}