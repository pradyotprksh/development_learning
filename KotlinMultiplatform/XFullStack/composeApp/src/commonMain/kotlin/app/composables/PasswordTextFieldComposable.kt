package app.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import utils.Localization
import utils.PasswordValidation
import utils.TextFieldType

@Composable
fun PasswordTextFieldComposable(
    modifier: Modifier = Modifier,
    passwordValue: String,
    passwordValidation: PasswordValidation,
    passwordValid: Boolean,
    showPasswordErrors: Boolean,
    imeAction: ImeAction,
    onDone: (() -> Unit)? = null,
    onValueChange: (TextFieldType, String) -> Unit,
) {
    OutlinedTextField(
        value = passwordValue,
        onValueChange = {
            onValueChange(
                TextFieldType.Password,
                it
            )
        },
        label = {
            Text(
                Localization.PASSWORD
            )
        },
        modifier = modifier.fillMaxWidth(),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone?.invoke()
            }
        ),
        supportingText = {
            if (passwordValue.isNotBlank() && !passwordValidation.isValid && showPasswordErrors) {
                Column {
                    if (!passwordValidation.length)
                        Text(
                            Localization.PASSWORD_LENGTH_ERROR,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.error
                            ),
                        )
                    if (!passwordValidation.uppercase)
                        Text(
                            Localization.NO_UPPERCASE_ERROR,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.error
                            ),
                        )
                    if (!passwordValidation.lowercase)
                        Text(
                            Localization.NO_LOWERCASE_ERROR,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.error
                            ),
                        )
                    if (!passwordValidation.digit)
                        Text(
                            Localization.NO_DIGIT_ERROR,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.error
                            ),
                        )
                    if (!passwordValidation.specialCharacter)
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
            if (passwordValid) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = Icons.Default.CheckCircle.name,
                    tint = Color.Green
                )
            }
        },
        visualTransformation = PasswordVisualTransformation()
    )
}