package com.pradyotprakash.notes.app.pages.signup.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.notes.app.composables.PageStateComposable
import com.pradyotprakash.notes.app.localization.TR
import com.pradyotprakash.notes.app.pages.signup.viewmodel.FieldType
import com.pradyotprakash.notes.app.pages.signup.viewmodel.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpView(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
) {
    val error = signUpViewModel.error.observeAsState("").value
    val username = signUpViewModel.username.observeAsState("").value
    val firstName = signUpViewModel.firstName.observeAsState("").value
    val lastName = signUpViewModel.lastName.observeAsState("").value
    val emailId = signUpViewModel.emailId.observeAsState("").value
    val password = signUpViewModel.password.observeAsState("").value
    val isInputValid = signUpViewModel.isInputValid.observeAsState(false).value
    val usernameTaken = signUpViewModel.usernameTaken.observeAsState(false).value
    val emailIdTaken = signUpViewModel.emailIdTaken.observeAsState(false).value

    PageStateComposable(
        errorMessage = error,
        dismissErrorAlert = signUpViewModel::updateErrorState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = TR.signUp)
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = username,
                    onValueChange = { value ->
                        signUpViewModel.updateFormField(value, FieldType.Username)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    label = { Text(text = TR.username) },
                    isError = usernameTaken,
                    supportingText = {
                        if (usernameTaken) {
                            Text(text = TR.usernameTaken)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = firstName,
                    onValueChange = { value ->
                        signUpViewModel.updateFormField(value, FieldType.FirstName)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    label = { Text(text = TR.firstName) },
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = lastName,
                    onValueChange = { value ->
                        signUpViewModel.updateFormField(value, FieldType.LastName)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    label = { Text(text = TR.lastName) },
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = emailId,
                    onValueChange = { value ->
                        signUpViewModel.updateFormField(value, FieldType.EmailId)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    label = { Text(text = TR.emailId) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                    ),
                    isError = emailIdTaken,
                    supportingText = {
                        if (emailIdTaken) {
                            Text(text = TR.emailIdTaken)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = password,
                    onValueChange = { value ->
                        signUpViewModel.updateFormField(value, FieldType.Password)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    label = { Text(text = TR.password) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = signUpViewModel::createAccount,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    enabled = isInputValid,
                ) {
                    Text(text = TR.createAccount)
                }
            }
        }
    }
}