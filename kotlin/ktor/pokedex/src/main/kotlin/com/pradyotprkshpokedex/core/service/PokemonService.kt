package com.pradyotprkshpokedex.core.service

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

interface PokemonService {
    suspend fun getAbilityDetails(id: Int, path: String? = null): Ability
    suspend fun getAbilityByPagination(offset: Int, limit: Int): Pagination
    suspend fun getCharacteristicDetails(id: Int, path: String? = null): Characteristic
    suspend fun getCharacteristicByPagination(offset: Int, limit: Int): Pagination
    suspend fun getEggGroupDetails(id: Int, path: String? = null): EggGroup
    suspend fun getEggGroupByPagination(offset: Int, limit: Int): Pagination
    suspend fun getGenderDetails(id: Int, path: String? = null): Gender
    suspend fun getGenderByPagination(offset: Int, limit: Int): Pagination
    suspend fun getGrowthRateDetails(id: Int, path: String? = null): GrowthRate
    suspend fun getGrowthRateByPagination(offset: Int, limit: Int): Pagination
    suspend fun getNatureDetails(id: Int, path: String? = null): Nature
    suspend fun getNatureByPagination(offset: Int, limit: Int): Pagination
    suspend fun getPokeathlonStatDetails(id: Int, path: String? = null): PokeathlonStat
    suspend fun getPokeathlonStatByPagination(offset: Int, limit: Int): Pagination
    suspend fun getPokemonDetails(id: Int, path: String? = null): Pokemon
    suspend fun getPokemonByPagination(offset: Int, limit: Int): Pagination
    suspend fun getLocationAreaDetails(id: Int, path: String? = null): List<LocationArea>
    suspend fun getColorDetails(id: Int, path: String? = null): Color
    suspend fun getColorByPagination(offset: Int, limit: Int): Pagination
    suspend fun getFormDetails(id: Int, path: String? = null): Form
    suspend fun getFormByPagination(offset: Int, limit: Int): Pagination
    suspend fun getHabitatDetails(id: Int, path: String? = null): Habitat
    suspend fun getHabitatByPagination(offset: Int, limit: Int): Pagination
    suspend fun getShapeDetails(id: Int, path: String? = null): Shape
    suspend fun getShapeByPagination(offset: Int, limit: Int): Pagination
    suspend fun getSpeciesDetails(id: Int, path: String? = null): Species
    suspend fun getSpeciesByPagination(offset: Int, limit: Int): Pagination
    suspend fun getStatDetails(id: Int, path: String? = null): Stat
    suspend fun getStatByPagination(offset: Int, limit: Int): Pagination
    suspend fun getTypeDetails(id: Int, path: String? = null): Type
    suspend fun getTypeByPagination(offset: Int, limit: Int): Pagination
}