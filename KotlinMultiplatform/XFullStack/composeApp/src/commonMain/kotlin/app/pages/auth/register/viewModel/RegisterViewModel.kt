package app.pages.auth.register.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.auth.register.state.RegisterState
import core.models.response.ClientResponse
import di.ModulesDi
import domain.repositories.user.verification.UserVerificationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.kodein.di.instance
import utils.Constants.ConstValues.NAME_MAX_LENGTH
import utils.Constants.ErrorCode.USER_DETAILS_NOT_FOUND_CODE
import utils.TextFieldType
import utils.UtilsMethod
import utils.debounce

class RegisterViewModel : ViewModel() {
    private val userVerificationRepository: UserVerificationRepository by ModulesDi.di.instance()

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

    fun updateTextField(textFieldType: TextFieldType, value: String) {
        when (textFieldType) {
            TextFieldType.Name -> {
                if (value.length <= NAME_MAX_LENGTH) {
                    _registerScreenState.value = _registerScreenState.value.copy(
                        nameValue = value, isNameValid = false
                    )
                }
                checkForNameValidity(_registerScreenState.value.nameValue)
            }

            TextFieldType.PhoneEmail -> {
                _registerScreenState.value = _registerScreenState.value.copy(
                    phoneEmailValue = value, isPhoneEmailValid = false, showPhoneNumberError = false
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
                dobValueTimestamp = date,
            )
        } catch (e: Exception) {
            _registerScreenState.value = _registerScreenState.value.copy(
                dobValue = "",
                dobValueTimestamp = 0,
            )
        }
    }

    fun checkForDetails(
        navigateToLogin: (String) -> Unit,
    ) {
        viewModelScope.launch {
            userVerificationRepository.isUserPresent(_registerScreenState.value.phoneEmailValue)
                .collect {
                    when (it) {
                        is ClientResponse.Error -> onUserPresentError(
                            it,
                        )

                        ClientResponse.Idle -> updateLoaderState(showLoader = false)
                        ClientResponse.Loading -> updateLoaderState(showLoader = true)
                        is ClientResponse.Success -> onUserPresentSuccess(navigateToLogin)
                    }
                }
        }
    }

    private fun onUserPresentSuccess(
        navigateToLogin: (String) -> Unit
    ) {
        navigateToLogin(_registerScreenState.value.phoneEmailValue)
    }

    private fun onUserPresentError(
        error: ClientResponse.Error,
    ) {
        if (error.errorCode == USER_DETAILS_NOT_FOUND_CODE) {
            generateOtp()
        } else {
            _registerScreenState.value = _registerScreenState.value.copy(
                errorMessage = error.message,
            )
        }
    }

    private fun generateOtp() {
        _registerScreenState.value = _registerScreenState.value.copy(
            showOtpOption = true,
            useEmailOrPhoneState = false,
            datePickerVisible = false,
            showLoading = false,
            errorMessage = null,
        )
    }

    private fun updateLoaderState(showLoader: Boolean) {
        _registerScreenState.value = _registerScreenState.value.copy(
            showLoading = showLoader
        )
    }

    fun removeErrorMessage() {
        _registerScreenState.value = _registerScreenState.value.copy(
            errorMessage = null
        )
    }
}