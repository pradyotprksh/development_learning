package com.pradyotprakash.personalblog.app.pages.authenticationOption.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pradyotprakash.personalblog.app.pages.authenticationOption.state.FieldType
import com.pradyotprakash.personalblog.app.pages.authenticationOption.viewModel.AuthenticationOptionViewModel
import com.pradyotprakash.personalblog.utils.Localization

@Composable
fun AuthenticationOptionScreen(
    authenticationOptionViewModel: AuthenticationOptionViewModel = viewModel { AuthenticationOptionViewModel() },
    navigateToHome: (Boolean) -> Unit,
) {
    val state = authenticationOptionViewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Box(modifier = Modifier.weight(1f))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.clickable(
                    onClick = {
                        authenticationOptionViewModel.onIsAdminCheckChange()
                    },
                ).padding(10.dp)
            ) {
                Text(
                    Localization.ARE_YOU_ADMIN,
                )
                Box(
                    modifier = Modifier.width(15.dp)
                )
                Switch(
                    checked = state.value.isAdmin,
                    onCheckedChange = {
                        authenticationOptionViewModel.onIsAdminCheckChange()
                    },
                )
            }
            AnimatedVisibility(
                visible = state.value.isAdmin,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Box(
                        modifier = Modifier.height(10.dp)
                    )
                    OutlinedTextField(
                        value = state.value.username,
                        isError = state.value.isAdminError,
                        onValueChange = {
                            authenticationOptionViewModel.onFieldUpdate(
                                value = it,
                                fieldType = FieldType.Username,
                            )
                        },
                        label = {
                            Text(Localization.USERNAME)
                        }
                    )
                    Box(
                        modifier = Modifier.height(5.dp)
                    )
                    OutlinedTextField(
                        value = state.value.password,
                        isError = state.value.isAdminError,
                        onValueChange = {
                            authenticationOptionViewModel.onFieldUpdate(
                                value = it,
                                fieldType = FieldType.Password,
                            )
                        },
                        label = {
                            Text(Localization.PASSWORD)
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                        ),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Box(
                        modifier = Modifier.height(10.dp)
                    )
                }
            }
            Box(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    authenticationOptionViewModel.checkAuthenticationOption(
                        navigateToHome = navigateToHome,
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(Localization.CONTINUE)
            }
        }
    }
}