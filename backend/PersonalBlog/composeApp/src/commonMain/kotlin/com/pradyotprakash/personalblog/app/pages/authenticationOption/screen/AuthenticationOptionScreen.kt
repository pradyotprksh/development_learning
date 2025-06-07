package com.pradyotprakash.personalblog.app.pages.authenticationOption.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pradyotprakash.personalblog.app.pages.authenticationOption.viewModel.AuthenticationOptionViewModel

@Composable
fun AuthenticationOptionScreen(
    authenticationOptionViewModel: AuthenticationOptionViewModel = viewModel { AuthenticationOptionViewModel() },
    navigateToHome: (Boolean) -> Unit,
) {
    
}