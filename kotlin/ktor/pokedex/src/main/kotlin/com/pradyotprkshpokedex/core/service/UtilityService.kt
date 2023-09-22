package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.GithubPokemonImages

interface UtilityService {
    suspend fun getAllPokemonImages(): List<GithubPokemonImages>
}