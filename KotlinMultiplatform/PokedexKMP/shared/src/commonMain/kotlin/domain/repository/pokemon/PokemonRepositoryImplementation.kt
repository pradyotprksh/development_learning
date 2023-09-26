package domain.repository.pokemon

import core.exception.PokeApiException
import core.repository.PokemonRepository
import core.response.PokeApiResponse
import core.service.PokemonService
import domain.modal.PokemonImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImplementation(
    private val pokemonService: PokemonService
): PokemonRepository {
    override suspend fun getAllPokemonImages(): Flow<PokeApiResponse<out List<PokemonImage>>> = flow {
        emit(PokeApiResponse.Loading)
        try {
            emit(PokeApiResponse.Success(pokemonService.getPokemonImages()))
        } catch (e: Exception) {
            emit(PokeApiResponse.Error(exception = PokeApiException(message = e.message ?: "")))
        }
        emit(PokeApiResponse.Idle)
    }
}