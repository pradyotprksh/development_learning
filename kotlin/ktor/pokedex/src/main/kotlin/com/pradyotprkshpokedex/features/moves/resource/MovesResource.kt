package com.pradyotprkshpokedex.features.moves.resource

import com.pradyotprkshpokedex.utils.Paths
import io.ktor.resources.Resource

/**
 * Moves are the skills of Pokémon in battle. In battle, a Pokémon uses one move each turn. Some moves
 * (including those learned by Hidden Machine) can be used outside of battle as well, usually for the purpose of
 * removing obstacles or exploring new areas.
 */
@Resource(Paths.Moves.MOVE)
class MovesResource {
    @Resource("{${Paths.Parameters.ID}}")
    data class Id(private val parent: MovesResource = MovesResource(), val id: Int) {
        val isValid: Boolean
            get() = id > 0
    }

    /**
     * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
     */
    @Resource(Paths.Moves.PAGINATION)
    data class Pagination(
        private val parent: MovesResource = MovesResource(), val offset: Int, val limit: Int,
        val withDetails: Boolean = false
    ) {
        val isValid: Boolean
            get() = offset >= 0 && limit >= 0
    }

    /**
     * Move Ailments are status conditions caused by moves used during battle.
     */
    @Resource(Paths.Moves.AILMENT)
    class Ailment(private val parent: MovesResource = MovesResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Ailment = Ailment(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Moves.PAGINATION)
        data class Pagination(
            private val parent: Ailment = Ailment(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * Styles of moves when used in the Battle Palace.
     */
    @Resource(Paths.Moves.BATTLE_STYLE)
    class BattleStyle(private val parent: MovesResource = MovesResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: BattleStyle = BattleStyle(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Moves.PAGINATION)
        data class Pagination(
            private val parent: BattleStyle = BattleStyle(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * Very general categories that loosely group move effects.
     */
    @Resource(Paths.Moves.CATEGORY)
    class Category(private val parent: MovesResource = MovesResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Category = Category(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Moves.PAGINATION)
        data class Pagination(
            private val parent: Category = Category(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * Damage classes moves can have, e.g. physical, special, or non-damaging.
     */
    @Resource(Paths.Moves.DAMAGE_CLASS)
    class DamageClass(private val parent: MovesResource = MovesResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: DamageClass = DamageClass(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Moves.PAGINATION)
        data class Pagination(
            private val parent: DamageClass = DamageClass(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * Methods by which Pokémon can learn moves.
     */
    @Resource(Paths.Moves.LEARN_METHOD)
    class LearnMethod(private val parent: MovesResource = MovesResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: LearnMethod = LearnMethod(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Moves.PAGINATION)
        data class Pagination(
            private val parent: LearnMethod = LearnMethod(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    /**
     * Targets moves can be directed at during battle. Targets can be Pokémon, environments or even other moves.
     */
    @Resource(Paths.Moves.TARGET)
    class Target(private val parent: MovesResource = MovesResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Target = Target(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Moves.PAGINATION)
        data class Pagination(
            private val parent: Target = Target(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }
}