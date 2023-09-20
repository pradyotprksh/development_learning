package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.exception.PokeApiException
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.request.PokeApiRequestDetails
import com.pradyotprkshpokedex.core.service.MoveService
import com.pradyotprkshpokedex.domain.modal.Ailment
import com.pradyotprkshpokedex.domain.modal.BattleStyle
import com.pradyotprkshpokedex.domain.modal.DamageClass
import com.pradyotprkshpokedex.domain.modal.LearnMethod
import com.pradyotprkshpokedex.domain.modal.Move
import com.pradyotprkshpokedex.domain.modal.MoveCategory
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.domain.modal.Target
import com.pradyotprkshpokedex.utils.Paths

class MoveServiceImplementation(private val networkClient: NetworkClient) : MoveService {
    override suspend fun getMoveDetails(id: Int, path: String?): Move {
        val move = networkClient.get<Move>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Moves.MOVE}/$id",
                fullPath = path
            )
        )

        return move.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getMoveByPagination(offset: Int, limit: Int): Pagination {
        val moves = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Moves.MOVE,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return moves.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getAilmentDetails(id: Int, path: String?): Ailment {
        val ailment = networkClient.get<Ailment>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Moves.MOVE}-${Paths.Moves.AILMENT}/$id",
                fullPath = path
            )
        )

        return ailment.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getAilmentByPagination(offset: Int, limit: Int): Pagination {
        val ailments = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Moves.MOVE}-${Paths.Moves.AILMENT}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return ailments.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getBattleStyleDetails(id: Int, path: String?): BattleStyle {
        val battleStyle = networkClient.get<BattleStyle>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Moves.MOVE}-${Paths.Moves.BATTLE_STYLE}/$id",
                fullPath = path
            )
        )

        return battleStyle.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getBattleStyleByPagination(offset: Int, limit: Int): Pagination {
        val battleStyles = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Moves.MOVE}-${Paths.Moves.BATTLE_STYLE}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return battleStyles.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getCategoryDetails(id: Int, path: String?): MoveCategory {
        val category = networkClient.get<MoveCategory>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Moves.MOVE}-${Paths.Moves.CATEGORY}/$id",
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
                endpoint = "${Paths.Moves.MOVE}-${Paths.Moves.CATEGORY}",
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

    override suspend fun getDamageClassDetails(id: Int, path: String?): DamageClass {
        val damageClass = networkClient.get<DamageClass>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Moves.MOVE}-${Paths.Moves.DAMAGE_CLASS}/$id",
                fullPath = path
            )
        )

        return damageClass.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getDamageClassByPagination(offset: Int, limit: Int): Pagination {
        val damageClasses = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Moves.MOVE}-${Paths.Moves.DAMAGE_CLASS}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return damageClasses.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getLearnMethodDetails(id: Int, path: String?): LearnMethod {
        val learnMethod = networkClient.get<LearnMethod>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Moves.MOVE}-${Paths.Moves.LEARN_METHOD}/$id",
                fullPath = path
            )
        )

        return learnMethod.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getLearnMethodByPagination(offset: Int, limit: Int): Pagination {
        val learnMethods = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Moves.MOVE}-${Paths.Moves.LEARN_METHOD}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return learnMethods.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getTargetDetails(id: Int, path: String?): Target {
        val target = networkClient.get<Target>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Moves.MOVE}-${Paths.Moves.TARGET}/$id",
                fullPath = path
            )
        )

        return target.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getTargetByPagination(offset: Int, limit: Int): Pagination {
        val targets = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Moves.MOVE}-${Paths.Moves.TARGET}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return targets.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }
}