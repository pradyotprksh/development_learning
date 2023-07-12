package com.pradyotprakash.notes.app.pages.login.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.notes.app.composables.PageStateComposable
import com.pradyotprakash.notes.app.pages.login.viewmodel.LoginViewModel
import com.pradyotprakash.notes.app.pages.signup.viewmodel.SignUpViewModel

@Composable
fun LoginView(
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val error = loginViewModel.error.observeAsState("").value

    PageStateComposable(
        errorMessage = error,
        dismissErrorAlert = loginViewModel::updateErrorState
    ) {}
}