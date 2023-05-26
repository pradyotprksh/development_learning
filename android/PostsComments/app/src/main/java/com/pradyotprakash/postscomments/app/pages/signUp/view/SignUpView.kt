package com.pradyotprakash.postscomments.app.pages.signUp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import com.pradyotprakash.postscomments.app.pages.signUp.viewmodel.SignUpViewModel
import com.pradyotprakash.postscomments.app.utils.Assets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpView(
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val loading by signUpViewModel.loading.observeAsState(false)
    val enableRegister by signUpViewModel.enableRegister.observeAsState(false)
    val error by signUpViewModel.error.observeAsState("")
    val emailAddress by signUpViewModel.emailAddress.observeAsState("")
    val password by signUpViewModel.password.observeAsState("")
    val confirmPassword by signUpViewModel.confirmPassword.observeAsState("")

    PageStateComposable(
        isLoading = loading,
        errorMessage = error,
        dismissErrorAlert = signUpViewModel::updateErrorState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = Icons.Default.ArrowBack.name,
                    modifier = Modifier.clickable {
                        signUpViewModel.goToLoginScreen()
                    }
                )
                Image(
                    painter = painterResource(id = Assets.AppIcon.resourceId),
                    contentDescription = Assets.AppIcon.imageDescription,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                )
            }
            Box(modifier = Modifier.height(30.dp))
            Text(
                text = TR.signingUp,
                style = MaterialTheme.typography.headlineMedium
            )
            Box(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = emailAddress,
                onValueChange = {
                    signUpViewModel.updateValue(it, SignUpViewModel.FieldType.Email)
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
                    signUpViewModel.updateValue(it, SignUpViewModel.FieldType.Password)
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
            Box(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    signUpViewModel.updateValue(it, SignUpViewModel.FieldType.ConfirmPassword)
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = TR.confirmPassword)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation(),
            )
            Box(modifier = Modifier.height(15.dp))
            Button(
                onClick = signUpViewModel::registerUser,
                modifier = Modifier.fillMaxWidth(),
                enabled = enableRegister
            ) {
                Text(text = TR.register)
            }
            Box(modifier = Modifier.height(30.dp))
            TextButton(
                onClick = signUpViewModel::goToLoginScreen,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = TR.alreadyHaveAccount)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}