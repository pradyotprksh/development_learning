package data.pokemon

import core.network.NetworkClient
import core.request.PokeApiRequestDetails
import core.service.PokemonService
import data.utils.Endpoints
import domain.modal.NameUrl

class PokemonDataService(
    private val networkClient: NetworkClient
): PokemonService {
    override suspend fun getPokemonImages(): List<NameUrl> {
        val images = networkClient.get<List<NameUrl>>(
            details = PokeApiRequestDetails(
                endpoint = Endpoints.Utility.ALL_POKEMON_IMAGES
            )
        )

        return images.getOrElse { emptyList() }
    }
}