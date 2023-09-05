package com.pradyotprkshpokedex.features.berries.resource

import com.pradyotprkshpokedex.utils.Paths
import io.ktor.resources.*

/**
 * Berries are small fruits that can provide HP and status condition restoration, stat enhancement,
 * and even damage negation when eaten by Pokémon.
 */
@Resource(Paths.Berries.BERRY)
class Berry {
    @Resource("{${Paths.ID}}")
    data class Id(val parent: Berry = Berry(), val id: String)

    @Resource(Paths.Berries.PAGINATION)
    data class Pagination(val parent: Berry = Berry(), val offset: Int, val limit: Int)

    /**
     * Berries can be soft or hard.
     */
    @Resource(Paths.Berries.FIRMNESS)
    class BerryFirmness(val parent: Berry = Berry()) {
        @Resource("{${Paths.ID}}")
        data class Id(val parent: BerryFirmness = BerryFirmness(), val id: String)

        @Resource(Paths.Berries.PAGINATION)
        data class Pagination(val parent: BerryFirmness = BerryFirmness(), val offset: Int, val limit: Int)
    }

    /**
     * Flavors determine whether a Pokémon will benefit or suffer from eating a berry based on their nature.
     */
    @Resource(Paths.Berries.FLAVOR)
    class BerryFlavor(val parent: Berry = Berry()) {
        @Resource("{${Paths.ID}}")
        data class Id(val parent: BerryFlavor = BerryFlavor(), val id: String)

        @Resource(Paths.Berries.PAGINATION)
        data class Pagination(val parent: BerryFlavor = BerryFlavor(), val offset: Int, val limit: Int)
    }
}
