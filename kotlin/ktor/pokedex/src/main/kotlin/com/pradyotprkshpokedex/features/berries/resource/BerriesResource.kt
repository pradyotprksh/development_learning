package com.pradyotprkshpokedex.features.berries.resource

import com.pradyotprkshpokedex.utils.Paths
import io.ktor.resources.Resource

/**
 * Berries are small fruits that can provide HP and status condition restoration, stat enhancement,
 * and even damage negation when eaten by Pokémon.
 */
@Resource(Paths.Berries.BERRY)
class BerriesResource {
    @Resource("{${Paths.Parameters.ID}}")
    data class Id(val parent: BerriesResource = BerriesResource(), val id: Int) {
        val isValid: Boolean
            get() = id > 0
    }

    /**
     * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
     */
    @Resource(Paths.Berries.PAGINATION)
    data class Pagination(
        private val parent: BerriesResource = BerriesResource(),
        val offset: Int,
        val limit: Int,
        val withDetails: Boolean = false
    ) {
        val isValid: Boolean
            get() = offset >= 0 && limit >= 0
    }

    /**
     * Berries can be soft or hard.
     */
    @Resource(Paths.Berries.FIRMNESS)
    class BerryFirmness(private val parent: BerriesResource = BerriesResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: BerryFirmness = BerryFirmness(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Berries.PAGINATION)
        data class Pagination(
            private val parent: BerryFirmness = BerryFirmness(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * Flavors determine whether a Pokémon will benefit or suffer from eating a berry based on their nature.
     */
    @Resource(Paths.Berries.FLAVOR)
    class BerryFlavor(private val parent: BerriesResource = BerriesResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: BerryFlavor = BerryFlavor(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Berries.PAGINATION)
        data class Pagination(
            private val parent: BerryFlavor = BerryFlavor(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }
}
