package com.pradyotprakash.personalblog.app.pages.authenticationOption.viewModel

import androidx.lifecycle.ViewModel
import com.pradyotprakash.personalblog.app.pages.authenticationOption.state.AuthenticationOptionState
import com.pradyotprakash.personalblog.app.pages.authenticationOption.state.FieldType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthenticationOptionViewModel : ViewModel() {
    private val _state = MutableStateFlow(AuthenticationOptionState())
    val state = _state.asStateFlow()

    fun onIsAdminCheckChange() {
        _state.update {
            it.copy(
                isAdmin = !it.isAdmin,
            )
        }
    }

    fun onFieldUpdate(
        value: String,
        fieldType: FieldType,
    ) {
        _state.update {
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
        if (_state.value.isAdmin) {
            if (checkAdminCredentials()) {
                navigateToHome(true)
            } else {
                _state.update {
                    it.copy(
                        isAdminError = true,
                    )
                }
            }
        } else {
            navigateToHome(false)
        }
    }

    private fun checkAdminCredentials() = _state.value.isFieldsValid &&
            _state.value.username == "admin" &&
            _state.value.password == "admin"
}