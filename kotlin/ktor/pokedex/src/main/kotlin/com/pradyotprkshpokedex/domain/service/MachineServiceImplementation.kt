package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.exception.PokeApiException
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.request.PokeApiRequestDetails
import com.pradyotprkshpokedex.core.service.MachineService
import com.pradyotprkshpokedex.domain.modal.Machine
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.utils.Paths

class MachineServiceImplementation(
    private val networkClient: NetworkClient
) : MachineService {
    override suspend fun getMachinesByPagination(offset: Int, limit: Int): Pagination {
        val machines = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Machines.MACHINE,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return machines.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getMachineDetails(id: Int, path: String?): Machine {
        val machineDetails = networkClient.get<Machine>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Machines.MACHINE}/$id",
                fullPath = path,
            )
        )

        return machineDetails.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }
}