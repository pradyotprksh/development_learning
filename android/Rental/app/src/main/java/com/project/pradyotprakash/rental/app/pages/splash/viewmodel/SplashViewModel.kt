package com.project.pradyotprakash.rental.app.pages.splash.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.core.auth.AuthState
import com.project.pradyotprakash.rental.core.auth.AuthStateListener
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.core.navigation.path
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.usecase.AuthenticationUseCase
import com.project.pradyotprakash.rental.domain.usecase.BasicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The view model class for the splash screen
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val basicUseCase: BasicUseCase,
    private val navigator: Navigator,
    private val authenticationUseCase: AuthenticationUseCase,
    private val authStateListener: AuthStateListener,
) : ViewModel() {
    init {
        checkApiCalls()
    }

    /**
     * Check if the app server is running
     */
    private fun checkApiCalls() {
        viewModelScope.launch {
            basicUseCase.getDetails()
                .let {
                    when (it) {
                        is RenterResponse.Success -> {
                            if (authenticationUseCase.isUserLoggedIn()) {
                                delay(2000)
                            } else {
                                delay(2000)
                                navigator.navigate { navController ->
                                    navController.navigate(Routes.Option.path()) {
                                        popUpTo(Routes.Splash.path()) {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                        }
                        is RenterResponse.Error -> {
                            Log.d("error", it.exception.toString())
                        }
                        else -> {}
                    }
                }
        }
    }

    fun checkIfUserIsPresent() {
        if (authenticationUseCase.isUserLoggedIn()) {
            authStateListener.stateChange(AuthState.Authenticated)
        } else {
            authStateListener.stateChange(AuthState.Unauthenticated)
        }
    }
}