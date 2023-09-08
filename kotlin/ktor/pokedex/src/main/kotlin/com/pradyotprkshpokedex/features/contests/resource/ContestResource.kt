package com.pradyotprkshpokedex.features.contests.resource

import com.pradyotprkshpokedex.utils.Paths
import io.ktor.resources.Resource

@Resource(Paths.Contests.CONTESTS)
class ContestResource {
    /**
     * Contest types are categories judges used to weigh a Pokémon's condition in Pokémon contests.
     */
    @Resource(Paths.Contests.TYPE)
    class Type(private val parent: ContestResource = ContestResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Type = Type(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Contests.PAGINATION)
        data class Pagination(
            val parent: Type = Type(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * Contest effects refer to the effects of moves when used in contests.
     */
    @Resource(Paths.Contests.EFFECT)
    class Effect(private val parent: ContestResource = ContestResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Effect = Effect(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Contests.PAGINATION)
        data class Pagination(
            val parent: Effect = Effect(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * Super contest effects refer to the effects of moves when used in super contests.
     */
    @Resource(Paths.Contests.SUPER_CONTEST_EFFECT)
    class SupperContestEffect(private val parent: ContestResource = ContestResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: SupperContestEffect = SupperContestEffect(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Contests.PAGINATION)
        data class Pagination(
            val parent: SupperContestEffect = SupperContestEffect(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }
}