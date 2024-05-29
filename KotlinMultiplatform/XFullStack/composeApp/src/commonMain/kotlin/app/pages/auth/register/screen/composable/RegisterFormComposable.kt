package app.pages.auth.register.screen.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import app.pages.auth.register.state.RegisterState
import app.pages.auth.register.viewModel.RegisterViewModel
import utils.Constants
import utils.Localization
import utils.TextFieldType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterFormComposable(
    modifier: Modifier = Modifier,
    startEndPaddingModifier: Modifier = Modifier,
    registerScreenState: RegisterState,
    registerViewModel: RegisterViewModel,
) {
    val dateState = rememberDatePickerState()
    if (registerScreenState.datePickerVisible) {
        dateState.selectedDateMillis?.let { registerViewModel.updateSelectedDate(it) }
    }

    Column(
        modifier = modifier,
    ) {
        Text(
            Localization.CREATE_YOUR_ACCOUNT,
            style = MaterialTheme.typography.headlineSmall,
            modifier = startEndPaddingModifier
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedTextField(
            value = registerScreenState.nameValue,
            onValueChange = { value ->
                registerViewModel.updateTextField(
                    textFieldType = TextFieldType.Name, value
                )
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
                    text = "${registerScreenState.nameValue.length} / ${Constants.ConstValues.NAME_MAX_LENGTH}",
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
            },
        )
        OutlinedTextField(
            value = registerScreenState.phoneEmailValue,
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
                        if (registerScreenState.isUsingPhoneNumber) Localization.format(
                            Localization.EMAIL_PHONE_ERROR_MESSAGE,
                            Localization.PHONE_NUMBER.lowercase()
                        )
                        else Localization.format(
                            Localization.EMAIL_PHONE_ERROR_MESSAGE, Localization.EMAIL.lowercase()
                        )
                    )
                }
            },
        )
        OutlinedTextField(
            value = registerScreenState.dobValue,
            onValueChange = { value ->
                registerViewModel.updateTextField(
                    textFieldType = TextFieldType.Dob, value
                )
            },
            label = {
                Text(
                    Localization.DATE_OF_BIRTH
                )
            },
            modifier = startEndPaddingModifier.fillMaxWidth().onFocusChanged {
                registerViewModel.focusedChangeForDob()
            },
            readOnly = true,
            maxLines = 1,
            supportingText = {
                if (registerScreenState.datePickerVisible) {
                    Text(
                        text = Localization.DOB_SUPPORTING_TEXT,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            },
        )
        Spacer(modifier = Modifier.weight(1f))
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