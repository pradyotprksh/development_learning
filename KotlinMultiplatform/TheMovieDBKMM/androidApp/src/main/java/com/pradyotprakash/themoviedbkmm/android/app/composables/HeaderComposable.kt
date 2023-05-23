package com.pradyotprakash.themoviedbkmm.android.app.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HeaderComposable(title: String) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.background
            ),
            modifier = Modifier.padding(
                all = 15.dp,
            )
        )
    }
}