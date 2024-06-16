package app.pages.auth.authOptions.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import app.composables.AppIconComposable
import app.composables.XAppBarComposable
import app.pages.auth.authOptions.screen.composables.AuthOptionComposable

@Composable
fun AuthOptionsScreen(
    isPhone: Boolean,
    navigateToLogin: (String) -> Unit,
    navigateToRegister: () -> Unit,
) {
    Scaffold(
        topBar = {
            if (isPhone) {
                XAppBarComposable()
            }
        },
    ) {
        if (isPhone) {
            AuthOptionComposable(
                modifier = Modifier.fillMaxSize().padding(
                    top = it.calculateTopPadding() + 10.dp,
                    bottom = it.calculateBottomPadding() + 25.dp,
                    start = it.calculateStartPadding(LocalLayoutDirection.current) + 25.dp,
                    end = it.calculateEndPadding(LocalLayoutDirection.current) + 25.dp,
                ),
                navigateToLogin = navigateToLogin,
                navigateToRegister = navigateToRegister,
            )
        } else {
            Row(
                modifier = Modifier.fillMaxSize().padding(
                    top = it.calculateTopPadding() + 10.dp,
                    bottom = it.calculateBottomPadding() + if (isPhone) 25.dp else 10.dp,
                    start = it.calculateStartPadding(LocalLayoutDirection.current) + 25.dp,
                    end = it.calculateEndPadding(LocalLayoutDirection.current) + 25.dp,
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AppIconComposable(
                    boxModifier = Modifier.weight(1f).fillMaxSize(),
                )
                VerticalDivider()
                AuthOptionComposable(
                    modifier = Modifier.padding(
                        top = it.calculateTopPadding() + 10.dp,
                        bottom = it.calculateBottomPadding() + 25.dp,
                        start = it.calculateStartPadding(LocalLayoutDirection.current) + 25.dp,
                        end = it.calculateEndPadding(LocalLayoutDirection.current) + 25.dp,
                    ).weight(1f),
                    navigateToLogin = navigateToLogin,
                    navigateToRegister = navigateToRegister,
                )
            }
        }
    }
}