package com.pradyotprakash.personalblog.app.pages.splash.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pradyotprakash.personalblog.utils.Localization
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToAuthenticationOption: () -> Unit,
) {
    LaunchedEffect(Unit) {
        delay(1000)
        navigateToAuthenticationOption()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        ) {
            Text(
                Localization.APP_NAME,
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
            )
        }
    }
}