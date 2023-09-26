package core.service

import domain.modal.PokemonImage

interface PokemonService {
    suspend fun getPokemonImages(): List<PokemonImage>
}