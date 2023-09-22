package app.ui.splash.view

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import app.ui.splash.viewmodel.SplashViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel

class SplashView: Screen {
    @Composable
    override fun Content() {
        val splashViewModel = rememberScreenModel<SplashViewModel>()

        LaunchedEffect(Unit) {
            splashViewModel.getPokemonImages()
        }

        val state by splashViewModel.state.collectAsState()

        when (state) {
            is SplashViewModel.State.Init -> Box(modifier = Modifier)
            is SplashViewModel.State.Loading -> Box(modifier = Modifier)
            is SplashViewModel.State.PokemonImages -> PokemonImagesContent((state as SplashViewModel.State.PokemonImages).images)
        }
    }
}