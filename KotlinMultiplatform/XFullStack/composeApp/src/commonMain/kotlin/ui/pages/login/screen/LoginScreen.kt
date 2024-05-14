package ui.pages.login.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import core.utils.Localization
import ui.composables.XAppBar
import ui.pages.login.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel { LoginViewModel() },
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            XAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = navigateBack,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = Icons.Default.Close.name,
                        )
                    }
                }
            )
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
                )
                .imePadding()
        ) {
            Text(
                Localization.GET_STARTED_LOGIN,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = {
                    Text(
                        Localization.PHONE_EMAIL_USERNAME
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider()
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = {}
                    ) {
                        Text(
                            Localization.FORGOT_PASSWORD
                        )
                    }
                    Button(
                        onClick = {}
                    ) {
                        Text(
                            Localization.NEXT
                        )
                    }
                }
            }
        }
    }
}