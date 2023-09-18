package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.Attribute
import com.pradyotprkshpokedex.domain.modal.Category
import com.pradyotprkshpokedex.domain.modal.FilingEffect
import com.pradyotprkshpokedex.domain.modal.Item
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.domain.modal.Pocket

interface ItemService {
    suspend fun getItemDetails(id: Int, path: String? = null): Item
    suspend fun getItemByPagination(offset: Int, limit: Int): Pagination
    suspend fun getAttributeDetails(id: Int, path: String? = null): Attribute
    suspend fun getAttributeByPagination(offset: Int, limit: Int): Pagination
    suspend fun getCategoryDetails(id: Int, path: String? = null): Category
    suspend fun getCategoryByPagination(offset: Int, limit: Int): Pagination
    suspend fun getFilingEffectDetails(id: Int, path: String? = null): FilingEffect
    suspend fun getFilingEffectByPagination(offset: Int, limit: Int): Pagination
    suspend fun getPocketDetails(id: Int, path: String? = null): Pocket
    suspend fun getPocketByPagination(offset: Int, limit: Int): Pagination
}