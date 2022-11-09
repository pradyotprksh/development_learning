package com.project.pradyotprakash.rental.core.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.device.services.UserLocalServices
import com.project.pradyotprakash.rental.domain.modal.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A list of states for the authentication in the current application.
 */
enum class AuthState {
    Authenticated,
    Unauthenticated,
    Idle,
}

/**
 * A listener class for authentication which will also helps in updating and
 * getting the current state.
 */
@Singleton
class AuthStateListener @Inject constructor(
    private val userLocalServices: UserLocalServices,
) {
    private val _authState = MutableLiveData(AuthState.Idle)
    val authState: LiveData<AuthState>
        get() = _authState

    private val _userDetails = MutableLiveData<UserEntity?>()
    val userDetails: LiveData<UserEntity?>
        get() = _userDetails

    fun updateUserDetails(userDetails: UserEntity? = null) {
        userLocalServices.saveSelectedUserType(userDetails?.user_type ?: UserType.Unknown.name)
        _userDetails.postValue(userDetails)
    }

    /**
     * Navigate to a certain page
     */
    fun stateChange(authState: AuthState) {
        if (authState == AuthState.Unauthenticated) {
            updateUserDetails(null)
        }
        _authState.postValue(authState)
    }
}