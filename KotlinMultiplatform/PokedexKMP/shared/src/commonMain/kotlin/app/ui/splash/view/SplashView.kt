package app.ui.splash.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.ui.splash.viewmodel.SplashState
import app.ui.splash.viewmodel.SplashViewModel
import app.utils.Constants
import app.utils.Localization
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

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
            is SplashState.Error -> KamelImage(
                asyncPainterResource(Constants.ERROR_IMAGE_URL),
                contentDescription = currentState.message,
                modifier = Modifier.align(Alignment.Center)
            )
            is SplashState.PokemonImages -> AnimatedVisibility(currentState.images.isNotEmpty()) {
                PokemonImagesContent(currentState.images)
            }
        }
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(all = 10.dp).align(Alignment.BottomCenter)
        ) {
            Text(Localization.SEE_FULL_POKEDEX)
        }
    }
}