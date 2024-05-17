package app.pages.auth.register.screen

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.LoadingDialog
import app.composables.XAppBar
import app.pages.auth.register.screen.composable.OtpVerificationComposable
import app.pages.auth.register.screen.composable.RegisterFormComposable
import app.pages.auth.register.viewModel.RegisterViewModel
import kotlinx.coroutines.launch
import utils.Localization

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = viewModel(),
    navigateToLogin: (String) -> Unit,
    navigateBack: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val registerScreenState by registerViewModel.registerScreenState.collectAsState()
    if (registerScreenState.showLoading) {
        LoadingDialog()
    }
    registerScreenState.errorMessage?.let { message ->
        scope.launch {
            val result = snackbarHostState
                .showSnackbar(
                    message = message,
                    actionLabel = Localization.OKAY,
                    duration = SnackbarDuration.Short
                )
            when (result) {
                SnackbarResult.ActionPerformed, SnackbarResult.Dismissed -> {
                    registerViewModel.removeErrorMessage()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            XAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = navigateBack,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name,
                        )
                    }
                },
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        val startEndPaddingModifier = Modifier.padding(
            start = it.calculateStartPadding(LocalLayoutDirection.current) + 25.dp,
            end = it.calculateEndPadding(LocalLayoutDirection.current) + 25.dp,
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(
                top = it.calculateTopPadding() + 10.dp,
                bottom = it.calculateBottomPadding() + 25.dp,
            ).imePadding()
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                AnimatedVisibility(
                    visible = registerScreenState.showOtpOption
                ) {
                    OtpVerificationComposable(
                        modifier = Modifier.weight(1f),
                        startEndPaddingModifier = startEndPaddingModifier,
                        registerScreenState = registerScreenState,
                        registerViewModel = registerViewModel,
                    )
                }
                AnimatedVisibility(
                    visible = !registerScreenState.showOtpOption
                ) {
                    RegisterFormComposable(
                        modifier = Modifier.weight(1f),
                        startEndPaddingModifier = startEndPaddingModifier,
                        registerScreenState = registerScreenState,
                        registerViewModel = registerViewModel,
                    )
                }
            }
            HorizontalDivider()
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = startEndPaddingModifier.fillMaxWidth()
            ) {
                AnimatedVisibility(
                    visible = registerScreenState.useEmailOrPhoneState,
                ) {
                    OutlinedButton(
                        onClick = {
                            registerViewModel.updateUseEmailOrPhone()
                        },
                    ) {
                        Text(
                            if (registerScreenState.isUsingPhoneNumber) Localization.USE_EMAIL_INSTEAD
                            else Localization.USE_PHONE_INSTEAD
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        registerViewModel.checkForDetails(
                            navigateToLogin = navigateToLogin,
                        )
                    }, enabled = registerScreenState.enableNextButton
                ) {
                    Text(
                        Localization.NEXT
                    )
                }
            }
        }
    }
}