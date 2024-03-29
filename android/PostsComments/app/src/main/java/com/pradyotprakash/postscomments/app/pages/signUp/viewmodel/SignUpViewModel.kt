package com.pradyotprakash.postscomments.app.pages.signUp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.postscomments.app.utils.isValidEmailAddress
import com.pradyotprakash.postscomments.core.auth.AuthState
import com.pradyotprakash.postscomments.core.auth.AuthStateListener
import com.pradyotprakash.postscomments.core.navigator.Navigator
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.domain.usecases.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
    private val navigator: Navigator,
    private val authStateListener: AuthStateListener,
) : ViewModel() {
    enum class FieldType {
        Email,
        Password,
        ConfirmPassword,
        Name,
    }

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _name = MutableLiveData("")
    val name: LiveData<String>
        get() = _name
    private val _emailAddress = MutableLiveData("")
    val emailAddress: LiveData<String>
        get() = _emailAddress
    private val _password = MutableLiveData("")
    val password: LiveData<String>
        get() = _password
    private val _confirmPassword = MutableLiveData("")
    val confirmPassword: LiveData<String>
        get() = _confirmPassword
    private val _enableRegister = MutableLiveData(false)
    val enableRegister: LiveData<Boolean>
        get() = _enableRegister
    private val _isEmailWrong = MutableLiveData(false)
    val isEmailWrong: LiveData<Boolean>
        get() = _isEmailWrong
    private val _isPasswordSame = MutableLiveData(true)
    val isPasswordSame: LiveData<Boolean>
        get() = _isPasswordSame

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    fun updateValue(value: String, fieldType: FieldType) {
        when (fieldType) {
            FieldType.Email -> {
                _emailAddress.value = value
                _isEmailWrong.value = !value.isValidEmailAddress()
            }
            FieldType.Password -> {
                _password.value = value
                val confirmPasswordValue = _confirmPassword.value ?: ""
                if (confirmPasswordValue.isNotEmpty()) {
                    _isPasswordSame.value = _confirmPassword.value == value
                }
            }
            FieldType.ConfirmPassword -> {
                _confirmPassword.value = value
                _isPasswordSame.value = _password.value == value
            }
            FieldType.Name -> _name.value = value
        }

        areFieldsCorrect()
    }

    private fun areFieldsCorrect() {
        val password = _password.value ?: ""
        val confirmPassword = _confirmPassword.value ?: ""
        val name = _name.value ?: ""

        _enableRegister.value =
            _isEmailWrong.value != true &&
                    name.trim().isNotEmpty() &&
                    password.trim().isNotEmpty() &&
                    confirmPassword.trim().isNotEmpty() &&
                    _isPasswordSame.value == true
    }

    fun registerUser() {
        if (_enableRegister.value == true) {
            val emailAddress = _emailAddress.value ?: ""
            val password = _password.value ?: ""
            val name = _name.value ?: ""

            createAccount(emailAddress, password, name)
        }
    }

    private fun createAccount(emailAddress: String, password: String, name: String) {
        viewModelScope.launch {
            authenticationUseCase.createUserWithEmailPassword(
                email = emailAddress,
                password = password,
            ).collect {
                when (it) {
                    is PostsCommentsResponse.Error -> updateErrorState(it.exception.message)
                    PostsCommentsResponse.Idle -> _loading.value = false
                    PostsCommentsResponse.Loading -> _loading.value = true
                    is PostsCommentsResponse.Success -> {
                        authenticationUseCase.createUserDocument(
                            email = emailAddress,
                            name = name,
                        ).collect { user ->
                            when (user) {
                                is PostsCommentsResponse.Error -> updateErrorState(user.exception.message)
                                PostsCommentsResponse.Idle -> _loading.value = false
                                PostsCommentsResponse.Loading -> _loading.value = true
                                is PostsCommentsResponse.Success -> goToPostsScreen()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun goToPostsScreen() {
        authStateListener.stateChange(AuthState.Authenticated)
    }

    fun goToLoginScreen() {
        navigator.navigate {
            it.popBackStack()
        }
    }
}