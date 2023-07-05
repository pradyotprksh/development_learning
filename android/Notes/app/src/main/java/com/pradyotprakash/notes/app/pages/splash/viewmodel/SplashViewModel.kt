package com.pradyotprakash.notes.app.pages.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.notes.core.navigation.Navigator
import com.pradyotprakash.notes.core.navigation.Routes
import com.pradyotprakash.notes.core.navigation.path
import com.pradyotprakash.notes.domain.usecases.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val navigator: Navigator,
) : ViewModel() {
    init {
        checkIfUserAvailable()
    }

    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText

    fun updateErrorState() {
        _errorText.value = ""
    }

    private fun checkIfUserAvailable() {
        viewModelScope.launch {
            delay(2000)
            if (userUseCase.isUsersAvailable()) {
                navigator.navigate {
                    it.navigate(Routes.LoginUser.path()) {
                        popUpTo(Routes.Splash.path()) {
                            inclusive = true
                        }
                    }
                }
            } else {
                navigator.navigate {
                    it.navigate(Routes.SignUpUser.path()) {
                        popUpTo(Routes.Splash.path()) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}