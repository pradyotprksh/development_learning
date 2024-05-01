package ui.pages.splash.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import ui.pages.splash.viewModel.SplashScreenViewModel

@Composable
fun SplashScreen(
    splashScreenViewModel: SplashScreenViewModel = viewModel { SplashScreenViewModel() }
) {
}