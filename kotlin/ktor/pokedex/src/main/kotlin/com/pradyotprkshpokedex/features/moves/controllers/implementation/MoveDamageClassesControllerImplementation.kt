package com.pradyotprkshpokedex.features.moves.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.MoveService
import com.pradyotprkshpokedex.domain.modal.DamageClass
import com.pradyotprkshpokedex.features.moves.controllers.MoveDamageClassesController
import com.pradyotprkshpokedex.features.moves.resource.MovesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class MoveDamageClassesControllerImplementation(
    private val moveService: MoveService,
    private val defaultController: DefaultController,
) : MoveDamageClassesController {
    override suspend fun getAll(context: ApplicationCall, resource: MovesResource.DamageClass) {
        val allDamageClasses = moveService.getDamageClassByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<DamageClass>(context, allDamageClasses)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: MovesResource.DamageClass.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, moveService.getDamageClassDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: MovesResource.DamageClass.Pagination) {
        if (resource.isValid) {
            val damageClasses =
                moveService.getDamageClassByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {

                    defaultController.respondWithDetails<DamageClass>(context, damageClasses)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    damageClasses
                )
            }
        } else {
            throw ParametersInvalidException(
                invalidParameters = listOf(
                    Paths.Parameters.OFFSET,
                    Paths.Parameters.LIMIT
                )
            )
        }
    }
}