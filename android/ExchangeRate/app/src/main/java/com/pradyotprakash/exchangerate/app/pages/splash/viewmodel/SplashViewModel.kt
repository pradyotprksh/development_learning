package com.pradyotprakash.exchangerate.app.pages.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.exchangerate.app.routes.Routes
import com.pradyotprakash.exchangerate.core.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {
    init {
        redirectToNextScreen()
    }

    private fun redirectToNextScreen() {
        viewModelScope.launch {
            delay(3000)
            navigator.navigate { navController ->
                navController.navigate(Routes.Home.route) {
                    popUpTo(Routes.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}