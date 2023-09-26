package app.ui.splash.viewmodel

import domain.modal.NameUrl

sealed interface SplashState {
    data object Init : SplashState
    data object Loading : SplashState
    data class PokemonImages(val images: List<NameUrl> = emptyList()) : SplashState
}