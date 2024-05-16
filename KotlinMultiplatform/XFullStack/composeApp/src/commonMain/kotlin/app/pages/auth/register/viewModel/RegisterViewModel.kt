package app.pages.auth.register.viewModel

import androidx.lifecycle.ViewModel
import app.pages.auth.register.state.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel : ViewModel() {
    private val _registerScreenState = MutableStateFlow(RegisterState())
    val registerScreenState = _registerScreenState.asStateFlow()

    fun focusedChangeForPhoneEmail() {
        _registerScreenState.value = registerScreenState.value.copy(
            useEmailOrPhoneState = !registerScreenState.value.useEmailOrPhoneState,
        )
    }

    fun updateUseEmailOrPhone() {
        _registerScreenState.value = registerScreenState.value.copy(
            isUsingPhoneNumber = !registerScreenState.value.isUsingPhoneNumber,
        )
    }
}