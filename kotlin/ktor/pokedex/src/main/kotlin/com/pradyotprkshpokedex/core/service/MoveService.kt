package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.Ailment
import com.pradyotprkshpokedex.domain.modal.Ailments
import com.pradyotprkshpokedex.domain.modal.BattleStyle
import com.pradyotprkshpokedex.domain.modal.BattleStyles
import com.pradyotprkshpokedex.domain.modal.DamageClass
import com.pradyotprkshpokedex.domain.modal.DamageClasses
import com.pradyotprkshpokedex.domain.modal.LearnMethod
import com.pradyotprkshpokedex.domain.modal.LearnMethods
import com.pradyotprkshpokedex.domain.modal.Move
import com.pradyotprkshpokedex.domain.modal.MoveCategories
import com.pradyotprkshpokedex.domain.modal.MoveCategory
import com.pradyotprkshpokedex.domain.modal.Moves
import com.pradyotprkshpokedex.domain.modal.Target
import com.pradyotprkshpokedex.domain.modal.Targets

interface MoveService {
    suspend fun getMoveDetails(id: Int, path: String? = null): Move
    suspend fun getMoveByPagination(offset: Int, limit: Int): Moves
    suspend fun getAilmentDetails(id: Int, path: String? = null): Ailment
    suspend fun getAilmentByPagination(offset: Int, limit: Int): Ailments
    suspend fun getBattleStyleDetails(id: Int, path: String? = null): BattleStyle
    suspend fun getBattleStyleByPagination(offset: Int, limit: Int): BattleStyles
    suspend fun getCategoryDetails(id: Int, path: String? = null): MoveCategory
    suspend fun getCategoryByPagination(offset: Int, limit: Int): MoveCategories
    suspend fun getDamageClassDetails(id: Int, path: String? = null): DamageClass
    suspend fun getDamageClassByPagination(offset: Int, limit: Int): DamageClasses
    suspend fun getLearnMethodClassDetails(id: Int, path: String? = null): LearnMethod
    suspend fun getLearnMethodByPagination(offset: Int, limit: Int): LearnMethods
    suspend fun getTargetDetails(id: Int, path: String? = null): Target
    suspend fun getTargetByPagination(offset: Int, limit: Int): Targets
}