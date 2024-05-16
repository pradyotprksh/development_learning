package app.pages.auth.register.viewModel

import androidx.lifecycle.ViewModel
import app.pages.auth.register.state.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import utils.TextFieldType

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
            phoneEmailValue = "",
        )
    }

    fun updateTextField(textFieldType: TextFieldType, value: String) {
        _registerScreenState.value = when (textFieldType) {
            TextFieldType.Name -> registerScreenState.value.copy(
                nameValue = value,
            )

            TextFieldType.PhoneEmail -> registerScreenState.value.copy(
                phoneEmailValue = value,
            )

            TextFieldType.Dob -> registerScreenState.value.copy(
                dobValue = value,
            )
        }
    }
}