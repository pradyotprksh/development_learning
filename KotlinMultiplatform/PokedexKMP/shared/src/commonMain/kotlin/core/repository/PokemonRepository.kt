package core.repository

import core.response.PokeApiResponse
import domain.modal.PokemonImage
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getAllPokemonImages(): Flow<PokeApiResponse<out List<PokemonImage>>>
}