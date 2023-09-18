package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.network.NetworkClient
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

class PokemonServiceImplementation(private val networkClient: NetworkClient) : PokemonService {
    override suspend fun getAbilityDetails(id: Int, path: String?): Ability {
        TODO("Not yet implemented")
    }

    override suspend fun getAbilityByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacteristicDetails(id: Int, path: String?): Characteristic {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacteristicByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getEggGroupDetails(id: Int, path: String?): EggGroup {
        TODO("Not yet implemented")
    }

    override suspend fun getEggGroupByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getGenderDetails(id: Int, path: String?): Gender {
        TODO("Not yet implemented")
    }

    override suspend fun getGenderByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getGrowthRateDetails(id: Int, path: String?): GrowthRate {
        TODO("Not yet implemented")
    }

    override suspend fun getGrowthRateByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getNatureDetails(id: Int, path: String?): Nature {
        TODO("Not yet implemented")
    }

    override suspend fun getNatureByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getPokeathlonStatDetails(id: Int, path: String?): PokeathlonStat {
        TODO("Not yet implemented")
    }

    override suspend fun getPokeathlonStatByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonDetails(id: Int, path: String?): Pokemon {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getLocationAreaDetails(id: Int, path: String?): LocationArea {
        TODO("Not yet implemented")
    }

    override suspend fun getLocationAreaByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getColorDetails(id: Int, path: String?): Color {
        TODO("Not yet implemented")
    }

    override suspend fun getColorByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getFormDetails(id: Int, path: String?): Form {
        TODO("Not yet implemented")
    }

    override suspend fun getFormByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getHabitatDetails(id: Int, path: String?): Habitat {
        TODO("Not yet implemented")
    }

    override suspend fun getHabitatByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getShapeDetails(id: Int, path: String?): Shape {
        TODO("Not yet implemented")
    }

    override suspend fun getShapeByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getSpeciesDetails(id: Int, path: String?): Species {
        TODO("Not yet implemented")
    }

    override suspend fun getSpeciesByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getStatDetails(id: Int, path: String?): Stat {
        TODO("Not yet implemented")
    }

    override suspend fun getStatByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getTypeDetails(id: Int, path: String?): Type {
        TODO("Not yet implemented")
    }

    override suspend fun getTypeByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }
}