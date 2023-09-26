package data.pokemon

import core.network.NetworkClient
import core.request.PokeApiRequestDetails
import core.service.PokemonService
import data.utils.Endpoints
import domain.modal.PokemonImage

class PokemonDataService(
    private val networkClient: NetworkClient
): PokemonService {
    override suspend fun getPokemonImages(): List<PokemonImage> {
        val images = networkClient.get<List<PokemonImage>>(
            details = PokeApiRequestDetails(
                endpoint = Endpoints.Utility.ALL_POKEMON_IMAGES
            )
        )

        return images.getOrThrow()
    }
}