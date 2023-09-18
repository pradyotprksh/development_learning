package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.Generation
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.domain.modal.Pokedex
import com.pradyotprkshpokedex.domain.modal.Version
import com.pradyotprkshpokedex.domain.modal.VersionGroup

interface GameService {
    suspend fun getGenerationDetails(id: Int, path: String? = null): Generation
    suspend fun getGenerationByPagination(offset: Int, limit: Int): Pagination
    suspend fun getPokedexDetails(id: Int, path: String? = null): Pokedex
    suspend fun getPokedexByPagination(offset: Int, limit: Int): Pagination
    suspend fun getVersionDetails(id: Int, path: String? = null): Version
    suspend fun getVersionByPagination(offset: Int, limit: Int): Pagination
    suspend fun getVersionGroupDetails(id: Int, path: String? = null): VersionGroup
    suspend fun getVersionGroupByPagination(offset: Int, limit: Int): Pagination
}