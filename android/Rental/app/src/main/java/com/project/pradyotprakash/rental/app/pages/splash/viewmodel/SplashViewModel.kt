package com.project.pradyotprakash.rental.app.pages.splash.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.core.navigation.path
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.repositories.BasicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val basicRepository: BasicRepository,
    private val navigator: Navigator,
) : ViewModel() {
    init {
        checkApiCalls()
    }

    private fun checkApiCalls() {
        viewModelScope.launch {
            basicRepository.getDetails().let {
                when (it) {
                    is RenterResponse.Success -> {
                        delay(2000)
                        navigator.navigate { navController ->
                            navController.navigate(Routes.Option.path()) {
                                popUpTo(Routes.Splash.path()) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                    is RenterResponse.Error -> {
                        Log.d("error", it.exception.toString())
                    }
                    RenterResponse.Loading -> TODO()
                }
            }
        }
    }
}