package com.pradyotprkshpokedex.features.machines.controllers.implementation

import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.MachineService
import com.pradyotprkshpokedex.domain.modal.Berry
import com.pradyotprkshpokedex.domain.modal.Machine
import com.pradyotprkshpokedex.domain.modal.Machines
import com.pradyotprkshpokedex.features.machines.controllers.MachineController
import com.pradyotprkshpokedex.features.machines.resource.MachinesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MachinesControllerImplementation(
    private val machineService: MachineService
) : MachineController {
    override suspend fun getMachinesByPagination(
        context: ApplicationCall,
        machinesResource: MachinesResource.Pagination
    ) {
        if (machinesResource.isValid) {
            val machines =
                machineService.getMachinesByPagination(offset = machinesResource.offset, limit = machinesResource.limit)
            if (machinesResource.withDetails) {
                respondWithMachinesDetails(context, machines)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    machines
                )
            }
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.OFFSET, Paths.Parameters.LIMIT))
        }
    }

    override suspend fun getAllMachines(context: ApplicationCall) {
        val allMachines = machineService.getMachinesByPagination(offset = 0, limit = Int.MAX_VALUE)
        respondWithMachinesDetails(context, allMachines)
    }

    override suspend fun getMachineDetails(context: ApplicationCall, machinesResource: MachinesResource.Id) {
        if (machinesResource.isValid) {
            context.respond(status = HttpStatusCode.OK, machineService.getMachineDetails(id = machinesResource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    private suspend fun respondWithMachinesDetails(context: ApplicationCall, machines: Machines) {
        coroutineScope {
            val count = machines.results.size
            val channels = Channel<Machine>()
            machines.results.forEach { result ->
                result.url?.let { url ->
                    launch {
                        delay(1)
                        channels.send(
                            machineService.getMachineDetails(id = 0, path = url)
                        )
                    }
                }
            }
            val machinesDetails = mutableListOf<Machine>()
            repeat(count) {
                machinesDetails.add(channels.receive())
            }

            context.respond(status = HttpStatusCode.OK, machinesDetails)
        }
    }
}