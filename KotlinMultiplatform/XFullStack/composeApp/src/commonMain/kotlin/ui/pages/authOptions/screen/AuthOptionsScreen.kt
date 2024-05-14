package ui.pages.authOptions.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import core.utils.Localization
import ui.composables.TextBetweenDivider
import ui.composables.XAppBar
import ui.pages.authOptions.composables.LoginComposable
import ui.pages.authOptions.composables.SignupLegalDetails

@Composable
fun AuthOptionsScreen(
    navigateToLogin: () -> Unit,
) {
    Scaffold(
        topBar = {
            XAppBar()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding() + 10.dp,
                    bottom = it.calculateBottomPadding() + 25.dp,
                    start = it.calculateStartPadding(LocalLayoutDirection.current) + 25.dp,
                    end = it.calculateEndPadding(LocalLayoutDirection.current) + 25.dp,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(
                modifier = Modifier.weight(1f)
            )
            Text(
                Localization.SPLASH_SCREEN_MESSAGE,
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
                    Localization.CONTINUE_WITH_GOOGLE,
                )
            }
            TextBetweenDivider()
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    Localization.CREATE_ACCOUNT,
                )
            }
            Spacer(
                modifier = Modifier.height(15.dp)
            )
            SignupLegalDetails(
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.height(40.dp)
            )
            LoginComposable(
                modifier = Modifier.fillMaxWidth()
            ) {
                navigateToLogin()
            }
        }
    }
}