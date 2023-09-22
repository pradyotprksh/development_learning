package domain.repository.pokemon

import core.repository.PokemonRepository
import core.service.PokemonService
import domain.modal.NameUrl

class PokemonRepositoryImplementation(
    private val pokemonService: PokemonService
): PokemonRepository {
    override suspend fun getAllPokemonImages(): List<NameUrl> = pokemonService.getPokemonImages()
}