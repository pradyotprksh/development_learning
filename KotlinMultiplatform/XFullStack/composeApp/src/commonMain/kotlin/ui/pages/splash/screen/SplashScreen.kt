package ui.pages.splash.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.pages.splash.viewModel.SplashViewModel
import utils.Resources

@OptIn(ExperimentalResourceApi::class)
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding() + 10.dp,
                    bottom = it.calculateBottomPadding() + 25.dp,
                    start = it.calculateStartPadding(LocalLayoutDirection.current) + 25.dp,
                    end = it.calculateEndPadding(LocalLayoutDirection.current) + 25.dp,
                ),
        ) {
            Image(
                painter = painterResource(Resources.Logo.resource),
                Resources.Logo.contentDescription,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(100.dp)
            )
        }
    }
}