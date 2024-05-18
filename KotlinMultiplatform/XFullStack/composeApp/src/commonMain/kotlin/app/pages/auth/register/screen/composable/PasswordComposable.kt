package app.pages.auth.register.screen.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import app.composables.PasswordTextFieldComposable
import app.pages.auth.register.state.RegisterState
import app.pages.auth.register.viewModel.RegisterViewModel
import utils.Localization
import utils.TextFieldType

@Composable
fun PasswordComposable(
    modifier: Modifier = Modifier,
    startEndPaddingModifier: Modifier = Modifier,
    registerScreenState: RegisterState,
    registerViewModel: RegisterViewModel,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            Localization.PROTECT_YOUR_ACCOUNT_WITH_PASSWORD,
            style = MaterialTheme.typography.headlineSmall,
            modifier = startEndPaddingModifier
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextFieldComposable(
            modifier = startEndPaddingModifier,
            passwordValue = registerScreenState.passwordValue,
            passwordValidation = registerScreenState.passwordValidation,
            passwordValid = registerScreenState.passwordValid,
            showPasswordErrors = true,
            imeAction = ImeAction.Next,
            onValueChange = { type, value ->
                registerViewModel.updateTextField(
                    textFieldType = type,
                    value = value
                )
            }
        )
        AnimatedVisibility(
            visible = registerScreenState.showConfirmPassword
        ) {
            OutlinedTextField(
                value = registerScreenState.confirmPasswordValue,
                onValueChange = {
                    registerViewModel.updateTextField(
                        textFieldType = TextFieldType.ConfirmPassword,
                        value = it
                    )
                },
                label = {
                    Text(
                        Localization.CONFIRM_PASSWORD
                    )
                },
                modifier = startEndPaddingModifier.fillMaxWidth(),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Send,
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        registerViewModel.passwordDone()
                    }
                ),
                trailingIcon = {
                    if (registerScreenState.confirmPasswordValid) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = Icons.Default.CheckCircle.name,
                            tint = Color.Green
                        )
                    }
                }
            )
        }
    }
}