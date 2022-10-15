package com.project.pradyotprakash.rental.app.pages.welcome.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.core.navigation.path
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.core.services.AppCheckService
import com.project.pradyotprakash.rental.device.services.UserLocalServices
import com.project.pradyotprakash.rental.domain.usecase.AuthenticationUseCase
import com.project.pradyotprakash.rental.domain.usecase.BasicUseCase
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
    private val basicUseCase: BasicUseCase,
    private val authenticationUseCase: AuthenticationUseCase,
    private val appCheckService: AppCheckService,
    private val userLocalServices: UserLocalServices,
) : ViewModel() {
    var userType: UserType =
        UserType.values().find { it.name == userLocalServices.userType } ?: UserType.Unknown

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText

    /**
     * Navigate back
     */
    fun navigateBack() = navigator.navigateBack()

    fun initiateAuthCall(authType: AuthType) {
        viewModelScope.launch {
            appCheckService.getAppCheckToken().collect { appCheckToken ->
                when (appCheckToken) {
                    is RenterResponse.Error -> updateErrorState(appCheckToken.exception.message)
                    is RenterResponse.Idle -> _loading.value = false
                    is RenterResponse.Loading -> _loading.value = true
                    is RenterResponse.Success -> {
                        when (authType) {
                            AuthType.Email -> {
                                // TODO: Give option to create user as well
                                val userType = userLocalServices.userType
                                val email =
                                    if (userType == UserType.Owner.name) "pradyot@gmail.com" else "pradyotRenter@gmail.com"
                                val password =
                                    if (userType == UserType.Owner.name) "pradyot@gmail.com" else "pradyotRenter@gmail.com"
                                basicUseCase.isEmailAddressValid(
                                    emailAddress = email,
                                    appCheckToken = appCheckToken.data
                                ).collect { emailVerificationResponse ->
                                    when (emailVerificationResponse) {
                                        is RenterResponse.Loading -> _loading.value = true
                                        is RenterResponse.Success -> {
                                            authenticationUseCase.signInUserWithEmailPassword(
                                                email,
                                                password,
                                            ).collect { signInResponse ->
                                                when (signInResponse) {
                                                    is RenterResponse.Error -> updateErrorState(
                                                        signInResponse.exception.message
                                                    )
                                                    is RenterResponse.Loading -> _loading.value =
                                                        true
                                                    is RenterResponse.Success<*> -> checkForUserDetails()
                                                    RenterResponse.Idle -> _loading.value = false
                                                }
                                            }
                                        }
                                        is RenterResponse.Error -> updateErrorState(
                                            emailVerificationResponse.exception.message
                                        )
                                        else -> {}
                                    }
                                }
                            }
                            AuthType.Phone -> TODO()
                            AuthType.Google -> TODO()
                        }
                    }
                }
            }
        }
    }

    private suspend fun checkForUserDetails() {
        appCheckService.getAppCheckToken().collect { appCheckToken ->
            when (appCheckToken) {
                is RenterResponse.Error -> updateErrorState(appCheckToken.exception.message)
                is RenterResponse.Idle -> _loading.value = false
                is RenterResponse.Loading -> _loading.value = true
                is RenterResponse.Success -> {
                    authenticationUseCase.getCurrentUserId()?.let { userId ->
                        authenticationUseCase.getCurrentUserDetails(
                            appCheckToken = appCheckToken.data,
                            userId = userId,
                        ).collect { userDetails ->
                            when (userDetails) {
                                is RenterResponse.Error -> {
                                    if (userDetails.exception.isNotFound()) {
                                        goToInformationScreen(
                                            true,
                                        )
                                    } else {
                                        updateErrorState(
                                            userDetails.exception.message
                                        )
                                    }
                                }
                                is RenterResponse.Idle -> _loading.value = false
                                is RenterResponse.Loading -> _loading.value = true
                                is RenterResponse.Success -> {
                                    if (userDetails.data.data?.is_all_details_available == false) {
                                        goToInformationScreen(
                                            false,
                                        )
                                    } else {
                                        goToHomeScreen()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Go to the get information details screen
     */
    private fun goToInformationScreen(firstTimeAddingDetails: Boolean) {
        navigator.navigate {
            it.navigate("${Routes.Information.route}${false}/${false}/$firstTimeAddingDetails")
        }
    }

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    /**
     * Go to the get information details screen
     */
    private fun goToHomeScreen() {
        navigator.navigate {
            it.navigate(Routes.Home.path())
        }
    }
}