package com.pradyotprakash.notes.app.pages.signup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.notes.domain.usecases.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun updateErrorState() {
        _errorText.value = ""
    }

    fun updateFormField(value: String, fieldType: FieldType) {
        when (fieldType) {
            FieldType.emailId -> _emailId.value = value
            FieldType.username -> {
                _username.value = value
                checkForUsernameAvailability()
            }

            FieldType.password -> _password.value = value
        }
        checkForInputs()
    }

    private fun checkForUsernameAvailability() {
        val username = _username.value ?: ""
        if (username.isNotEmpty()) {
            viewModelScope.launch {
                _usernameTaken.value = userUseCase.isUsernameTaken(username = username)
            }
        }
    }

    private fun checkForInputs() {
        val username = _username.value ?: ""
        val emailId = _emailId.value ?: ""
        val password = _password.value ?: ""

        _isInputsValid.value =
            username.isNotEmpty() && emailId.isNotEmpty() && password.isNotEmpty()
    }
}