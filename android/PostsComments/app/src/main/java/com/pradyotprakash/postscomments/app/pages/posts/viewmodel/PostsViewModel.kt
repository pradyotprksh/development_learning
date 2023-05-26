package com.pradyotprakash.postscomments.app.pages.posts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pradyotprakash.postscomments.app.composables.ConfirmationDialog
import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.core.auth.AuthState
import com.pradyotprakash.postscomments.core.auth.AuthStateListener
import com.pradyotprakash.postscomments.domain.usecases.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
    private val authStateListener: AuthStateListener,
): ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _confirmationDialog = MutableLiveData(ConfirmationDialog())
    val confirmationDialog: LiveData<ConfirmationDialog>
        get() = _confirmationDialog

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    fun confirmLogOutUser() {
        _confirmationDialog.value = ConfirmationDialog(
            text = TR.confirmLogout,
            onConfirm = {
                _confirmationDialog.value = ConfirmationDialog()
                logoutUser()
            },
            onDismiss = {
                _confirmationDialog.value = ConfirmationDialog()
            },
        )
    }

    fun logoutUser() {
        authenticationUseCase.logoutUser()
        authStateListener.stateChange(AuthState.Unauthenticated)
    }
}