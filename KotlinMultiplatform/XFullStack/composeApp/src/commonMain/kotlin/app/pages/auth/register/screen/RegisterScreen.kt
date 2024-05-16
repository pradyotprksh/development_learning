package app.pages.auth.register.screen

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import app.composables.XAppBar
import app.pages.auth.register.viewModel.RegisterViewModel
import core.utils.Localization

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = viewModel(),
    navigateBack: () -> Unit,
) {
    Scaffold(topBar = {
        XAppBar(navigationIcon = {
            IconButton(
                onClick = navigateBack,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name,
                )
            }
        })
    }) {
        Column(
            modifier = Modifier.fillMaxSize().padding(
                top = it.calculateTopPadding() + 10.dp,
                bottom = it.calculateBottomPadding() + 25.dp,
            ).imePadding()
        ) {
            val startEndPaddingModifier = Modifier.padding(
                start = it.calculateStartPadding(LocalLayoutDirection.current) + 25.dp,
                end = it.calculateEndPadding(LocalLayoutDirection.current) + 25.dp,
            )

            Text(
                Localization.CREATE_YOUR_ACCOUNT,
                style = MaterialTheme.typography.headlineSmall,
                modifier = startEndPaddingModifier
            )
            Spacer(modifier = Modifier.weight(1f))
            OutlinedTextField(value = "", onValueChange = { }, label = {
                Text(
                    Localization.NAME
                )
            }, modifier = startEndPaddingModifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(value = "", onValueChange = { }, label = {
                Text(
                    Localization.PHONE_NUMBER_OR_EMAIL
                )
            }, modifier = startEndPaddingModifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(value = "", onValueChange = { }, label = {
                Text(
                    Localization.DATE_OF_BIRTH
                )
            }, modifier = startEndPaddingModifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = startEndPaddingModifier.fillMaxWidth()
            ) {
                OutlinedButton(onClick = {}) {
                    Text(
                        Localization.USE_EMAIL_INSTEAD
                    )
                }
                Button(onClick = {}) {
                    Text(
                        Localization.NEXT
                    )
                }
            }
        }
    }
}