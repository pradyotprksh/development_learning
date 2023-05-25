package com.pradyotprakash.postscomments.app.pages.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.postscomments.core.navigator.Navigator
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

            }
        }
    }
}