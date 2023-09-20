package com.pradyotprkshpokedex.features.items.controllers

import com.pradyotprkshpokedex.features.items.resource.ItemsResource
import io.ktor.server.application.ApplicationCall

interface ItemController {
    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAll(context: ApplicationCall, resource: ItemsResource)

    suspend fun getDetails(context: ApplicationCall, resource: ItemsResource.Id)

    suspend fun getByPagination(
        context: ApplicationCall,
        resource: ItemsResource.Pagination
    )
}