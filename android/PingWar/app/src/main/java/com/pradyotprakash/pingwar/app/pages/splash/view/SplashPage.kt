package com.pradyotprakash.pingwar.app.pages.splash.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.pradyotprakash.pingwar.app.composables.BouncingBall
import com.pradyotprakash.pingwar.app.localization.TR

@Composable
fun SplashPage() {
    Box(modifier = Modifier.fillMaxSize()) {
        BouncingBall()
        Text(
            text = TR.appName,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}