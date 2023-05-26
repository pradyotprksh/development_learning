package com.pradyotprakash.postscomments.core.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
class AuthStateListener @Inject constructor() {
    private val _authState = MutableLiveData(AuthState.Idle)
    val authState: LiveData<AuthState>
        get() = _authState

    /**
     * Navigate to a certain page
     */
    fun stateChange(authState: AuthState) {
        _authState.postValue(authState)
    }
}