package com.pradyotprkshpokedex.features.machines.controllers

import com.pradyotprkshpokedex.features.machines.resource.MachinesResource
import io.ktor.server.application.ApplicationCall

interface MachineController {
    suspend fun getMachinesByPagination(context: ApplicationCall, machinesResource: MachinesResource.Pagination)

    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAllMachines(context: ApplicationCall)

    suspend fun getMachineDetails(context: ApplicationCall, machinesResource: MachinesResource.Id)
}