package ui.pages.splash.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.pages.splash.screen.composables.SignupLegalDetails
import ui.pages.splash.screen.composables.TextBetweenDivider
import ui.pages.splash.viewModel.SplashScreenViewModel
import utils.Resources
import xfullstack.composeapp.generated.resources.Res
import xfullstack.composeapp.generated.resources.continue_with_google
import xfullstack.composeapp.generated.resources.create_account
import xfullstack.composeapp.generated.resources.splash_screen_message

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SplashScreen(
    splashScreenViewModel: SplashScreenViewModel = viewModel { SplashScreenViewModel() }
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = 10.dp,
                    horizontal = 15.dp,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(Resources.Logo.resource),
                Resources.Logo.contentDescription,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(50.dp)
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
            Text(
                stringResource(Res.string.splash_screen_message),
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    stringResource(Res.string.continue_with_google),
                )
            }
            TextBetweenDivider()
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    stringResource(Res.string.create_account),
                )
            }
            Spacer(
                modifier = Modifier.height(15.dp)
            )
            SignupLegalDetails()
        }
    }
}