package com.pradyotprakash.findingfalcone.app.pages.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.findingfalcone.core.navigation.Navigator
import com.pradyotprakash.findingfalcone.core.navigation.Routes
import com.pradyotprakash.findingfalcone.core.navigation.path
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {
    fun startJourney() {
        viewModelScope.launch {
            delay(1500)
            navigator.navigate {
                it.navigate(Routes.Selector.path())
            }
        }
    }
}