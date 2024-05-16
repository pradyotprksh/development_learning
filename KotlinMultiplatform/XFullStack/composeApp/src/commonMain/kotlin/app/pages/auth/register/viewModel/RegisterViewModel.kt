package app.pages.auth.register.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.auth.register.state.RegisterState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import utils.Constants.ConstValues.NAME_MAX_LENGTH
import utils.TextFieldType
import utils.UtilsMethod
import utils.debounce

class RegisterViewModel : ViewModel() {
    private val _registerScreenState = MutableStateFlow(RegisterState())
    val registerScreenState = _registerScreenState.asStateFlow()

    fun focusedChangeForPhoneEmail() {
        _registerScreenState.value = _registerScreenState.value.copy(
            useEmailOrPhoneState = !_registerScreenState.value.useEmailOrPhoneState,
        )
    }

    fun updateUseEmailOrPhone() {
        _registerScreenState.value = _registerScreenState.value.copy(
            isUsingPhoneNumber = !_registerScreenState.value.isUsingPhoneNumber,
            phoneEmailValue = "",
            isPhoneEmailValid = false,
            showPhoneNumberError = false
        )
    }

    @OptIn(FlowPreview::class)
    fun updateTextField(textFieldType: TextFieldType, value: String) {
        when (textFieldType) {
            TextFieldType.Name -> {
                if (value.length <= NAME_MAX_LENGTH) {
                    _registerScreenState.value = _registerScreenState.value.copy(
                        nameValue = value,
                        isNameValid = false
                    )
                }
                checkForNameValidity(_registerScreenState.value.nameValue)
            }

            TextFieldType.PhoneEmail -> {
                _registerScreenState.value = _registerScreenState.value.copy(
                    phoneEmailValue = value,
                    isPhoneEmailValid = false,
                    showPhoneNumberError = false
                )
                checkForEmailPhoneValidity(_registerScreenState.value.phoneEmailValue)
            }

            TextFieldType.Dob -> {
                _registerScreenState.value = _registerScreenState.value.copy(
                    dobValue = value,
                )
            }
        }
    }

    private val checkForNameValidity = debounce<String>(
        waitMs = 1000,
        scope = viewModelScope,
    ) {
        if (_registerScreenState.value.nameValue.isNotBlank()) {
            _registerScreenState.value = _registerScreenState.value.copy(
                isNameValid = try {
                    UtilsMethod.isValidName(it)
                } catch (e: Throwable) {
                    false
                }
            )
        }
    }

    private val checkForEmailPhoneValidity = debounce<String>(
        waitMs = 1000,
        scope = viewModelScope,
    ) {
        if (_registerScreenState.value.phoneEmailValue.isNotBlank()) {
            val isValid = try {
                if (_registerScreenState.value.isUsingPhoneNumber) {
                    UtilsMethod.isValidPhoneNumber(it)
                } else {
                    UtilsMethod.isValidEmail(it)
                }
            } catch (e: Throwable) {
                false
            }
            _registerScreenState.value = _registerScreenState.value.copy(
                isPhoneEmailValid = isValid,
                showPhoneNumberError = !isValid,
            )
        }
    }

    fun focusedChangeForDob() {
        _registerScreenState.value = _registerScreenState.value.copy(
            datePickerVisible = !_registerScreenState.value.datePickerVisible,
        )
    }

    fun updateSelectedDate(date: Long) {
        try {
            val value = UtilsMethod.convertLongToReadableDate(date)
            _registerScreenState.value = _registerScreenState.value.copy(
                dobValue = value,
                dobValueLong = date,
            )
        } catch (e: Exception) {
            _registerScreenState.value = _registerScreenState.value.copy(
                dobValue = "",
                dobValueLong = 0,
            )
        }
    }
}