package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.Attribute
import com.pradyotprkshpokedex.domain.modal.Attributes
import com.pradyotprkshpokedex.domain.modal.Categories
import com.pradyotprkshpokedex.domain.modal.Category
import com.pradyotprkshpokedex.domain.modal.FilingEffect
import com.pradyotprkshpokedex.domain.modal.FilingEffects
import com.pradyotprkshpokedex.domain.modal.Item
import com.pradyotprkshpokedex.domain.modal.Items
import com.pradyotprkshpokedex.domain.modal.Pocket
import com.pradyotprkshpokedex.domain.modal.Pockets

interface ItemService {
    suspend fun getItemDetails(id: Int, path: String? = null): Item
    suspend fun getItemByPagination(offset: Int, limit: Int): Items
    suspend fun getAttributeDetails(id: Int, path: String? = null): Attribute
    suspend fun getAttributeByPagination(offset: Int, limit: Int): Attributes
    suspend fun getCategoryDetails(id: Int, path: String? = null): Category
    suspend fun getCategoryByPagination(offset: Int, limit: Int): Categories
    suspend fun getFilingEffectDetails(id: Int, path: String? = null): FilingEffect
    suspend fun getFilingEffectByPagination(offset: Int, limit: Int): FilingEffects
    suspend fun getPocketDetails(id: Int, path: String? = null): Pocket
    suspend fun getPocketByPagination(offset: Int, limit: Int): Pockets
}