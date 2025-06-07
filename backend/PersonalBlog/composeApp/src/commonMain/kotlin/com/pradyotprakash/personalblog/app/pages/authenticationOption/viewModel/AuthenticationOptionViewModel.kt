package com.pradyotprakash.personalblog.app.pages.authenticationOption.viewModel

import androidx.lifecycle.ViewModel
import com.pradyotprakash.personalblog.app.pages.authenticationOption.state.AuthenticationOptionState
import com.pradyotprakash.personalblog.app.pages.authenticationOption.state.FieldType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthenticationOptionViewModel : ViewModel() {
    private val _authenticationOptionState = MutableStateFlow(AuthenticationOptionState())
    val authenticationOptionState = _authenticationOptionState.asStateFlow()

    fun onIsAdminCheckChange() {
        _authenticationOptionState.update {
            it.copy(
                isAdmin = !it.isAdmin,
            )
        }
    }

    fun onFieldUpdate(
        value: String,
        fieldType: FieldType,
    ) {
        _authenticationOptionState.update {
            when (fieldType) {
                FieldType.Username -> it.copy(
                    username = value,
                    isAdminError = false,
                )
                FieldType.Password -> it.copy(
                    password = value,
                    isAdminError = false,
                )
            }
        }
    }

    fun checkAuthenticationOption(navigateToHome: (Boolean) -> Unit) {
        if (_authenticationOptionState.value.isAdmin) {
            if (checkAdminCredentials()) {
                navigateToHome(true)
            } else {
                _authenticationOptionState.update {
                    it.copy(
                        isAdminError = true,
                    )
                }
            }
        } else {
            navigateToHome(false)
        }
    }

    private fun checkAdminCredentials() = _authenticationOptionState.value.isFieldsValid &&
            _authenticationOptionState.value.username == "admin" &&
            _authenticationOptionState.value.password == "admin"
}