package com.pradyotprkshpokedex.features.pokemon.resource

import com.pradyotprkshpokedex.features.moves.resource.MovesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.resources.*

@Resource(Paths.Pokemon.POKEMON)
class PokemonResource {
    @Resource(Paths.Pokemon.ABILITY)
    class Ability(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Ability = Ability(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            val parent: Ability = Ability(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.CHARACTERISTICS)
    class Characteristics(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Characteristics = Characteristics(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            val parent: Characteristics = Characteristics(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.EGG_GROUP)
    class EggGroup(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: EggGroup = EggGroup(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            val parent: EggGroup = EggGroup(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.GENDER)
    class Gender(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Gender = Gender(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            val parent: Gender = Gender(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.GROWTH_RATE)
    class GrowthRate(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: GrowthRate = GrowthRate(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            val parent: GrowthRate = GrowthRate(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.NATURE)
    class Nature(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Nature = Nature(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            val parent: Nature = Nature(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.POKEATHLON_STAT)
    class PokeathlonStat(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: PokeathlonStat = PokeathlonStat(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            val parent: PokeathlonStat = PokeathlonStat(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource("{${Paths.Parameters.ID}}")
    data class Id(val parent: MovesResource = MovesResource(), val id: Int) {
        val isValid: Boolean
            get() = id > 0
    }

    /**
     * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
     */
    @Resource(Paths.Pokemon.PAGINATION)
    data class Pagination(
        val parent: MovesResource = MovesResource(), val offset: Int, val limit: Int,
        val withDetails: Boolean = false
    ) {
        val isValid: Boolean
            get() = offset >= 0 && limit >= 0
    }

    @Resource(Paths.Pokemon.ENCOUNTERS)
    class Encounters(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Encounters = Encounters(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            val parent: Encounters = Encounters(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.COLOR)
    class Color(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Color = Color(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            val parent: Color = Color(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.FORM)
    class Form(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Form = Form(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            val parent: Form = Form(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.HABITAT)
    class Habitat(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Habitat = Habitat(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            val parent: Habitat = Habitat(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.SHAPE)
    class Shape(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Shape = Shape(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            val parent: Shape = Shape(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.SPECIES)
    class Species(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Species = Species(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            val parent: Species = Species(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.STAT)
    class Stat(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Stat = Stat(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            val parent: Stat = Stat(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.TYPE)
    class Type(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(val parent: Type = Type(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            val parent: Type = Type(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }
}