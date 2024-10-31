package com.pradyotprakash.glassbridgegame.app.pages.splash.screen

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pradyotprakash.glassbridgegame.app.pages.splash.viewModel.SplashViewModel
import glassbridgegame.composeapp.generated.resources.Res
import glassbridgegame.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = viewModel { SplashViewModel() },
    navigateToHome: () -> Unit,
) {
    LaunchedEffect(Unit) {
        splashViewModel.goToNextScreen(
            navigateToHome = navigateToHome,
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(15.dp),
        ) {
            Text(
                stringResource(Res.string.app_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
            )
        }
    }
}