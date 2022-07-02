package com.project.pradyotprakash.twitter.splash.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.twitter.navigator.Navigator
import com.project.pradyotprakash.twitter.navigator.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: Navigator
): ViewModel() {
    private var isNavigationStarted: Boolean = false

    fun navigateToAuthOptionScreen() {
        if (!isNavigationStarted) {
            isNavigationStarted = true
            navigator.navigate {
                it.popBackStack()
                it.navigate(Routes.AuthOption.route)
            }
        }
    }
}