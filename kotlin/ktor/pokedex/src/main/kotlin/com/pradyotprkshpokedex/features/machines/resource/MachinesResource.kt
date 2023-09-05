package com.pradyotprkshpokedex.features.machines.resource

import com.pradyotprkshpokedex.utils.Paths
import io.ktor.resources.*

/**
 * Machines are the representation of items that teach moves to Pokémon. They vary from version to version, so it
 * is not certain that one specific TM or HM corresponds to a single Machine.
 */
@Resource(Paths.Machines.MACHINE)
class MachinesResource {
    @Resource("{${Paths.Parameters.ID}}")
    data class Id(val parent: MachinesResource = MachinesResource(), val id: Int) {
        val isValid: Boolean
            get() = id > 0
    }

    /**
     * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
     */
    @Resource(Paths.Berries.PAGINATION)
    data class Pagination(
        val parent: MachinesResource = MachinesResource(),
        val offset: Int,
        val limit: Int,
        val withDetails: Boolean = false
    ) {
        val isValid: Boolean
            get() = offset >= 0 && limit >= 0
    }
}