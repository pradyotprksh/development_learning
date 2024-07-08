package app.pages.home.grok.screen.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import utils.Localization

@Composable
fun GrokTextFieldComposable(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    sendPrompt: () -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(Localization.ASK_US_ANYTHING)
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    sendPrompt()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = Icons.AutoMirrored.Filled.Send.name,
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Send,
        ),
        keyboardActions = KeyboardActions(
            onSend = {
                sendPrompt()
            }
        )
    )
}