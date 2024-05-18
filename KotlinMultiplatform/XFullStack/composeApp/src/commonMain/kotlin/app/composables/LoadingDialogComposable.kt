package app.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import utils.Localization

@Composable
fun LoadingDialogComposable(
    message: String = Localization.LOADING
) {
    Dialog(
        content = {
            Box(
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.background
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 15.dp,
                        bottom = 15.dp
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Text(message)
                }
            }
        },
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        ),
    )
}