package core.repository

import domain.modal.NameUrl

interface PokemonRepository {
    suspend fun getAllPokemonImages(): List<NameUrl>
}