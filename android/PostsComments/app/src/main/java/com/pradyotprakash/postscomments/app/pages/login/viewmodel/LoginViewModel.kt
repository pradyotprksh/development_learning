package com.pradyotprakash.postscomments.app.pages.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.postscomments.app.utils.isValidEmailAddress
import com.pradyotprakash.postscomments.core.auth.AuthState
import com.pradyotprakash.postscomments.core.auth.AuthStateListener
import com.pradyotprakash.postscomments.core.navigator.Navigator
import com.pradyotprakash.postscomments.core.navigator.Routes
import com.pradyotprakash.postscomments.core.navigator.path
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.domain.usecases.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
    private val navigator: Navigator,
    private val authStateListener: AuthStateListener,
) : ViewModel() {
    enum class FieldType {
        Email,
        Password,
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
    private val _enableLogin = MutableLiveData(false)
    val enableLogin: LiveData<Boolean>
        get() = _enableLogin

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    fun updateValue(value: String, fieldType: FieldType) {
        when (fieldType) {
            FieldType.Email -> _emailAddress.value = value
            FieldType.Password -> _password.value = value
        }

        areFieldsCorrect()
    }

    private fun areFieldsCorrect() {
        val emailAddress = _emailAddress.value ?: ""
        val password = _password.value ?: ""

        _enableLogin.value =
            emailAddress.isValidEmailAddress() &&
                    password.isNotEmpty()
    }

    fun loginUser() {
        if (_enableLogin.value == true) {
            val emailAddress = _emailAddress.value ?: ""
            val password = _password.value ?: ""

            validateAccount(emailAddress, password)
        }
    }

    private fun validateAccount(emailAddress: String, password: String) {
        viewModelScope.launch {
            authenticationUseCase.signInUserWithEmailPassword(
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

    fun goToRegisterScreen() {
        navigator.navigate {
            it.navigate(Routes.SignUp.path())
        }
    }
}