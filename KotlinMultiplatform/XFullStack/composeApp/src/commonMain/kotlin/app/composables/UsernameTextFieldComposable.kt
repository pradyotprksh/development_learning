package app.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import utils.Localization
import utils.TextFieldType

@Composable
fun UsernameTextFieldComposable(
    modifier: Modifier = Modifier,
    usernameValue: String,
    isUsernameValid: Boolean,
    imageAction: ImeAction,
    onDone: (() -> Unit)? = null,
    onValueChange: (TextFieldType, String) -> Unit,
) {
    OutlinedTextField(
        value = usernameValue,
        onValueChange = { value ->
            onValueChange(
                TextFieldType.Username,
                value
            )
        },
        label = {
            Text(
                Localization.USERNAME
            )
        },
        modifier = modifier.fillMaxWidth(),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = imageAction,
            capitalization = KeyboardCapitalization.None,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone?.invoke()
            }
        ),
        trailingIcon = {
            if (isUsernameValid) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = Icons.Default.CheckCircle.name,
                    tint = Color.Green
                )
            }
        }
    )
}