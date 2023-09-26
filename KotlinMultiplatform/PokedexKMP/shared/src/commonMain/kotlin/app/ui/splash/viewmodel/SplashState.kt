package app.ui.splash.viewmodel

import domain.modal.PokemonImage

sealed interface SplashState {
    data object Init : SplashState
    data class Error(val message: String) : SplashState
    data object Loading : SplashState
    data class PokemonImages(val images: List<PokemonImage> = emptyList()) : SplashState
}