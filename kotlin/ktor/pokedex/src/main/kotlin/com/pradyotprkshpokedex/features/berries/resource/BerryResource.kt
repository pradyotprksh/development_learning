package com.pradyotprkshpokedex.features.berries.resource

import com.pradyotprkshpokedex.utils.Paths
import io.ktor.resources.*

/**
 * Berries are small fruits that can provide HP and status condition restoration, stat enhancement,
 * and even damage negation when eaten by Pokémon.
 */
@Resource(Paths.Berries.BERRY)
class BerryResource {
    @Resource("{${Paths.Parameters.ID}}")
    data class Id(val parent: BerryResource = BerryResource(), val id: Int) {
        val isValid: Boolean
            get() = id > 0
    }

    @Resource(Paths.Berries.PAGINATION)
    data class Pagination(
        val parent: BerryResource = BerryResource(),
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
    class BerryFirmness(val parent: BerryResource = BerryResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: BerryFirmness = BerryFirmness(), val id: String)

        @Resource(Paths.Berries.PAGINATION)
        data class Pagination(val parent: BerryFirmness = BerryFirmness(), val offset: Int, val limit: Int)
    }

    /**
     * Flavors determine whether a Pokémon will benefit or suffer from eating a berry based on their nature.
     */
    @Resource(Paths.Berries.FLAVOR)
    class BerryFlavor(val parent: BerryResource = BerryResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: BerryFlavor = BerryFlavor(), val id: String)

        @Resource(Paths.Berries.PAGINATION)
        data class Pagination(val parent: BerryFlavor = BerryFlavor(), val offset: Int, val limit: Int)
    }
}
