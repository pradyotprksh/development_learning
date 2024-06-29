package app.pages.directMessage.screen.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Gif
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import utils.Localization

@Composable
fun MessageFieldComposable(
    modifier: Modifier = Modifier,
    value: String,
    isFocused: Boolean,
    onValueChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit,
    onImageOptionSelect: () -> Unit,
    onGifOptionSelect: () -> Unit,
    onAudioOptionSelect: () -> Unit,
    sendMessage: () -> Unit,
) {
    TextField(
        value = value, onValueChange = onValueChange,
        leadingIcon = if (!isFocused) {
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = onImageOptionSelect,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = Icons.Default.Image.name,
                        )
                    }
                    IconButton(
                        onClick = onGifOptionSelect,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Gif,
                            contentDescription = Icons.Default.Gif.name,
                        )
                    }
                }
            }
        } else null,
        trailingIcon = if (!isFocused) {
            {
                IconButton(
                    onClick = onAudioOptionSelect,
                ) {
                    Icon(
                        imageVector = Icons.Default.RecordVoiceOver,
                        contentDescription = Icons.Default.RecordVoiceOver.name,
                    )
                }
            }
        } else null,
        modifier = modifier.fillMaxWidth().onFocusChanged {
            onFocusChange(it.isFocused)
        },
        placeholder = {
            Text(
                Localization.SEND_A_MESSAGE,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        colors = TextFieldDefaults.colors().copy(
            disabledTextColor = Color.Transparent,
            errorCursorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            disabledLeadingIconColor = Color.Transparent,
            errorLeadingIconColor = Color.Transparent,
            disabledTrailingIconColor = Color.Transparent,
            errorTrailingIconColor = Color.Transparent,
            focusedLabelColor = Color.Transparent,
            unfocusedLabelColor = Color.Transparent,
            disabledLabelColor = Color.Transparent,
            errorLabelColor = Color.Transparent,
            disabledPlaceholderColor = Color.Transparent,
        ),
        supportingText = if (isFocused) {
            {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = onImageOptionSelect,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = Icons.Default.Image.name,
                        )
                    }
                    IconButton(
                        onClick = onGifOptionSelect,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Gif,
                            contentDescription = Icons.Default.Gif.name,
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    if (value.isEmpty()) {
                        IconButton(
                            onClick = onAudioOptionSelect,
                        ) {
                            Icon(
                                imageVector = Icons.Default.RecordVoiceOver,
                                contentDescription = Icons.Default.RecordVoiceOver.name,
                            )
                        }
                    } else {
                        IconButton(
                            onClick = {
                                sendMessage()
                            },
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Send,
                                contentDescription = Icons.AutoMirrored.Filled.Send.name,
                            )
                        }
                    }
                }
            }
        } else null,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Send,
        ),
        keyboardActions = KeyboardActions(
            onSend = {
                sendMessage()
            },
        ),
    )
}