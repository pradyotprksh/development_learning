package com.pradyotprakash.findingfalcone.app.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.pradyotprakash.findingfalcone.app.localization.TR
import com.pradyotprakash.findingfalcone.app.localization.Translation

@Composable
fun PageStateComposable(
    isLoading: Boolean = false,
    errorMessage: String = "",
    retryOperation: () -> Unit = {},
    dismissErrorAlert: () -> Unit = {},
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        retryOperation()
                    },
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = Icons.Default.Refresh.name,
                )
                Box(modifier = Modifier.height(10.dp))
                Text(
                    text = Translation.getString(TR.retry),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.ExtraBold
                )
            }
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
    }
}