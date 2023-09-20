package com.pradyotprkshpokedex.features.moves.controllers

import com.pradyotprkshpokedex.features.moves.resource.MovesResource
import io.ktor.server.application.ApplicationCall

interface MoveLearnMethodsController {
    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAll(context: ApplicationCall, resource: MovesResource.LearnMethod)

    suspend fun getDetails(context: ApplicationCall, resource: MovesResource.LearnMethod.Id)

    suspend fun getByPagination(
        context: ApplicationCall,
        resource: MovesResource.LearnMethod.Pagination
    )
}