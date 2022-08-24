package com.project.pradyotprakash.rental.core.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

/**
 * A listener class for authentication which will also helps in updating and
 * getting the current state.
 */
class AuthStateListener @Inject constructor() {
    private val _authState = MutableLiveData(AuthState.Unauthenticated)
    val authState: LiveData<AuthState>
        get() = _authState

    /**
     * Navigate to a certain page
     */
    fun stateChange(authState: AuthState) {
        _authState.postValue(authState)
    }
}