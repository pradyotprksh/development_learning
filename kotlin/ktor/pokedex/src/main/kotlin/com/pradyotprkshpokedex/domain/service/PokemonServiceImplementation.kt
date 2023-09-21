package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.exception.PokeApiException
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.request.PokeApiRequestDetails
import com.pradyotprkshpokedex.core.service.PokemonService
import com.pradyotprkshpokedex.domain.modal.Ability
import com.pradyotprkshpokedex.domain.modal.Characteristic
import com.pradyotprkshpokedex.domain.modal.Color
import com.pradyotprkshpokedex.domain.modal.EggGroup
import com.pradyotprkshpokedex.domain.modal.Form
import com.pradyotprkshpokedex.domain.modal.Gender
import com.pradyotprkshpokedex.domain.modal.GrowthRate
import com.pradyotprkshpokedex.domain.modal.Habitat
import com.pradyotprkshpokedex.domain.modal.LocationArea
import com.pradyotprkshpokedex.domain.modal.Nature
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.domain.modal.PokeathlonStat
import com.pradyotprkshpokedex.domain.modal.Pokemon
import com.pradyotprkshpokedex.domain.modal.Shape
import com.pradyotprkshpokedex.domain.modal.Species
import com.pradyotprkshpokedex.domain.modal.Stat
import com.pradyotprkshpokedex.domain.modal.Type
import com.pradyotprkshpokedex.utils.Paths

class PokemonServiceImplementation(private val networkClient: NetworkClient) : PokemonService {
    override suspend fun getAbilityDetails(id: Int, path: String?): Ability {
        val ability = networkClient.get<Ability>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.ABILITY}/$id",
                fullPath = path
            )
        )

        return ability.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getAbilityByPagination(offset: Int, limit: Int): Pagination {
        val abilities = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Pokemon.ABILITY,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return abilities.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getCharacteristicDetails(id: Int, path: String?): Characteristic {
        val characteristic = networkClient.get<Characteristic>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.CHARACTERISTICS}/$id",
                fullPath = path
            )
        )

        return characteristic.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getCharacteristicByPagination(offset: Int, limit: Int): Pagination {
        val characteristics = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Pokemon.CHARACTERISTICS,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return characteristics.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getEggGroupDetails(id: Int, path: String?): EggGroup {
        val eggGroup = networkClient.get<EggGroup>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.EGG_GROUP}/$id",
                fullPath = path
            )
        )

        return eggGroup.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getEggGroupByPagination(offset: Int, limit: Int): Pagination {
        val eggGroups = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Pokemon.EGG_GROUP,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return eggGroups.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getGenderDetails(id: Int, path: String?): Gender {
        val gender = networkClient.get<Gender>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.GENDER}/$id",
                fullPath = path
            )
        )

        return gender.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getGenderByPagination(offset: Int, limit: Int): Pagination {
        val genders = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Pokemon.GENDER,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return genders.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getGrowthRateDetails(id: Int, path: String?): GrowthRate {
        val growthRate = networkClient.get<GrowthRate>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.GROWTH_RATE}/$id",
                fullPath = path
            )
        )

        return growthRate.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getGrowthRateByPagination(offset: Int, limit: Int): Pagination {
        val growthRates = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Pokemon.GROWTH_RATE,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return growthRates.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getNatureDetails(id: Int, path: String?): Nature {
        val nature = networkClient.get<Nature>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.NATURE}/$id",
                fullPath = path
            )
        )

        return nature.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getNatureByPagination(offset: Int, limit: Int): Pagination {
        val natures = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Pokemon.NATURE,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return natures.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getPokeathlonStatDetails(id: Int, path: String?): PokeathlonStat {
        val pokeathlonStat = networkClient.get<PokeathlonStat>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.POKEATHLON_STAT}/$id",
                fullPath = path
            )
        )

        return pokeathlonStat.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getPokeathlonStatByPagination(offset: Int, limit: Int): Pagination {
        val pokeathlonStats = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Pokemon.POKEATHLON_STAT,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return pokeathlonStats.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getPokemonDetails(id: Int, path: String?): Pokemon {
        val pokemon = networkClient.get<Pokemon>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.POKEMON}/$id",
                fullPath = path
            )
        )

        return pokemon.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getPokemonByPagination(offset: Int, limit: Int): Pagination {
        val pokemons = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Pokemon.POKEMON,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return pokemons.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getLocationAreaDetails(id: Int, path: String?): List<LocationArea> {
        val locationArea = networkClient.get<List<LocationArea>>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.POKEMON}/$id/${Paths.Pokemon.ENCOUNTERS}",
                fullPath = path
            )
        )

        return locationArea.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getColorDetails(id: Int, path: String?): Color {
        val color = networkClient.get<Color>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.POKEMON}-${Paths.Pokemon.COLOR}/$id",
                fullPath = path
            )
        )

        return color.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getColorByPagination(offset: Int, limit: Int): Pagination {
        val colors = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.POKEMON}-${Paths.Pokemon.COLOR}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return colors.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getFormDetails(id: Int, path: String?): Form {
        val form = networkClient.get<Form>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.POKEMON}-${Paths.Pokemon.FORM}/$id",
                fullPath = path
            )
        )

        return form.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getFormByPagination(offset: Int, limit: Int): Pagination {
        val forms = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.POKEMON}-${Paths.Pokemon.FORM}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return forms.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getHabitatDetails(id: Int, path: String?): Habitat {
        val habitat = networkClient.get<Habitat>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.POKEMON}-${Paths.Pokemon.HABITAT}/$id",
                fullPath = path
            )
        )

        return habitat.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getHabitatByPagination(offset: Int, limit: Int): Pagination {
        val habitats = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.POKEMON}-${Paths.Pokemon.HABITAT}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return habitats.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getShapeDetails(id: Int, path: String?): Shape {
        val shape = networkClient.get<Shape>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.POKEMON}-${Paths.Pokemon.SHAPE}/$id",
                fullPath = path
            )
        )

        return shape.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getShapeByPagination(offset: Int, limit: Int): Pagination {
        val shapes = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.POKEMON}-${Paths.Pokemon.SHAPE}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return shapes.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getSpeciesDetails(id: Int, path: String?): Species {
        val specie = networkClient.get<Species>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.POKEMON}-${Paths.Pokemon.SPECIES}/$id",
                fullPath = path
            )
        )

        return specie.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getSpeciesByPagination(offset: Int, limit: Int): Pagination {
        val species = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.POKEMON}-${Paths.Pokemon.SPECIES}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return species.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getStatDetails(id: Int, path: String?): Stat {
        val stat = networkClient.get<Stat>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.STAT}/$id",
                fullPath = path
            )
        )

        return stat.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getStatByPagination(offset: Int, limit: Int): Pagination {
        val stats = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Pokemon.STAT,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return stats.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getTypeDetails(id: Int, path: String?): Type {
        val type = networkClient.get<Type>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Pokemon.TYPE}/$id",
                fullPath = path
            )
        )

        return type.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getTypeByPagination(offset: Int, limit: Int): Pagination {
        val types = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Pokemon.TYPE,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return types.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }
}