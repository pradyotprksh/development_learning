package com.project.pradyotprakash.rental.app.pages.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.app.localization.TR
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
class UserViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
    private val authStateListener: AuthStateListener,
    private val navigator: Navigator,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _userDetails = MutableLiveData<UserEntity>()
    val userDetails: LiveData<UserEntity>
        get() = _userDetails

    fun getUserDetails(userId: String) {
        viewModelScope.launch {
            authenticationUseCase.getCurrentUserDetails(
                userId = userId,
            ).collect {
                when (it) {
                    is RenterResponse.Error -> updateErrorState(it.exception.localizedMessage)
                    is RenterResponse.Loading -> _loading.value = true
                    is RenterResponse.Success -> {
                        it.data.data?.let { userDetails ->
                            authStateListener.updateUserDetails(userDetails)
                            authenticationUseCase.updateUserDetails(
                                userDetails.fullName,
                                userDetails.profile_pic_url,
                            )
                            _userDetails.value = userDetails
                        } ?: kotlin.run {
                            updateErrorState(TR.pleaseTryAgain)
                        }
                    }
                    is RenterResponse.Idle -> _loading.value = false
                }
            }
        }
    }

    /**
     * Go to the get information details screen
     */
    fun goToInformationScreen() {
        navigator.navigate {
            it.navigate("${Routes.Information.route}${!isCurrentUser()}/${true}/${false}")
        }
    }

    /**
     * Navigate back
     */
    fun navigateBack() = navigator.navigateBack()

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    fun floatingActionButtonText(): String {
        return if (isCurrentUser()) {
            TR.edit
        } else {
            ""
        }
    }

    fun isCurrentUser(): Boolean {
        return _userDetails.value?.let { details ->
            authenticationUseCase.getCurrentUserId()?.let { currentUserId ->
                details.user_id == currentUserId
            } ?: kotlin.run {
                false
            }
        } ?: kotlin.run {
            false
        }
    }

    fun logoutUser() {
        authStateListener.stateChange(AuthState.Unauthenticated)
    }

    fun navigateToPropertyDetails(propertyId: String) {
        navigator.navigate {
            it.navigate("${Routes.PropertyDetails.route}${propertyId}")
        }
    }

    fun goToWishlist() {
        navigator.navigate {
            it.navigate(Routes.Wishlist.route)
        }
    }
}