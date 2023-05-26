package com.pradyotprakash.postscomments.app.pages.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.postscomments.app.composables.PageStateComposable
import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.app.pages.login.viewmodel.LoginViewModel
import com.pradyotprakash.postscomments.app.pages.signUp.viewmodel.SignUpViewModel
import com.pradyotprakash.postscomments.app.utils.Assets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val loading by loginViewModel.loading.observeAsState(false)
    val enableLogin by loginViewModel.enableLogin.observeAsState(false)
    val error by loginViewModel.error.observeAsState("")
    val emailAddress by loginViewModel.emailAddress.observeAsState("")
    val password by loginViewModel.password.observeAsState("")

    PageStateComposable(
        isLoading = loading,
        errorMessage = error,
        dismissErrorAlert = loginViewModel::updateErrorState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Image(
                painter = painterResource(id = Assets.AppIcon.resourceId),
                contentDescription = Assets.AppIcon.imageDescription,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Box(modifier = Modifier.height(30.dp))
            Text(
                text = TR.welcomeToApp,
                style = MaterialTheme.typography.headlineMedium
            )
            Box(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = emailAddress,
                onValueChange = {
                    loginViewModel.updateValue(it, LoginViewModel.FieldType.Email)
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = TR.email)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )
            Box(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    loginViewModel.updateValue(it, LoginViewModel.FieldType.Password)
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = TR.password)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation(),
            )
            Box(modifier = Modifier.height(15.dp))
            Button(
                onClick = loginViewModel::loginUser,
                modifier = Modifier.fillMaxWidth(),
                enabled = enableLogin
            ) {
                Text(text = TR.login)
            }
            Box(modifier = Modifier.height(5.dp))
            TextButton(
                onClick = {}
            ) {
                Text(text = TR.forgotPassword)
            }
            Box(modifier = Modifier.height(30.dp))
            TextButton(
                onClick = loginViewModel::goToRegisterScreen,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = TR.signUp)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}