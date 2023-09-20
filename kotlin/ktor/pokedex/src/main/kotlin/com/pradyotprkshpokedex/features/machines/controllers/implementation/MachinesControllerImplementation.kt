package com.pradyotprkshpokedex.features.machines.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.MachineService
import com.pradyotprkshpokedex.domain.modal.Machine
import com.pradyotprkshpokedex.features.machines.controllers.MachineController
import com.pradyotprkshpokedex.features.machines.resource.MachinesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class MachinesControllerImplementation(
    private val machineService: MachineService,
    private val defaultController: DefaultController,
) : MachineController {
    override suspend fun getByPagination(
        context: ApplicationCall,
        resource: MachinesResource.Pagination
    ) {
        if (resource.isValid) {
            val machines =
                machineService.getMachinesByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Machine>(machines))
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    machines
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

    override suspend fun getAll(context: ApplicationCall) {
        val allMachines = machineService.getMachinesByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Machine>(allMachines))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: MachinesResource.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, machineService.getMachineDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }
}