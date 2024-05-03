package ui.pages.splash.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class SplashViewModel : ViewModel() {
    suspend fun navigateToAuthOption(navigate: () -> Unit) {
        delay(3000)
        navigate()
    }
}