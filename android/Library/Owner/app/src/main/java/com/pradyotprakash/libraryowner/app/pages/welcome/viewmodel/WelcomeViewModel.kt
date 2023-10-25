package com.pradyotprakash.libraryowner.app.pages.welcome.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.pradyotprakash.libraryowner.domain.usecases.UserFirestoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val userFirestoreUseCase: UserFirestoreUseCase,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    fun firebaseAuthResponse(result: FirebaseAuthUIAuthenticationResult?) {
        result?.let {
            val response = result.idpResponse
            if (result.resultCode == Activity.RESULT_OK) {
            } else {
                updateErrorState(response?.error?.localizedMessage ?: "")
            }
        }
    }
}