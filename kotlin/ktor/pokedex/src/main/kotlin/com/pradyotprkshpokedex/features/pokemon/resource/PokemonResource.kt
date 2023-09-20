package com.pradyotprkshpokedex.features.pokemon.resource

import com.pradyotprkshpokedex.utils.Paths
import io.ktor.resources.Resource

@Resource(Paths.Pokemon.POKEMON)
class PokemonResource {
    @Resource(Paths.Pokemon.ABILITY)
    class Ability(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Ability = Ability(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            private val parent: Ability = Ability(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.CHARACTERISTICS)
    class Characteristics(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Characteristics = Characteristics(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            private val parent: Characteristics = Characteristics(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.EGG_GROUP)
    class EggGroup(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: EggGroup = EggGroup(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            private val parent: EggGroup = EggGroup(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.GENDER)
    class Gender(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Gender = Gender(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            private val parent: Gender = Gender(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.GROWTH_RATE)
    class GrowthRate(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: GrowthRate = GrowthRate(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            private val parent: GrowthRate = GrowthRate(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.NATURE)
    class Nature(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Nature = Nature(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            private val parent: Nature = Nature(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.POKEATHLON_STAT)
    class PokeathlonStat(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: PokeathlonStat = PokeathlonStat(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            private val parent: PokeathlonStat = PokeathlonStat(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource("{${Paths.Parameters.ID}}")
    data class Id(private val parent: PokemonResource = PokemonResource(), val id: Int) {
        val isValid: Boolean
            get() = id > 0
    }

    /**
     * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
     */
    @Resource(Paths.Pokemon.PAGINATION)
    data class Pagination(
        private val parent: PokemonResource = PokemonResource(), val offset: Int, val limit: Int,
        val withDetails: Boolean = false
    ) {
        val isValid: Boolean
            get() = offset >= 0 && limit >= 0
    }

    @Resource(Paths.Pokemon.ENCOUNTERS)
    class Encounters(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Encounters = Encounters(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }
    }

    @Resource(Paths.Pokemon.COLOR)
    class Color(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Color = Color(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            private val parent: Color = Color(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.FORM)
    class Form(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Form = Form(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            private val parent: Form = Form(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.HABITAT)
    class Habitat(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Habitat = Habitat(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            private val parent: Habitat = Habitat(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.SHAPE)
    class Shape(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Shape = Shape(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            private val parent: Shape = Shape(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.SPECIES)
    class Species(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Species = Species(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            private val parent: Species = Species(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.STAT)
    class Stat(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Stat = Stat(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            private val parent: Stat = Stat(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }

    @Resource(Paths.Pokemon.TYPE)
    class Type(private val parent: PokemonResource = PokemonResource()) {
        @Resource("{${Paths.Parameters.ID}}")
        data class Id(private val parent: Type = Type(), val id: Int) {
            val isValid: Boolean
                get() = id > 0
        }

        /**
         * @param withDetails If true, and the size if large result might throw Connection Reset / Timeout error.
         */
        @Resource(Paths.Pokemon.PAGINATION)
        data class Pagination(
            private val parent: Type = Type(), val offset: Int, val limit: Int,
            val withDetails: Boolean = false
        ) {
            val isValid: Boolean
                get() = offset >= 0 && limit >= 0
        }
    }
}