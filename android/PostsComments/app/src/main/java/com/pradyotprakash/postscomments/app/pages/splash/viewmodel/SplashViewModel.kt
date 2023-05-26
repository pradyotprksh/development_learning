package com.pradyotprakash.postscomments.app.pages.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.postscomments.core.auth.AuthState
import com.pradyotprakash.postscomments.core.auth.AuthStateListener
import com.pradyotprakash.postscomments.domain.usecases.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authStateListener: AuthStateListener,
    private val authenticationUseCase: AuthenticationUseCase,
) : ViewModel() {
    fun startJourney() {
        viewModelScope.launch {
            delay(1500)
            if (authenticationUseCase.isUserLoggedIn()) {
                authStateListener.stateChange(AuthState.Authenticated)
            } else {
                authStateListener.stateChange(AuthState.Unauthenticated)
            }
        }
    }
}