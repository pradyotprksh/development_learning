package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.service.ItemService
import com.pradyotprkshpokedex.domain.modal.Attribute
import com.pradyotprkshpokedex.domain.modal.Category
import com.pradyotprkshpokedex.domain.modal.FilingEffect
import com.pradyotprkshpokedex.domain.modal.Item
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.domain.modal.Pocket

class ItemServiceImplementation(private val networkClient: NetworkClient) : ItemService {
    override suspend fun getItemDetails(id: Int, path: String?): Item {
        TODO("Not yet implemented")
    }

    override suspend fun getItemByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getAttributeDetails(id: Int, path: String?): Attribute {
        TODO("Not yet implemented")
    }

    override suspend fun getAttributeByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryDetails(id: Int, path: String?): Category {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getFilingEffectDetails(id: Int, path: String?): FilingEffect {
        TODO("Not yet implemented")
    }

    override suspend fun getFilingEffectByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getPocketDetails(id: Int, path: String?): Pocket {
        TODO("Not yet implemented")
    }

    override suspend fun getPocketByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }
}