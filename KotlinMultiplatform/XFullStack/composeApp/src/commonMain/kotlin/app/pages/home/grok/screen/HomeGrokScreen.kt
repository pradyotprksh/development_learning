package app.pages.home.grok.screen

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import app.pages.home.grok.viewModel.HomeGrokViewModel
import kotlinx.coroutines.launch
import utils.Localization

@Composable
fun HomeGrokScreen(
    isPhone: Boolean,
    homeGrokViewModel: HomeGrokViewModel = viewModel { HomeGrokViewModel() },
) {
    val homeGrokState by homeGrokViewModel.homeGrokState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    homeGrokState.snackBarMessage?.let { message ->
        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = Localization.OKAY,
                duration = SnackbarDuration.Short
            )
            when (result) {
                SnackbarResult.ActionPerformed, SnackbarResult.Dismissed -> {
                    homeGrokViewModel.removeSnackBarMessage()
                }
            }
        }
    }

    Scaffold { paddingValues ->

    }
}