package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.Generation
import com.pradyotprkshpokedex.domain.modal.Generations
import com.pradyotprkshpokedex.domain.modal.Pokedex
import com.pradyotprkshpokedex.domain.modal.Pokedexes
import com.pradyotprkshpokedex.domain.modal.Version
import com.pradyotprkshpokedex.domain.modal.VersionGroup
import com.pradyotprkshpokedex.domain.modal.VersionGroups
import com.pradyotprkshpokedex.domain.modal.Versions

interface GameService {
    suspend fun getGenerationDetails(id: Int, path: String? = null): Generation
    suspend fun getGenerationByPagination(offset: Int, limit: Int): Generations
    suspend fun getPokedexDetails(id: Int, path: String? = null): Pokedex
    suspend fun getPokedexByPagination(offset: Int, limit: Int): Pokedexes
    suspend fun getVersionDetails(id: Int, path: String? = null): Version
    suspend fun getVersionByPagination(offset: Int, limit: Int): Versions
    suspend fun getVersionGroupDetails(id: Int, path: String? = null): VersionGroup
    suspend fun getVersionGroupByPagination(offset: Int, limit: Int): VersionGroups
}