package com.pradyotprakash.postscomments.app.pages.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.postscomments.core.navigator.Navigator
import com.pradyotprakash.postscomments.core.navigator.Routes
import com.pradyotprakash.postscomments.domain.usecases.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: Navigator,
    private val authenticationUseCase: AuthenticationUseCase,
) : ViewModel() {
    fun startJourney() {
        viewModelScope.launch {
            delay(1500)
            navigator.navigate {
                navigator.navigate {
                    it.popBackStack()
                    if (authenticationUseCase.isUserLoggedIn()) {
                        it.navigate(Routes.Posts.route)
                    } else {
                        it.navigate(Routes.Login.route)
                    }
                }
            }
        }
    }
}