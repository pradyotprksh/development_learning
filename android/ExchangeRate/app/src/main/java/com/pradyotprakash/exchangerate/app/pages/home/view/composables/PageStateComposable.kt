package com.pradyotprakash.exchangerate.app.pages.home.view.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.pradyotprakash.exchangerate.app.localization.TR

data class ConfirmationDialog(
    val text: String = "",
    val onConfirm: () -> Unit = {},
    val onDismiss: () -> Unit = {},
) {
    val showDialog: Boolean
        get() = text.isNotBlank()
}

@Composable
fun PageStateComposable(
    isLoading: Boolean = false,
    errorMessage: String = "",
    dismissErrorAlert: () -> Unit = {},
    confirmationDialog: ConfirmationDialog = ConfirmationDialog(),
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        content()
        if (isLoading) {
            Card(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
        if (errorMessage.isNotEmpty()) {
            AlertDialog(
                text = {
                    Text(
                        text = errorMessage,
                        textAlign = TextAlign.Center,
                    )
                },
                onDismissRequest = dismissErrorAlert,
                confirmButton = {
                    TextButton(onClick = dismissErrorAlert) {
                        Text(text = TR.okay)
                    }
                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                )
            )
        }
        if (confirmationDialog.showDialog) {
            AlertDialog(
                text = {
                    Text(
                        text = confirmationDialog.text,
                        textAlign = TextAlign.Center,
                    )
                },
                onDismissRequest = confirmationDialog.onDismiss,
                confirmButton = {
                    TextButton(onClick = confirmationDialog.onConfirm) {
                        Text(text = TR.okay)
                    }
                },
                dismissButton = {
                    TextButton(onClick = confirmationDialog.onDismiss) {
                        Text(text = TR.cancel)
                    }
                },
                properties = DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false
                )
            )
        }
    }
}