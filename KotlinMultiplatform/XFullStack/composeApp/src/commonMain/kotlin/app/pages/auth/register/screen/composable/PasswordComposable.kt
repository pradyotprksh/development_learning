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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
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
        OutlinedTextField(
            value = registerScreenState.passwordValue,
            onValueChange = {
                registerViewModel.updateTextField(
                    textFieldType = TextFieldType.Password,
                    value = it
                )
            },
            label = {
                Text(
                    Localization.PASSWORD
                )
            },
            modifier = startEndPaddingModifier.fillMaxWidth(),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
            ),
            supportingText = {
                if (registerScreenState.passwordValue.isNotBlank() && !registerScreenState.passwordValidation.isValid) {
                    Column {
                        if (!registerScreenState.passwordValidation.length)
                            Text(
                                Localization.PASSWORD_LENGTH_ERROR,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.error
                                ),
                            )
                        if (!registerScreenState.passwordValidation.uppercase)
                            Text(
                                Localization.NO_UPPERCASE_ERROR,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.error
                                ),
                            )
                        if (!registerScreenState.passwordValidation.lowercase)
                            Text(
                                Localization.NO_LOWERCASE_ERROR,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.error
                                ),
                            )
                        if (!registerScreenState.passwordValidation.digit)
                            Text(
                                Localization.NO_DIGIT_ERROR,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.error
                                ),
                            )
                        if (!registerScreenState.passwordValidation.specialCharacter)
                            Text(
                                Localization.NO_SPECIAL_CHARACTER_ERROR,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.error
                                ),
                            )
                    }
                }
            },
            trailingIcon = {
                if (registerScreenState.passwordValid) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = Icons.Default.CheckCircle.name,
                        tint = Color.Green
                    )
                }
            },
            visualTransformation = PasswordVisualTransformation()
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
                    onSend = {}
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