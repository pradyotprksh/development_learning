package com.project.pradyotprakash.rental.app.pages.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.core.auth.AuthState
import com.project.pradyotprakash.rental.core.auth.AuthStateListener
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.core.services.AppCheckService
import com.project.pradyotprakash.rental.core.utils.Constants
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity
import com.project.pradyotprakash.rental.domain.modal.UserEntity
import com.project.pradyotprakash.rental.domain.usecase.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator,
    private val authenticationUseCase: AuthenticationUseCase,
    private val authStateListener: AuthStateListener,
    private val appCheckService: AppCheckService,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    val userDetails: LiveData<UserEntity>
        get() = authStateListener.userDetails
    private val _properties = MutableLiveData<List<PropertyEntity>>()
    val properties: LiveData<List<PropertyEntity>>
        get() = _properties

    init {
        checkForUserDetails()
    }

    private fun checkForUserDetails() {
        viewModelScope.launch {
            appCheckService.getAppCheckToken().collect { appCheckToken ->
                when (appCheckToken) {
                    is RenterResponse.Error -> updateErrorState(appCheckToken.exception.message)
                    is RenterResponse.Idle -> _loading.value = false
                    is RenterResponse.Loading -> _loading.value = true
                    is RenterResponse.Success -> {
                        authenticationUseCase.getCurrentUserId()?.let { userId ->
                            authenticationUseCase.getCurrentUserDetails(
                                userId = userId,
                                appCheckToken = appCheckToken.data,
                            ).collect {
                                when (it) {
                                    is RenterResponse.Error -> {
                                        if (it.exception.isNotFound()) {
                                            // TODO change current user type to be saved in local storage
                                            goToInformationScreen(
                                                Constants.currentUserType?.name ?: "",
                                                true,
                                            )
                                        } else {
                                            updateErrorState(it.exception.localizedMessage)
                                        }
                                    }
                                    is RenterResponse.Loading -> _loading.value = true
                                    is RenterResponse.Success -> {
                                        it.data.data?.let { userDetails ->
                                            authStateListener.updateUserDetails(userDetails)

                                            if (userDetails.user_type == UserType.Owner.name) {
                                                _properties.value = userDetails.properties ?: emptyList()
                                            } else {
                                               TODO()
                                            }

                                            if (!userDetails.is_all_details_available) {
                                                goToInformationScreen(
                                                    userDetails.user_type,
                                                    false,
                                                )
                                            }
                                        } ?: kotlin.run {
                                            authStateListener.stateChange(AuthState.Unauthenticated)
                                        }
                                    }
                                    is RenterResponse.Idle -> _loading.value = false
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
    private fun goToInformationScreen(userType: String, firstTimeAddingDetails: Boolean) {
        navigator.navigate {
            it.navigate("${Routes.Information.route}${userType}/${false}/${false}/$firstTimeAddingDetails")
        }
    }

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    fun goToAddPropertyScreen() {
        navigator.navigate {
            it.navigate(Routes.Property.route)
        }
    }

    fun navigateToPropertyDetails(propertyId: String) {
        navigator.navigate {
            it.navigate("${Routes.PropertyDetails.route}${propertyId}")
        }
    }
}