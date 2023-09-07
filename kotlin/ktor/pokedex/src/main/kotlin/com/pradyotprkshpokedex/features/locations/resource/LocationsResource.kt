package com.pradyotprkshpokedex.features.locations.resource

import com.pradyotprkshpokedex.utils.Paths
import io.ktor.resources.*

/**
 * Locations that can be visited within the games. Locations make up sizable portions of regions,
 * like cities or routes.
 */
@Resource(Paths.Locations.LOCATION)
class LocationsResource {
    @Resource("{${Paths.Parameters.ID}}")
    data class Id(val parent: LocationsResource = LocationsResource(), val id: Int) {
        val isValid: Boolean
            get() = id > 0
    }

    /**
     * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
     */
    @Resource(Paths.Locations.PAGINATION)
    data class Pagination(
        val parent: LocationsResource = LocationsResource(), val offset: Int, val limit: Int,
        val withDetails: Boolean = false
    ) {
        val isValid: Boolean
            get() = offset >= 0 && limit >= 0
    }

    /**
     * Location areas are sections of areas, such as floors in a building or cave. Each area has its own set
     * of possible Pokémon encounters.
     */
    @Resource(Paths.Locations.AREA)
    class Area(private val parent: LocationsResource = LocationsResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Area = Area(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Locations.PAGINATION)
        data class Pagination(
            val parent: Area = Area(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * Areas used for grouping Pokémon encounters in Pal Park. They're like habitats that are specific to Pal Park.
     */
    @Resource(Paths.Locations.PAL_PAK_AREA)
    class PalPakArea(private val parent: LocationsResource = LocationsResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: PalPakArea = PalPakArea(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Locations.PAGINATION)
        data class Pagination(
            val parent: PalPakArea = PalPakArea(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * A region is an organized area of the Pokémon world. Most often, the main difference between regions is the
     * species of Pokémon that can be encountered within them.
     */
    @Resource(Paths.Locations.REGION)
    class Region(private val parent: LocationsResource = LocationsResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Region = Region(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Locations.PAGINATION)
        data class Pagination(
            val parent: Region = Region(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }
}