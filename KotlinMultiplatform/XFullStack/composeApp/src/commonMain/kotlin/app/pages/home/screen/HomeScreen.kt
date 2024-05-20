package app.pages.home.screen

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.LoadingDialogComposable
import app.composables.XAppBarComposable
import app.pages.home.viewModel.HomeViewModel
import kotlinx.coroutines.launch
import utils.Localization

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel { HomeViewModel() }
) {
    LaunchedEffect(homeViewModel) {
        homeViewModel.initialSetup()
    }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val homeScreenState by homeViewModel.homeScreenState.collectAsState()
    if (homeScreenState.showLoading) {
        LoadingDialogComposable()
    }
    homeScreenState.snackBarMessage?.let { message ->
        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = Localization.OKAY,
                duration = SnackbarDuration.Short
            )
            when (result) {
                SnackbarResult.ActionPerformed, SnackbarResult.Dismissed -> {
                    homeViewModel.removeSnackBarMessage()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            XAppBarComposable(
                actions = {
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = Icons.Default.Settings.name,
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
    }
}