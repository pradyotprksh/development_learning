package com.pradyotprakash.glassbridgegame.app.pages.splash.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    fun goToNextScreen(
        navigateToHome: () -> Unit,
    ) {
        viewModelScope.launch {
            delay(1500)
            navigateToHome()
        }
    }
}