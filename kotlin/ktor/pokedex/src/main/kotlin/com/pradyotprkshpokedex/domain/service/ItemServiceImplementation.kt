package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.exception.PokeApiException
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.request.PokeApiRequestDetails
import com.pradyotprkshpokedex.core.service.ItemService
import com.pradyotprkshpokedex.domain.modal.Attribute
import com.pradyotprkshpokedex.domain.modal.Category
import com.pradyotprkshpokedex.domain.modal.FilingEffect
import com.pradyotprkshpokedex.domain.modal.Item
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.domain.modal.Pocket
import com.pradyotprkshpokedex.utils.Paths

class ItemServiceImplementation(private val networkClient: NetworkClient) : ItemService {
    override suspend fun getItemDetails(id: Int, path: String?): Item {
        val item = networkClient.get<Item>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Items.ITEM}/$id",
                fullPath = path
            )
        )

        return item.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getItemByPagination(offset: Int, limit: Int): Pagination {
        val items = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Items.ITEM,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return items.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getAttributeDetails(id: Int, path: String?): Attribute {
        val attribute = networkClient.get<Attribute>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Items.ITEM}-${Paths.Items.ATTRIBUTE}/$id",
                fullPath = path
            )
        )

        return attribute.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getAttributeByPagination(offset: Int, limit: Int): Pagination {
        val attributes = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Items.ITEM}-${Paths.Items.ATTRIBUTE}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return attributes.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getCategoryDetails(id: Int, path: String?): Category {
        val category = networkClient.get<Category>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Items.ITEM}-${Paths.Items.CATEGORY}/$id",
                fullPath = path
            )
        )

        return category.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getCategoryByPagination(offset: Int, limit: Int): Pagination {
        val categories = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Items.ITEM}-${Paths.Items.CATEGORY}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return categories.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getFilingEffectDetails(id: Int, path: String?): FilingEffect {
        val filingEffect = networkClient.get<FilingEffect>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Items.ITEM}-${Paths.Items.FILING_EFFECT}/$id",
                fullPath = path
            )
        )

        return filingEffect.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getFilingEffectByPagination(offset: Int, limit: Int): Pagination {
        val filingEffects = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Items.ITEM}-${Paths.Items.FILING_EFFECT}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return filingEffects.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getPocketDetails(id: Int, path: String?): Pocket {
        val pocket = networkClient.get<Pocket>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Items.ITEM}-${Paths.Items.POCKETS}/$id",
                fullPath = path
            )
        )

        return pocket.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getPocketByPagination(offset: Int, limit: Int): Pagination {
        val pockets = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Items.ITEM}-${Paths.Items.POCKETS}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return pockets.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }
}