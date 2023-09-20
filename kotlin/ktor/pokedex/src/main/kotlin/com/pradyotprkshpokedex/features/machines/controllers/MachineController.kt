package com.pradyotprkshpokedex.features.machines.controllers

import com.pradyotprkshpokedex.features.machines.resource.MachinesResource
import io.ktor.server.application.ApplicationCall

interface MachineController {
    suspend fun getByPagination(context: ApplicationCall, resource: MachinesResource.Pagination)

    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAll(context: ApplicationCall)

    suspend fun getDetails(context: ApplicationCall, resource: MachinesResource.Id)
}