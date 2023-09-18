package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.service.MoveService
import com.pradyotprkshpokedex.domain.modal.Ailment
import com.pradyotprkshpokedex.domain.modal.BattleStyle
import com.pradyotprkshpokedex.domain.modal.DamageClass
import com.pradyotprkshpokedex.domain.modal.LearnMethod
import com.pradyotprkshpokedex.domain.modal.Move
import com.pradyotprkshpokedex.domain.modal.MoveCategory
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.domain.modal.Target

class MoveServiceImplementation(private val networkClient: NetworkClient) : MoveService {
    override suspend fun getMoveDetails(id: Int, path: String?): Move {
        TODO("Not yet implemented")
    }

    override suspend fun getMoveByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getAilmentDetails(id: Int, path: String?): Ailment {
        TODO("Not yet implemented")
    }

    override suspend fun getAilmentByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getBattleStyleDetails(id: Int, path: String?): BattleStyle {
        TODO("Not yet implemented")
    }

    override suspend fun getBattleStyleByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryDetails(id: Int, path: String?): MoveCategory {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getDamageClassDetails(id: Int, path: String?): DamageClass {
        TODO("Not yet implemented")
    }

    override suspend fun getDamageClassByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getLearnMethodClassDetails(id: Int, path: String?): LearnMethod {
        TODO("Not yet implemented")
    }

    override suspend fun getLearnMethodByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getTargetDetails(id: Int, path: String?): Target {
        TODO("Not yet implemented")
    }

    override suspend fun getTargetByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }
}