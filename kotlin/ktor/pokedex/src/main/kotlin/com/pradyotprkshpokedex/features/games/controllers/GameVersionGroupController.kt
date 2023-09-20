package com.pradyotprkshpokedex.features.games.controllers

import com.pradyotprkshpokedex.features.games.resource.GamesResource
import io.ktor.server.application.ApplicationCall

interface GameVersionGroupController {
    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAll(context: ApplicationCall, resource: GamesResource.VersionGroup)

    suspend fun getDetails(context: ApplicationCall, resource: GamesResource.VersionGroup.Id)

    suspend fun getByPagination(
        context: ApplicationCall,
        resource: GamesResource.VersionGroup.Pagination
    )
}