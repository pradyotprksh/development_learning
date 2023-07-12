package com.pradyotprakash.notes.app.pages.signup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.notes.app.localization.TR
import com.pradyotprakash.notes.domain.usecases.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
) : ViewModel() {
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText

    private val _username = MutableLiveData("")
    val username: LiveData<String>
        get() = _username

    private val _firstName = MutableLiveData("")
    val firstName: LiveData<String>
        get() = _firstName

    private val _lastName = MutableLiveData("")
    val lastName: LiveData<String>
        get() = _lastName

    private val _emailId = MutableLiveData("")
    val emailId: LiveData<String>
        get() = _emailId

    private val _password = MutableLiveData("")
    val password: LiveData<String>
        get() = _password

    private val _isInputsValid = MutableLiveData(false)
    val isInputValid: LiveData<Boolean>
        get() = _isInputsValid

    private val _usernameTaken = MutableLiveData(false)
    val usernameTaken: LiveData<Boolean>
        get() = _usernameTaken

    private val _emailIdTaken = MutableLiveData(false)
    val emailIdTaken: LiveData<Boolean>
        get() = _emailIdTaken

    fun updateErrorState(message: String = "") {
        _errorText.value = message
    }

    fun updateFormField(value: String, fieldType: FieldType) {
        when (fieldType) {
            FieldType.EmailId -> {
                _emailId.value = value
                checkForEmailIdAvailability()
            }
            FieldType.Username -> {
                _username.value = value
                checkForUsernameAvailability()
            }

            FieldType.Password -> _password.value = value
            FieldType.FirstName -> _firstName.value = value
            FieldType.LastName -> _lastName.value = value
        }
        checkForInputs()
    }

    private fun checkForEmailIdAvailability() {
        val emailId = _emailId.value ?: ""
        if (emailId.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                _emailIdTaken.postValue(userUseCase.isEmailIdUsed(emailId = emailId))
            }
        }
    }

    private fun checkForUsernameAvailability() {
        val username = _username.value ?: ""
        if (username.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                _usernameTaken.postValue(userUseCase.isUsernameTaken(username = username))
            }
        }
    }

    private fun checkForInputs() {
        val username = _username.value ?: ""
        val emailId = _emailId.value ?: ""
        val password = _password.value ?: ""
        val firstName = _firstName.value ?: ""
        val lastName = _lastName.value ?: ""
        val usernameValid = !(_usernameTaken.value ?: true)
        val emailIdValid = !(_emailIdTaken.value ?: true)

        _isInputsValid.value =
            username.isNotEmpty() && emailId.isNotEmpty() && password.isNotEmpty()
                    && firstName.isNotEmpty() && lastName.isNotEmpty()
                    && usernameValid && emailIdValid
    }

    fun createAccount() {
        if (_isInputsValid.value == true) {
            val username = _username.value ?: ""
            val emailId = _emailId.value ?: ""
            val password = _password.value ?: ""
            val firstName = _firstName.value ?: ""
            val lastName = _lastName.value ?: ""

            viewModelScope.launch(Dispatchers.IO) {
                val isAdded = userUseCase.createUser(
                    username = username,
                    emailId = emailId,
                    password = password,
                    firstName = firstName,
                    lastName = lastName
                )

                if (isAdded) {

                } else {
                    updateErrorState(message = TR.signUpUserError)
                }
            }
        }
    }
}