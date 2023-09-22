package app.ui.splash.viewmodel

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import core.repository.PokemonRepository
import domain.modal.NameUrl
import kotlinx.coroutines.launch

class SplashViewModel(
    private val pokemonRepository: PokemonRepository
): StateScreenModel<SplashViewModel.State>(State.Init) {

    sealed interface State {
        data object Init : State
        data object Loading : State
        data class PokemonImages(val images: List<NameUrl> = emptyList()) : State
    }

    fun getPokemonImages() {
        coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.PokemonImages(images = pokemonRepository.getAllPokemonImages())
        }
    }
}