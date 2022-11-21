package com.project.pradyotprakash.rental.app.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardSwitchComposable(
    text: String = "",
    value: Boolean = false,
    onChange: (Boolean) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = text)
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = value,
                onCheckedChange = onChange,
            )
        }
    }
}