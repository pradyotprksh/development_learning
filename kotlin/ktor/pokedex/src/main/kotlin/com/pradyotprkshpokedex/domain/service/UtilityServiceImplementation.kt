package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.exception.PokeApiException
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.request.PokeApiRequestDetails
import com.pradyotprkshpokedex.core.service.UtilityService
import com.pradyotprkshpokedex.domain.modal.GithubPokemonImages

class UtilityServiceImplementation(
    private val networkClient: NetworkClient,
): UtilityService {
    override suspend fun getAllPokemonImages(): List<GithubPokemonImages> {
        val images = networkClient.get<List<GithubPokemonImages>>(
            details = PokeApiRequestDetails(
                endpoint = "",
                fullPath = "https://api.github.com/repositories/65623828/contents/sprites/pokemon"
            )
        )

        return images.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while fetching all the images. Please try again.")
        }
    }
}