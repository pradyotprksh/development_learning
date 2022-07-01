package com.project.pradyotprakash.twitter.splash.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.twitter.navigator.Navigator
import com.project.pradyotprakash.twitter.navigator.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: Navigator
): ViewModel() {

    fun navigateToAuthOptionScreen() {
        viewModelScope.launch {
            delay(5000)
            navigator.navigate {
                it.navigate(Routes.AuthOption.route)
            }
        }
    }
}