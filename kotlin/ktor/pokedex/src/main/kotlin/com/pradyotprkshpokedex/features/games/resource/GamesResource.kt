package com.pradyotprkshpokedex.features.games.resource

import com.pradyotprkshpokedex.utils.Paths
import io.ktor.resources.Resource

@Resource(Paths.Games.GAMES)
class GamesResource {
    /**
     * A generation is a grouping of the Pokémon games that separates them based on the Pokémon they include.
     * In each generation, a new set of Pokémon, Moves, Abilities and Types that did not exist in the previous
     * generation are released.
     */
    @Resource(Paths.Games.GENERATION)
    class Generation(private val parent: GamesResource = GamesResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Generation = Generation(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Games.PAGINATION)
        data class Pagination(
            private val parent: Generation = Generation(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * A Pokédex is a handheld electronic encyclopedia device; one which is capable of recording and retaining
     * information of the various Pokémon in a given region with the exception of the national dex and some
     * smaller dexes related to portions of a region.
     */
    @Resource(Paths.Games.POKEDEX)
    class Pokedex(private val parent: GamesResource = GamesResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Pokedex = Pokedex(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Games.PAGINATION)
        data class Pagination(
            private val parent: Pokedex = Pokedex(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * Versions of the games, e.g., Red, Blue or Yellow.
     */
    @Resource(Paths.Games.VERSION)
    class Version(private val parent: GamesResource = GamesResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Version = Version(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Games.PAGINATION)
        data class Pagination(
            private val parent: Version = Version(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * Version groups categorize highly similar versions of the games.
     */
    @Resource(Paths.Games.VERSION_GROUP)
    class VersionGroup(private val parent: GamesResource = GamesResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: VersionGroup = VersionGroup(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Games.PAGINATION)
        data class Pagination(
            private val parent: VersionGroup = VersionGroup(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }
}