package com.project.pradyotprakash.rental.core.auth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * A listener class for authentication which will also helps in updating and
 * getting the current state.
 */
class AuthStateListener @Inject constructor() {
    private val _authState = MutableStateFlow(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState> = _authState

    /**
     * Navigate to a certain page
     */
    fun stateChange(authState: AuthState) {
        _authState.value = authState
    }
}