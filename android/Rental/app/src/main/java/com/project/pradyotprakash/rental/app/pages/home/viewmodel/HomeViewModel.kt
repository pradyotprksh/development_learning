package com.project.pradyotprakash.rental.app.pages.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.core.auth.AuthState
import com.project.pradyotprakash.rental.core.auth.AuthStateListener
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.core.response.RenterResponse
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
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    val userDetails: LiveData<UserEntity>
        get() = authStateListener.userDetails

    init {
        checkForUserDetails()
    }

    private fun checkForUserDetails() {
        viewModelScope.launch {
            authenticationUseCase.getCurrentUserId()?.let { userId ->
                _loading.value = true
                authenticationUseCase.getCurrentUserDetails(userId = userId).let {
                    when (it) {
                        is RenterResponse.Success -> {
                            _loading.value = false
                            it.data.data?.let { userDetails ->
                                authStateListener.updateUserDetails(userDetails)
                                if (!userDetails.is_all_details_available) {
                                    goToInformationScreen(userDetails.user_type)
                                }
                            } ?: kotlin.run {
                                authenticationUseCase.logoutUser()
                                authStateListener.stateChange(AuthState.Unauthenticated)
                                _loading.value = false
                            }
                        }
                        is RenterResponse.Error -> {
                            authenticationUseCase.logoutUser()
                            authStateListener.stateChange(AuthState.Unauthenticated)
                            _loading.value = false
                        }
                        is RenterResponse.Loading -> _loading.value = true
                    }
                }
            }
        }
    }

    /**
     * Go to the get information details screen
     */
    private fun goToInformationScreen(userType: String) {
        navigator.navigate {
            it.navigate("${Routes.Information.route}${userType}/${false}/${false}")
        }
    }

    fun updateErrorState() {
        _errorText.value = ""
    }
}