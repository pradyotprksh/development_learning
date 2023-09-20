package com.pradyotprkshpokedex.features.evolution.resource

import com.pradyotprkshpokedex.utils.Paths
import io.ktor.resources.Resource

@Resource(Paths.Evolution.EVOLUTION)
class EvolutionResource {

    /**
     * Evolution chains are essentially family trees. They start with the lowest stage within a family and detail
     * evolution conditions for each as well as Pokémon they can evolve into up through the hierarchy.
     */
    @Resource(Paths.Evolution.CHAINS)
    class Chains(private val parent: EvolutionResource = EvolutionResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Chains = Chains(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Evolution.PAGINATION)
        data class Pagination(
            private val parent: Chains = Chains(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * Evolution triggers are the events and conditions that cause a Pokémon to evolve.
     */
    @Resource(Paths.Evolution.TRIGGERS)
    class Triggers(private val parent: EvolutionResource = EvolutionResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Triggers = Triggers(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Evolution.PAGINATION)
        data class Pagination(
            private val parent: Triggers = Triggers(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }
}