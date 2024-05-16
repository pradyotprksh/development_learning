package app.pages.auth.register.viewModel

import androidx.lifecycle.ViewModel
import app.pages.auth.register.state.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import utils.Constants.ConstValues.NAME_MAX_LENGTH
import utils.TextFieldType
import utils.UtilsMethod

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
            TextFieldType.Name -> {
                if (value.length <= NAME_MAX_LENGTH) {
                    registerScreenState.value.copy(
                        nameValue = value,
                    )
                } else {
                    registerScreenState.value
                }
            }

            TextFieldType.PhoneEmail -> registerScreenState.value.copy(
                phoneEmailValue = value,
            )

            TextFieldType.Dob -> registerScreenState.value.copy(
                dobValue = value,
            )
        }
    }

    fun focusedChangeForDob() {
        _registerScreenState.value = registerScreenState.value.copy(
            datePickerVisible = !registerScreenState.value.datePickerVisible,
        )
    }

    fun updateSelectedDate(date: Long) {
        try {
            val value = UtilsMethod.convertLongToReadableDate(date)
            _registerScreenState.value = registerScreenState.value.copy(
                dobValue = value,
                dobValueLong = date,
            )
        } catch (e: Exception) {
            _registerScreenState.value = registerScreenState.value.copy(
                dobValue = "",
                dobValueLong = 0,
            )
        }
    }
}