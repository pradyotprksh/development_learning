package app.ui.splash.viewmodel

import core.repository.PokemonRepository
import core.response.PokeApiResponse
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import di.KodeinDI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.kodein.di.instance

class SplashViewModel: ViewModel() {
    private val pokemonRepository: PokemonRepository by KodeinDI.di.instance()

    private val _uiState = MutableStateFlow<SplashState>(SplashState.Init)
    val uiState = _uiState.asStateFlow()

    init {
        getPokemonImages()
    }

    private fun getPokemonImages() {
        viewModelScope.launch {
            _uiState.update { SplashState.Loading }
            pokemonRepository.getAllPokemonImages().collect { res ->
                when (res) {
                    is PokeApiResponse.Error -> _uiState.update { SplashState.Error(message = res.exception.message) }
                    is PokeApiResponse.Loading -> _uiState.update { SplashState.Loading }
                    is PokeApiResponse.Success -> _uiState.update { SplashState.PokemonImages(images = res.data) }
                    else -> {}
                }
            }
        }
    }
}