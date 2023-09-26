package app.ui.splash.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.ui.splash.viewmodel.SplashState
import app.ui.splash.viewmodel.SplashViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun SplashView() {
    val splashViewModel = getViewModel(Unit, viewModelFactory { SplashViewModel() })
    val state by splashViewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (val currentState = state) {
            is SplashState.Init -> Unit
            is SplashState.Loading -> Card(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(20.dp)
                )
            }
            is SplashState.PokemonImages -> AnimatedVisibility(currentState.images.isNotEmpty()) {
                PokemonImagesContent(currentState.images)
            }
        }
    }
}