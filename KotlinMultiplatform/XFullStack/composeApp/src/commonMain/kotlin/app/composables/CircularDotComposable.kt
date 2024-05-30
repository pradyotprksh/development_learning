package app.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

@Composable
fun CircularDotComposable(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.clip(CircleShape).background(
            color = MaterialTheme.colorScheme.onBackground
        )
    )
}