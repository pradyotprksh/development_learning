package app.pages.auth.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.auth.login.state.LoginState
import core.models.response.ClientResponse
import data.request.LoginRequest
import di.ModulesDi
import domain.repositories.user.current.CurrentUserRepository
import domain.repositories.user.verification.UserVerificationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.kodein.di.instance
import utils.Constants.ErrorCode.USER_DETAILS_NOT_FOUND_CODE
import utils.Constants.SuccessCode.EMAIL_PRESENT
import utils.Constants.SuccessCode.PHONE_NUMBER_PRESENT
import utils.Constants.SuccessCode.USERNAME_PRESENT
import utils.PasswordValidation
import utils.TextFieldType
import utils.UtilsMethod
import utils.debounce

class LoginViewModel : ViewModel() {
    private val userVerificationRepository: UserVerificationRepository by ModulesDi.di.instance()
    private val currentUserRepository: CurrentUserRepository by ModulesDi.di.instance()

    private val _loginScreenState = MutableStateFlow(LoginState())
    val loginScreenState = _loginScreenState.asStateFlow()

    fun updateTextField(textFieldType: TextFieldType, value: String) {
        when (textFieldType) {
            TextFieldType.Password -> {
                _loginScreenState.value = _loginScreenState.value.copy(
                    passwordValue = value
                )
                checkPasswordValidity(value)
            }

            TextFieldType.UsernamePhoneEmail -> {
                _loginScreenState.value = _loginScreenState.value.copy(
                    usernamePhoneEmailValue = value, isUsernamePhoneEmailValid = false
                )
                checkForUsernamePhoneEmailValidity(_loginScreenState.value.usernamePhoneEmailValue)
            }

            else -> {}
        }
    }

    private fun checkPasswordValidity(value: String) {
        try {
            UtilsMethod.isValidPassword(value)
            _loginScreenState.value = _loginScreenState.value.copy(
                passwordValidation = PasswordValidation(
                    length = true,
                    uppercase = true,
                    lowercase = true,
                    digit = true,
                    specialCharacter = true,
                )
            )
        } catch (e: Throwable) {
            _loginScreenState.value = _loginScreenState.value.copy(
                passwordValidation = PasswordValidation(
                    length = UtilsMethod.minPasswordLengthValid(value),
                    uppercase = UtilsMethod.passwordContainsAtLeastOneUpperCase(value),
                    lowercase = UtilsMethod.passwordContainsAtLeastOneLowerCase(value),
                    digit = UtilsMethod.passwordContainsAtLeastOneDigit(value),
                    specialCharacter = UtilsMethod.passwordContainsAtLeastOneSpecialCharacters(value),
                )
            )
        }
    }

    private val checkForUsernamePhoneEmailValidity = debounce<String>(
        waitMs = 1000,
        scope = viewModelScope,
    ) {
        if (_loginScreenState.value.usernamePhoneEmailValue.isNotBlank()) {
            viewModelScope.launch {
                userVerificationRepository.isUserPresent(_loginScreenState.value.usernamePhoneEmailValue)
                    .collect {
                        when (it) {
                            is ClientResponse.Error -> {
                                if (it.errorCode == USER_DETAILS_NOT_FOUND_CODE) {
                                    _loginScreenState.value = _loginScreenState.value.copy(
                                        isUsernamePhoneEmailValid = false,
                                    )
                                } else {
                                    showMessage(it.message)
                                }
                            }

                            is ClientResponse.Success -> {
                                _loginScreenState.value = when (it.data.code) {
                                    USERNAME_PRESENT -> _loginScreenState.value.copy(
                                        isUsernamePhoneEmailValid = true,
                                        isUsernameValue = true,
                                    )

                                    EMAIL_PRESENT -> _loginScreenState.value.copy(
                                        isUsernamePhoneEmailValid = true,
                                        isEmailValue = true,
                                    )

                                    PHONE_NUMBER_PRESENT -> _loginScreenState.value.copy(
                                        isUsernamePhoneEmailValid = true,
                                        isPhoneNumberValue = true,
                                    )

                                    else -> _loginScreenState.value
                                }
                            }

                            else -> {}
                        }
                    }
            }
        }
    }

    private fun showMessage(message: String) {
        _loginScreenState.value = _loginScreenState.value.copy(
            snackBarMessage = message,
        )
    }

    fun removeSnackBarMessage() {
        _loginScreenState.value = _loginScreenState.value.copy(
            snackBarMessage = null
        )
    }

    fun loginUser() {
        val loginRequest = LoginRequest(
            username = if (_loginScreenState.value.isUsernameValue) _loginScreenState.value.usernamePhoneEmailValue else null,
            phoneNumber = if (_loginScreenState.value.isPhoneNumberValue) _loginScreenState.value.usernamePhoneEmailValue else null,
            emailAddress = if (_loginScreenState.value.isEmailValue) _loginScreenState.value.usernamePhoneEmailValue else null,
            password = _loginScreenState.value.passwordValue,
        )

        viewModelScope.launch {
            currentUserRepository.loginUser(loginRequest).collect {
                when (it) {
                    is ClientResponse.Error -> showMessage(it.message)
                    ClientResponse.Idle -> updateLoaderState(false)
                    ClientResponse.Loading -> updateLoaderState(true)
                    is ClientResponse.Success -> {
                        it.data.data?.userId?.let { userId ->
                            it.data.data?.token?.let { token ->
                                currentUserRepository.saveUserDetails(
                                    userId,
                                    token,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updateLoaderState(showLoader: Boolean) {
        _loginScreenState.value = _loginScreenState.value.copy(
            showLoading = showLoader
        )
    }
}