package com.project.pradyotprakash.rental.app.pages.splash.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.localization.Translation
import com.project.pradyotprakash.rental.app.pages.splash.viewmodel.SplashViewModel

/**
 * The splash screen which will be used to perform some initial
 * processes.
 */
@Composable
fun SplashView(
    splashViewModel: SplashViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = Translation.getString(TR.appName),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.ExtraBold
        )
    }
}
