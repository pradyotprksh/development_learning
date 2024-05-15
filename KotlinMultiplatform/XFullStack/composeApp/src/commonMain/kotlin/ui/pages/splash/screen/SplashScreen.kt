package ui.pages.splash.screen

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import core.utils.Localization
import ui.composables.AppIcon
import ui.pages.splash.viewModel.SplashViewModel

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = viewModel { SplashViewModel() },
    navigateToAuthOption: () -> Unit,
    navigateToHome: () -> Unit,
) {
    LaunchedEffect(Unit) {
        splashViewModel.navigateToAuthOption(
            navigateToAuthOption,
            navigateToHome,
        )
    }

    Scaffold {
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
            AppIcon(
                boxModifier = Modifier.size(100.dp),
                imageModifier = Modifier.size(100.dp),
                showCircularProgressIndicator = true,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                Localization.LOADING, style = MaterialTheme.typography.titleMedium
            )
        }
    }
}