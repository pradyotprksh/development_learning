package com.project.pradyotprakash.rental.core.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.pradyotprakash.rental.domain.modal.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A listener class for authentication which will also helps in updating and
 * getting the current state.
 */
@Singleton
class AuthStateListener @Inject constructor() {
    private val _authState = MutableLiveData(AuthState.Unauthenticated)
    val authState: LiveData<AuthState>
        get() = _authState

    private val _userDetails = MutableLiveData<UserEntity>()
    val userDetails: LiveData<UserEntity>
        get() = _userDetails

    fun updateUserDetails(userDetails: UserEntity? = null) {
        _userDetails.value = userDetails
    }

    /**
     * Navigate to a certain page
     */
    fun stateChange(authState: AuthState) {
        if (authState == AuthState.Unauthenticated) {
            updateUserDetails(null)
        }
        _authState.value = authState
    }
}