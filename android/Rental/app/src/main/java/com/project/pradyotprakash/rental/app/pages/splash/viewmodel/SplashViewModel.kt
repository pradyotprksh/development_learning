package com.project.pradyotprakash.rental.app.pages.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.app.utils.UserType
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
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText

    init {
        checkIfUserIsPresent()
        checkApiCalls()
    }

    fun updateErrorState() {
        _errorText.value = ""
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
                            // TODO: Need to redirect user to home if logged in
                            delay(2000)
                            if (authenticationUseCase.isUserLoggedIn()) {
                                navigator.navigate { navController ->
                                    navController.navigate("${Routes.Information.route}${UserType.Owner}/${false}") {
                                        popUpTo(Routes.Splash.path()) {
                                            inclusive = true
                                        }
                                    }
                                }
                            } else {
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
                            _errorText.value = it.exception.message
                        }
                        else -> {}
                    }
                }
        }
    }

    private fun checkIfUserIsPresent() {
        if (authenticationUseCase.isUserLoggedIn()) {
            authStateListener.stateChange(AuthState.Authenticated)
        } else {
            authStateListener.stateChange(AuthState.Unauthenticated)
        }
    }
}