package app.pages.auth.register.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.auth.register.state.RegisterState
import core.models.request.RegisterRequest
import core.models.response.ClientResponse
import di.SharedModulesDi
import domain.repositories.user.current.CurrentUserRepository
import domain.repositories.user.verification.UserVerificationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import utils.Constants.ConstValues.NAME_MAX_LENGTH
import utils.Constants.ConstValues.OTP_LENGTH
import utils.Constants.ErrorCode.USERNAME_ALREADY_PRESENT_ERROR_CODE
import utils.Constants.ErrorCode.USERNAME_VALIDITY_ERROR_CODE
import utils.Constants.ErrorCode.USER_DETAILS_NOT_FOUND_CODE
import utils.Logger
import utils.LoggerLevel
import utils.PasswordValidation
import utils.TextFieldType
import utils.UtilsMethod
import utils.extensions.debounce

class RegisterViewModel(
    private val userVerificationRepository: UserVerificationRepository = SharedModulesDi.Instance.userVerificationRepository,
    private val currentUserRepository: CurrentUserRepository = SharedModulesDi.Instance.currentUserRepository,
) : ViewModel() {
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

            TextFieldType.Otp -> {
                if (value.length <= OTP_LENGTH) {
                    _registerScreenState.value = _registerScreenState.value.copy(
                        otpValue = value
                    )
                }
            }

            TextFieldType.Password -> {
                _registerScreenState.value = _registerScreenState.value.copy(
                    passwordValue = value
                )
                checkPasswordValidity(value)
            }

            TextFieldType.ConfirmPassword -> {
                _registerScreenState.value = _registerScreenState.value.copy(
                    confirmPasswordValue = value
                )
            }

            TextFieldType.Username -> {
                _registerScreenState.value = _registerScreenState.value.copy(
                    usernameValue = value, isUsernameValid = false
                )
                checkForUsernameValidity(_registerScreenState.value.usernameValue)
            }

            TextFieldType.Bio -> {
                _registerScreenState.value = _registerScreenState.value.copy(
                    bioValue = value.ifBlank { null }, isBioValid = false
                )
                checkForBioValidity(_registerScreenState.value.bioValue ?: "")
            }

            else -> {}
        }
    }

    private fun checkPasswordValidity(value: String) {
        try {
            UtilsMethod.Validation.isValidPassword(value)
            _registerScreenState.value = _registerScreenState.value.copy(
                showConfirmPassword = true, passwordValidation = PasswordValidation(
                    length = true,
                    uppercase = true,
                    lowercase = true,
                    digit = true,
                    specialCharacter = true,
                )
            )
        } catch (e: Throwable) {
            _registerScreenState.value = _registerScreenState.value.copy(
                showConfirmPassword = false, passwordValidation = PasswordValidation(
                    length = UtilsMethod.Validation.minPasswordLengthValid(value),
                    uppercase = UtilsMethod.Validation.passwordContainsAtLeastOneUpperCase(value),
                    lowercase = UtilsMethod.Validation.passwordContainsAtLeastOneLowerCase(value),
                    digit = UtilsMethod.Validation.passwordContainsAtLeastOneDigit(value),
                    specialCharacter = UtilsMethod.Validation.passwordContainsAtLeastOneSpecialCharacters(
                        value
                    ),
                )
            )
        }
    }

    private val checkForNameValidity = debounce<String>(
        waitMs = 1000,
        scope = viewModelScope,
    ) {
        if (_registerScreenState.value.nameValue.isNotBlank()) {
            _registerScreenState.value = _registerScreenState.value.copy(
                isNameValid = try {
                    UtilsMethod.Validation.isValidName(it)
                } catch (e: Throwable) {
                    false
                }
            )
        }
    }

    private val checkForBioValidity = debounce<String>(
        waitMs = 1000,
        scope = viewModelScope,
    ) {
        if (_registerScreenState.value.bioValue?.isNotBlank() == true) {
            _registerScreenState.value = _registerScreenState.value.copy(
                isBioValid = try {
                    UtilsMethod.Validation.isValidBio(it)
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
                    UtilsMethod.Validation.isValidPhoneNumber(it)
                } else {
                    UtilsMethod.Validation.isValidEmail(it)
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

    private val checkForUsernameValidity = debounce<String>(
        waitMs = 1000,
        scope = viewModelScope,
    ) {
        if (_registerScreenState.value.usernameValue.isNotBlank()) {
            viewModelScope.launch {
                userVerificationRepository.isUsernameValid(_registerScreenState.value.usernameValue)
                    .collect {
                        when (it) {
                            is ClientResponse.Error -> {
                                if (it.errorCode == USERNAME_ALREADY_PRESENT_ERROR_CODE || it.errorCode == USERNAME_VALIDITY_ERROR_CODE) {
                                    _registerScreenState.value = _registerScreenState.value.copy(
                                        isUsernameValid = false,
                                    )
                                } else {
                                    showMessage(it.message)
                                }
                            }

                            is ClientResponse.Success -> _registerScreenState.value =
                                _registerScreenState.value.copy(
                                    isUsernameValid = true,
                                )

                            else -> {}
                        }
                    }
            }
        }
    }

    fun focusedChangeForDob() {
        _registerScreenState.value = _registerScreenState.value.copy(
            datePickerVisible = !_registerScreenState.value.datePickerVisible,
        )
    }

    fun updateSelectedDate(date: Long) {
        try {
            val value = UtilsMethod.Dates.convertLongToReadableDate(date)
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
        navigateToLogin: ((String) -> Unit)? = null,
    ) {
        viewModelScope.launch {
            if (_registerScreenState.value.otpForm) {
                performOTPOperations()
            } else if (_registerScreenState.value.passwordForm) {
                performPasswordOperations()
            } else if (_registerScreenState.value.usernameProfileImageForm) {
                performCreateAccountOperations(navigateToLogin)
            } else {
                performRegisterFormOperations(navigateToLogin)
            }
        }
    }

    private fun performPasswordOperations() {
        passwordDone()
    }

    private suspend fun performCreateAccountOperations(navigateToLogin: ((String) -> Unit)?) {
        val registerRequest = RegisterRequest(
            name = _registerScreenState.value.nameValue,
            username = _registerScreenState.value.usernameValue,
            password = _registerScreenState.value.passwordValue,
            bio = if (_registerScreenState.value.isBioValid) _registerScreenState.value.bioValue else null,
            emailAddress = if (_registerScreenState.value.isUsingPhoneNumber) null else _registerScreenState.value.phoneEmailValue,
            phoneNumber = if (_registerScreenState.value.isUsingPhoneNumber) _registerScreenState.value.phoneEmailValue else null,
            profilePicture = _registerScreenState.value.profileImageValue,
            dateOfBirth = _registerScreenState.value.dobValueTimestamp,
        )
        currentUserRepository.registerUser(registerRequest).collect {
            when (it) {
                is ClientResponse.Error -> showMessage(it.message)
                ClientResponse.Idle -> updateLoaderState(false)
                ClientResponse.Loading -> updateLoaderState(true)
                is ClientResponse.Success -> {
                    it.data.message?.let { message -> showMessage(message) }
                    navigateToLogin?.invoke(_registerScreenState.value.usernameValue)
                }
            }
        }
    }

    private suspend fun performRegisterFormOperations(navigateToLogin: ((String) -> Unit)? = null) {
        userVerificationRepository.isUserPresent(_registerScreenState.value.phoneEmailValue)
            .collect {
                when (it) {
                    is ClientResponse.Error -> onUserPresentError(
                        it,
                    )

                    ClientResponse.Idle -> updateLoaderState(showLoader = false)
                    ClientResponse.Loading -> updateLoaderState(showLoader = true)
                    is ClientResponse.Success -> {
                        navigateToLogin?.invoke(_registerScreenState.value.phoneEmailValue)
                    }
                }
            }
    }

    private suspend fun performOTPOperations() {
        userVerificationRepository.validateOtp(
            otp = _registerScreenState.value.otpValue,
            value = _registerScreenState.value.phoneEmailValue,
        ).collect {
            when (it) {
                is ClientResponse.Error -> showMessage(it.message)
                ClientResponse.Idle -> updateLoaderState(showLoader = false)
                ClientResponse.Loading -> updateLoaderState(showLoader = true)
                is ClientResponse.Success -> otpVerificationSuccess()
            }
        }
    }

    private fun otpVerificationSuccess() {
        _registerScreenState.value = _registerScreenState.value.copy(
            showOtpOption = false,
            showPasswordOption = true,
            useEmailOrPhoneState = false,
            datePickerVisible = false,
            showLoading = false,
            snackBarMessage = null,
        )
    }

    private fun onUserPresentError(
        error: ClientResponse.Error,
    ) {
        if (error.errorCode == USER_DETAILS_NOT_FOUND_CODE) {
            generateOtp()
        } else {
            showMessage(error.message)
        }
    }

    private fun showMessage(message: String) {
        _registerScreenState.value = _registerScreenState.value.copy(
            snackBarMessage = message,
        )
    }

    private fun generateOtp() {
        viewModelScope.launch {
            userVerificationRepository.generateOtp(_registerScreenState.value.phoneEmailValue)
                .collect {
                    when (it) {
                        is ClientResponse.Error -> showMessage(it.message)
                        ClientResponse.Idle -> updateLoaderState(showLoader = false)
                        ClientResponse.Loading -> updateLoaderState(showLoader = true)
                        is ClientResponse.Success -> {
                            Logger.log(LoggerLevel.Info, "Generate OTP ${it.data.data?.otp}")
                            showOtpOption()
                        }
                    }
                }
        }
    }

    private fun showOtpOption() {
        _registerScreenState.value = _registerScreenState.value.copy(
            showOtpOption = true,
            useEmailOrPhoneState = false,
            datePickerVisible = false,
            showLoading = false,
            snackBarMessage = null,
        )
    }

    private fun updateLoaderState(showLoader: Boolean) {
        _registerScreenState.value = _registerScreenState.value.copy(
            showLoading = showLoader
        )
    }

    fun removeSnackBarMessage() {
        _registerScreenState.value = _registerScreenState.value.copy(
            snackBarMessage = null
        )
    }

    fun passwordDone() {
        _registerScreenState.value = _registerScreenState.value.copy(
            showOtpOption = false,
            showPasswordOption = false,
            showUsernameProfileImage = true,
            showLoading = false,
            snackBarMessage = null,
        )
    }
}