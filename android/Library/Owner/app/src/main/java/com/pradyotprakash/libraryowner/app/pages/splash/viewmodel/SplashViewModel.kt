package com.pradyotprakash.libraryowner.app.pages.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.libraryowner.app.routes.Routes
import com.pradyotprakash.libraryowner.app.routes.path
import com.pradyotprakash.libraryowner.app.utils.BuildDetails
import com.pradyotprakash.libraryowner.core.navigation.Navigator
import com.pradyotprakash.libraryowner.core.response.OwnerResponse
import com.pradyotprakash.libraryowner.domain.usecases.AuthenticationUseCase
import com.pradyotprakash.libraryowner.domain.usecases.UnsplashUseCase
import com.pradyotprakash.libraryowner.domain.usecases.UserFirestoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val unsplashUseCase: UnsplashUseCase,
    private val authenticationUseCase: AuthenticationUseCase,
    private val navigator: Navigator,
    private val userFirestoreUseCase: UserFirestoreUseCase,
) : ViewModel() {
    private val _backgroundImageUrls = MutableLiveData(emptyList<String>())
    val backgroundImageUrls: LiveData<List<String>>
        get() = _backgroundImageUrls

    fun getBackgroundImage() {
        if (!BuildDetails.IS_DEBUG) {
            viewModelScope.launch {
                unsplashUseCase.getLibraryPortraitImage(count = 50).collect { response ->
                    when (response) {
                        is OwnerResponse.Success -> {
                            val backgroundImages =
                                response.data.results.mapNotNull { it.urls?.full }.shuffled()
                            if (backgroundImages.size > 9) {
                                _backgroundImageUrls.value =
                                    backgroundImages.subList(fromIndex = 0, toIndex = 9)
                            }
                        }

                        else -> {}
                    }

                    goToNextScreen()
                }
            }
        } else {
            goToNextScreen()
        }
    }

    private fun goToNextScreen() {
        viewModelScope.launch {
            delay(2500)

            if (authenticationUseCase.isUserLoggedIn()) {
                checkForUserDetails()
            } else {
                goToWelcomeScreen()
            }
        }
    }

    private fun goToWelcomeScreen() {
        navigator.navigate { navController ->
            navController.navigate(Routes.Welcome.path()) {
                popUpTo(Routes.Splash.path()) {
                    inclusive = true
                }
            }
        }
    }

    private fun checkForUserDetails() {
        authenticationUseCase.getCurrentUserId()?.let { userId ->
            viewModelScope.launch {
                val isUserDetailsAvailable = userFirestoreUseCase.isUserDetailsAvailable(userId)
                if (isUserDetailsAvailable) {
                } else {
                    navigator.navigate { navController ->
                        navController.navigate(Routes.Details.path()) {
                            popUpTo(Routes.Welcome.path()) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        } ?: kotlin.run {
            goToWelcomeScreen()
        }
    }
}