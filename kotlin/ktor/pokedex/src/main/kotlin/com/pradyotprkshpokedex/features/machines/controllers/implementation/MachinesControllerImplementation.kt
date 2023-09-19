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
    override suspend fun getMachinesByPagination(
        context: ApplicationCall,
        machinesResource: MachinesResource.Pagination
    ) {
        if (machinesResource.isValid) {
            val machines =
                machineService.getMachinesByPagination(offset = machinesResource.offset, limit = machinesResource.limit)
            if (machinesResource.withDetails) {
                defaultController.respondWithDetails<Machine>(context, machines)
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

    override suspend fun getAllMachines(context: ApplicationCall) {
        val allMachines = machineService.getMachinesByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<Machine>(context, allMachines)
    }

    override suspend fun getMachineDetails(context: ApplicationCall, machinesResource: MachinesResource.Id) {
        if (machinesResource.isValid) {
            context.respond(status = HttpStatusCode.OK, machineService.getMachineDetails(id = machinesResource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }
}