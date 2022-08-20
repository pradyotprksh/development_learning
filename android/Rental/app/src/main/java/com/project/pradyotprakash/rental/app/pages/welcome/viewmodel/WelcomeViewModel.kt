package com.project.pradyotprakash.rental.app.pages.welcome.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.usecase.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class AuthType {
    Email,
    Phone,
    Google,
}

/**
 * The view model class which will handle the business logic.
 */
@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val navigator: Navigator,
    private val authenticationUseCase: AuthenticationUseCase,
) : ViewModel() {
    lateinit var userType: UserType

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText

    /**
     * Set the initial value of the view model
     */
    fun start(userType: UserType) {
        this.userType = userType
    }

    /**
     * Navigate back
     */
    fun navigateBack() = navigator.navigateBack()

    fun initiateAuthCall(authType: AuthType) {
        _loading.value = true
        when (authType) {
            AuthType.Email -> {
                // TODO: Give option to create user as well
                viewModelScope.launch {
                    authenticationUseCase.signInUserWithEmailPassword(
                        "pradyot@gmail.com",
                        "pradyot@gmail.com"
                    ) {
                        when (it) {
                            is RenterResponse.Error -> {
                                _loading.value = false
                                _errorText.value = it.exception.message
                            }
                            is RenterResponse.Loading -> _loading.value = true
                            is RenterResponse.Success -> {
                                _loading.value = false
                                goToInformationScreen()
                            }
                            null -> _loading.value = false
                        }
                    }
                }
            }
            AuthType.Phone -> {}
            AuthType.Google -> {}
        }
    }

    fun updateErrorState() {
        _errorText.value = ""
    }

    /**
     * Go to the get information details screen
     */
    private fun goToInformationScreen() {
        navigator.navigate {
            it.navigate("${Routes.Information.route}${userType}/${false}")
        }
    }
}