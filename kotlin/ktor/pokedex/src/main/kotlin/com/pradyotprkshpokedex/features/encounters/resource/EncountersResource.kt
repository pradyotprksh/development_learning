package com.pradyotprkshpokedex.features.encounters.resource

import com.pradyotprkshpokedex.utils.Paths
import io.ktor.resources.*

@Resource(Paths.Encounters.ENCOUNTERS)
class EncountersResource {
    /**
     * Methods by which the player might can encounter Pokémon in the wild, e.g., walking in tall grass.
     */
    @Resource(Paths.Encounters.METHODS)
    class Methods(private val parent: EncountersResource = EncountersResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Methods = Methods(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Encounters.PAGINATION)
        data class Pagination(
            val parent: Methods = Methods(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * Conditions which affect what pokemon might appear in the wild, e.g., day or night.
     */
    @Resource(Paths.Encounters.CONDITION)
    class Condition(private val parent: EncountersResource = EncountersResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Condition = Condition(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Encounters.PAGINATION)
        data class Pagination(
            val parent: Condition = Condition(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * Encounter condition values are the various states that an encounter condition can have, i.e., time of day
     * can be either day or night.
     */
    @Resource(Paths.Encounters.CONDITION_VALUE)
    class ConditionValue(private val parent: EncountersResource = EncountersResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: ConditionValue = ConditionValue(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Encounters.PAGINATION)
        data class Pagination(
            val parent: ConditionValue = ConditionValue(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }
}