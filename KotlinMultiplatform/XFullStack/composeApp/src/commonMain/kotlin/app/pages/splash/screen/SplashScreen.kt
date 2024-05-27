package app.pages.splash.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.AppIconComposable
import app.pages.splash.viewModel.SplashViewModel
import kotlinx.coroutines.launch
import utils.Localization

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = viewModel { SplashViewModel() },
    navigateToAuthOption: () -> Unit,
    navigateToHome: () -> Unit,
) {
    LaunchedEffect(Unit) {
        splashViewModel.initiate(
            navigateToAuthOption,
            navigateToHome,
        )
    }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val splashScreenState by splashViewModel.splashScreenState.collectAsState()
    splashScreenState.snackBarMessage?.let { message ->
        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = Localization.OKAY,
                duration = SnackbarDuration.Short
            )
            when (result) {
                SnackbarResult.ActionPerformed, SnackbarResult.Dismissed -> {
                    splashViewModel.removeSnackBarMessage()
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(
                top = it.calculateTopPadding() + 10.dp,
                bottom = it.calculateBottomPadding() + 25.dp,
                start = it.calculateStartPadding(LocalLayoutDirection.current) + 25.dp,
                end = it.calculateEndPadding(LocalLayoutDirection.current) + 25.dp,
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AppIconComposable(
                boxModifier = Modifier.size(100.dp),
                imageModifier = Modifier.size(50.dp),
                showCircularProgressIndicator = splashScreenState.showLoading,
            )
            Spacer(modifier = Modifier.height(15.dp))
            if (splashScreenState.showLoading) {
                Text(
                    Localization.LOADING, style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}