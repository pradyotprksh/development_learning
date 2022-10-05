package com.project.pradyotprakash.rental.app.pages.property.details.view.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.rental.app.localization.TR

@Composable
fun NoPropertyDetailsComposable() {
    Text(
        text = TR.noPropertiesAvailable,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(
            start = 15.dp,
            end = 15.dp,
        )
    )
}