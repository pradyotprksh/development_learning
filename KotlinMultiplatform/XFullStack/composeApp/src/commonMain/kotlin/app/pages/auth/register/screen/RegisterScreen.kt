package app.pages.auth.register.screen

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.LoadingDialog
import app.composables.XAppBar
import app.pages.auth.register.viewModel.RegisterViewModel
import app.pages.otpVerification.model.OtpVerificationNavArguments
import kotlinx.coroutines.launch
import utils.Constants.ConstValues.NAME_MAX_LENGTH
import utils.Localization
import utils.TextFieldType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = viewModel(),
    navigateToOtpVerification: (OtpVerificationNavArguments) -> Unit,
    navigateToLogin: (String) -> Unit,
    navigateBack: () -> Unit,
) {
    val dateState = rememberDatePickerState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val registerScreenState by registerViewModel.registerScreenState.collectAsState()
    if (registerScreenState.datePickerVisible) {
        dateState.selectedDateMillis?.let { registerViewModel.updateSelectedDate(it) }
    }
    if (registerScreenState.showLoading) {
        LoadingDialog()
    }
    registerScreenState.errorMessage?.let { message ->
        scope.launch {
            val result = snackbarHostState
                .showSnackbar(
                    message = message,
                    actionLabel = Localization.OKAY,
                    duration = SnackbarDuration.Short
                )
            when (result) {
                SnackbarResult.ActionPerformed, SnackbarResult.Dismissed -> {
                    registerViewModel.removeErrorMessage()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            XAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = navigateBack,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name,
                        )
                    }
                },
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(
                top = it.calculateTopPadding() + 10.dp,
                bottom = it.calculateBottomPadding() + 25.dp,
            ).imePadding()
        ) {
            val startEndPaddingModifier = Modifier.padding(
                start = it.calculateStartPadding(LocalLayoutDirection.current) + 25.dp,
                end = it.calculateEndPadding(LocalLayoutDirection.current) + 25.dp,
            )

            Text(
                Localization.CREATE_YOUR_ACCOUNT,
                style = MaterialTheme.typography.headlineSmall,
                modifier = startEndPaddingModifier
            )
            Spacer(modifier = Modifier.weight(1f))
            OutlinedTextField(value = registerScreenState.nameValue,
                onValueChange = { value ->
                    registerViewModel.updateTextField(textFieldType = TextFieldType.Name, value)
                },
                label = {
                    Text(
                        Localization.NAME
                    )
                },
                modifier = startEndPaddingModifier.fillMaxWidth(),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Words,
                ),
                supportingText = {
                    Text(
                        text = "${registerScreenState.nameValue.length} / $NAME_MAX_LENGTH",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                trailingIcon = {
                    if (registerScreenState.isNameValid) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = Icons.Default.CheckCircle.name,
                            tint = Color.Green
                        )
                    }
                })
            OutlinedTextField(value = registerScreenState.phoneEmailValue,
                onValueChange = { value ->
                    registerViewModel.updateTextField(
                        textFieldType = TextFieldType.PhoneEmail, value
                    )
                },
                label = {
                    if (registerScreenState.useEmailOrPhoneState) {
                        if (registerScreenState.isUsingPhoneNumber) {
                            Text(
                                Localization.PHONE
                            )
                        } else {
                            Text(
                                Localization.EMAIL
                            )
                        }
                    } else {
                        Text(
                            Localization.PHONE_NUMBER_OR_EMAIL
                        )
                    }
                },
                modifier = startEndPaddingModifier.fillMaxWidth().onFocusChanged {
                    registerViewModel.focusedChangeForPhoneEmail()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = if (registerScreenState.isUsingPhoneNumber) {
                        KeyboardType.Phone
                    } else {
                        KeyboardType.Email
                    }, imeAction = ImeAction.Next
                ),
                maxLines = 1,
                trailingIcon = {
                    if (registerScreenState.isPhoneEmailValid) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = Icons.Default.CheckCircle.name,
                            tint = Color.Green
                        )
                    }
                },
                isError = registerScreenState.showPhoneNumberError,
                supportingText = {
                    if (registerScreenState.showPhoneNumberError) {
                        Text(
                            if (registerScreenState.isUsingPhoneNumber) Localization.EMAIL_PHONE_ERROR_MESSAGE.replace(
                                "%s", Localization.PHONE_NUMBER.lowercase()
                            )
                            else Localization.EMAIL_PHONE_ERROR_MESSAGE.replace(
                                "%s", Localization.EMAIL.lowercase()
                            )
                        )
                    }
                })
            OutlinedTextField(value = registerScreenState.dobValue, onValueChange = { value ->
                registerViewModel.updateTextField(textFieldType = TextFieldType.Dob, value)
            }, label = {
                Text(
                    Localization.DATE_OF_BIRTH
                )
            }, modifier = startEndPaddingModifier.fillMaxWidth().onFocusChanged {
                registerViewModel.focusedChangeForDob()
            }, readOnly = true, maxLines = 1, supportingText = {
                if (registerScreenState.datePickerVisible) {
                    Text(
                        text = Localization.DOB_SUPPORTING_TEXT,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            })
            Spacer(modifier = Modifier.weight(1f))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = startEndPaddingModifier.fillMaxWidth()
            ) {
                AnimatedVisibility(
                    visible = registerScreenState.useEmailOrPhoneState,
                ) {
                    OutlinedButton(onClick = {
                        registerViewModel.updateUseEmailOrPhone()
                    }) {
                        Text(
                            if (registerScreenState.isUsingPhoneNumber) Localization.USE_EMAIL_INSTEAD
                            else Localization.USE_PHONE_INSTEAD
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        registerViewModel.checkForDetails(
                            navigateToLogin = navigateToLogin,
                            navigateToOtpVerification = navigateToOtpVerification
                        )
                    }, enabled = registerScreenState.enableNextButton
                ) {
                    Text(
                        Localization.NEXT
                    )
                }
            }
            AnimatedVisibility(
                visible = registerScreenState.datePickerVisible,
            ) {
                DatePicker(
                    state = dateState,
                    title = null,
                    headline = null,
                    showModeToggle = false,
                )
            }
        }
    }
}