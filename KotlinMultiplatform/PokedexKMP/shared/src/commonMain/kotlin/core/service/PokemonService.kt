package core.service

import domain.modal.NameUrl

interface PokemonService {
    suspend fun getPokemonImages(): List<NameUrl>
}