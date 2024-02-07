package com.pradyotprakash.pingwar.app.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

@Composable
fun BouncingBall() {
    val circleColor = MaterialTheme.colorScheme.primary
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasSize = Size(size.width, size.height)
        drawCircle(
            color = circleColor,
            center = Offset(canvasSize.width / 2, canvasSize.height / 2),
            radius = 50f
        )
    }
}