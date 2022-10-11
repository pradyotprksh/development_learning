package com.project.pradyotprakash.demo.app.pages.splash.view

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.project.pradyotprakash.demo.app.pages.splash.viewmodel.SplashViewModel

@Composable
fun SplashView(splashViewModel: SplashViewModel) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Demo App",
            style = MaterialTheme.typography.caption,
        )
    }
}