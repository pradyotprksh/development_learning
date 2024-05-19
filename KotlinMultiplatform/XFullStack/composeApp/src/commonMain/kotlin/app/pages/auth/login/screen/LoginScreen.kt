package app.pages.auth.login.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.LoadingDialogComposable
import app.composables.PasswordTextFieldComposable
import app.composables.XAppBarComposable
import app.pages.auth.login.viewModel.LoginViewModel
import kotlinx.coroutines.launch
import utils.Localization
import utils.TextFieldType

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel { LoginViewModel() },
    usernameEmailPhoneValue: String?,
    navigateBack: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val loginScreenState by loginViewModel.loginScreenState.collectAsState()
    LaunchedEffect(usernameEmailPhoneValue) {
        usernameEmailPhoneValue?.let {
            loginViewModel.updateTextField(
                TextFieldType.UsernamePhoneEmail,
                it
            )
        }
    }
    if (loginScreenState.showLoading) {
        LoadingDialogComposable()
    }
    loginScreenState.snackBarMessage?.let { message ->
        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = Localization.OKAY,
                duration = SnackbarDuration.Short
            )
            when (result) {
                SnackbarResult.ActionPerformed, SnackbarResult.Dismissed -> {
                    loginViewModel.removeSnackBarMessage()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            XAppBarComposable(
                navigationIcon = {
                    IconButton(
                        onClick = navigateBack,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = Icons.Default.Close.name,
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        val startEndPaddingModifier = Modifier.padding(
            start = it.calculateStartPadding(LocalLayoutDirection.current) + 25.dp,
            end = it.calculateEndPadding(LocalLayoutDirection.current) + 25.dp,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding() + 10.dp,
                    bottom = it.calculateBottomPadding() + 25.dp,
                )
                .imePadding()
        ) {
            Text(
                Localization.GET_STARTED_LOGIN,
                style = MaterialTheme.typography.headlineSmall,
                modifier = startEndPaddingModifier
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = loginScreenState.usernamePhoneEmailValue,
                onValueChange = { value ->
                    loginViewModel.updateTextField(
                        textFieldType = TextFieldType.UsernamePhoneEmail,
                        value = value,
                    )
                },
                label = {
                    Text(
                        Localization.PHONE_EMAIL_USERNAME
                    )
                },
                modifier = startEndPaddingModifier.fillMaxWidth(),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                ),
                trailingIcon = {
                    if (loginScreenState.isUsernamePhoneEmailValid) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = Icons.Default.CheckCircle.name,
                            tint = Color.Green
                        )
                    }
                },
            )
            Spacer(modifier = Modifier.height(15.dp))
            AnimatedVisibility(
                visible = loginScreenState.isUsernamePhoneEmailValid,
            ) {
                PasswordTextFieldComposable(
                    modifier = startEndPaddingModifier,
                    passwordValue = loginScreenState.passwordValue,
                    passwordValidation = loginScreenState.passwordValidation,
                    passwordValid = loginScreenState.passwordValid,
                    showPasswordErrors = false,
                    imeAction = ImeAction.Done,
                    onDone = {
                        loginViewModel.loginUser()
                    },
                    onValueChange = { type, value ->
                        loginViewModel.updateTextField(type, value)
                    }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider()
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = startEndPaddingModifier.fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = {}
                    ) {
                        Text(
                            Localization.FORGOT_PASSWORD
                        )
                    }
                    Button(
                        onClick = {
                            loginViewModel.loginUser()
                        }
                    ) {
                        Text(
                            Localization.LOG_IN
                        )
                    }
                }
            }
        }
    }
}