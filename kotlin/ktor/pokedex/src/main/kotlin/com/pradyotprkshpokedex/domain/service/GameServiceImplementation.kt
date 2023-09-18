package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.service.GameService
import com.pradyotprkshpokedex.domain.modal.Generation
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.domain.modal.Pokedex
import com.pradyotprkshpokedex.domain.modal.Version
import com.pradyotprkshpokedex.domain.modal.VersionGroup

class GameServiceImplementation(private val networkClient: NetworkClient) : GameService {
    override suspend fun getGenerationDetails(id: Int, path: String?): Generation {
        TODO("Not yet implemented")
    }

    override suspend fun getGenerationByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getPokedexDetails(id: Int, path: String?): Pokedex {
        TODO("Not yet implemented")
    }

    override suspend fun getPokedexByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getVersionDetails(id: Int, path: String?): Version {
        TODO("Not yet implemented")
    }

    override suspend fun getVersionByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getVersionGroupDetails(id: Int, path: String?): VersionGroup {
        TODO("Not yet implemented")
    }

    override suspend fun getVersionGroupByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }
}