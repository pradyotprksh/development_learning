package com.pradyotprakash.libraryowner.app.pages.welcome.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.pradyotprakash.libraryowner.app.localization.TR
import com.pradyotprakash.libraryowner.app.routes.Routes
import com.pradyotprakash.libraryowner.app.routes.path
import com.pradyotprakash.libraryowner.core.navigation.Navigator
import com.pradyotprakash.libraryowner.domain.usecases.AuthenticationUseCase
import com.pradyotprakash.libraryowner.domain.usecases.UserFirestoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val userFirestoreUseCase: UserFirestoreUseCase,
    private val authenticationUseCase: AuthenticationUseCase,
    private val navigator: Navigator,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    fun firebaseAuthResponse(result: FirebaseAuthUIAuthenticationResult?) {
        result?.let {
            val response = result.idpResponse
            if (result.resultCode == Activity.RESULT_OK) {
                _loading.value = true
                checkForUserDetails()
            } else {
                updateErrorState(response?.error?.localizedMessage ?: "")
            }
        }
    }

    private fun checkForUserDetails() {
        authenticationUseCase.getCurrentUserId()?.let { userId ->
            viewModelScope.launch {
                val isUserDetailsAvailable = userFirestoreUseCase.isUserDetailsAvailable(userId)
                _loading.value = false
                if (isUserDetailsAvailable) {} else {
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
            updateErrorState(TR.loginError)
        }
    }
}