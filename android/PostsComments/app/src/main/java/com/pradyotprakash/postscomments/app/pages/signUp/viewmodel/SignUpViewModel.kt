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
    }

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
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

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    fun updateValue(value: String, fieldType: FieldType) {
        when (fieldType) {
            FieldType.Email -> _emailAddress.value = value
            FieldType.Password -> _password.value = value
            FieldType.ConfirmPassword -> _confirmPassword.value = value
        }

        areFieldsCorrect()
    }

    private fun areFieldsCorrect() {
        val emailAddress = _emailAddress.value ?: ""
        val password = _password.value ?: ""
        val confirmPassword = _confirmPassword.value ?: ""

        _enableRegister.value =
            emailAddress.isValidEmailAddress() &&
                    password.isNotEmpty() && confirmPassword.isNotEmpty() &&
                    password == confirmPassword
    }

    fun registerUser() {
        if (_enableRegister.value == true) {
            val emailAddress = _emailAddress.value ?: ""
            val password = _password.value ?: ""

            createAccount(emailAddress, password)
        }
    }

    private fun createAccount(emailAddress: String, password: String) {
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
                        goToPostsScreen()
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