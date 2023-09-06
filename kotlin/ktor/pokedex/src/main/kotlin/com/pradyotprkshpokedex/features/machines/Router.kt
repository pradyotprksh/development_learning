package com.pradyotprkshpokedex.features.machines

import com.pradyotprkshpokedex.features.machines.controllers.MachinesController
import com.pradyotprkshpokedex.features.machines.resource.MachinesResource
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Routing.machines(
    machinesController: MachinesController
) {
    get<MachinesResource> { machinesController.getAllMachines(this.context) }
    get<MachinesResource.Id> { machinesController.getMachineDetails(this.context, it) }
    get<MachinesResource.Pagination> { machinesController.getMachinesByPagination(this.context, it) }
}