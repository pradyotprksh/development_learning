package app.pages.slides.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.LoadingDialogComposable
import app.pages.slides.viewModel.SlidesViewModel
import kotlinx.coroutines.launch
import utils.Localization
import utils.getDisplayName
import utils.getDisplaySymbol

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SlidesScreen(
    slidesFilePath: String,
    slidesViewModel: SlidesViewModel = viewModel { SlidesViewModel() },
    navigateBack: () -> Unit,
) {
    LaunchedEffect(slidesFilePath) {
        slidesViewModel.loadSlidesDetails(slidesFilePath)
    }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val slidesState by slidesViewModel.slidesState.collectAsState()
    val requester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        requester.requestFocus()
    }

    if (slidesState.showLoading) {
        LoadingDialogComposable()
    }

    slidesState.snackBarMessage?.let { message ->
        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = Localization.OKAY,
                duration = SnackbarDuration.Short
            )
            when (result) {
                SnackbarResult.ActionPerformed, SnackbarResult.Dismissed -> {
                    slidesViewModel.removeSnackBarMessage()
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .focusRequester(requester)
            .focusable()
            .onPreviewKeyEvent { event ->
                slidesViewModel.updateKeyEventDetails(
                    name = event.key.getDisplayName(),
                    symbol = event.key.getDisplaySymbol(),
                )
                true
            },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        slidesState.slidesDetails?.title ?: Localization.SLIDES,
                    )
                },
                navigationIcon = {
                    if (slidesState.slidesDetails?.showBackButton == true) {
                        IconButton(
                            onClick = navigateBack,
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name,
                            )
                        }
                    }
                },
            )
        },
    ) { paddingValues ->
        slidesState.slidesDetails?.let { details ->
            Box(
                modifier = Modifier.fillMaxSize().padding(
                    paddingValues = paddingValues,
                ),
            ) {
                AnimatedVisibility(
                    visible = details.showKeyMap && slidesState.keyEvent != null && slidesState.showKeyEvent,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier.align(
                        Alignment.TopEnd,
                    )
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            Box(
                                modifier = Modifier.background(
                                    color = MaterialTheme.colorScheme.onBackground,
                                    shape = RoundedCornerShape(10)
                                )
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier.padding(10.dp),
                                ) {
                                    slidesState.keyEvent?.first?.let { symbol ->
                                        Text(
                                            symbol,
                                            style = MaterialTheme.typography.headlineSmall.copy(
                                                color = MaterialTheme.colorScheme.background,
                                            ),
                                        )
                                        Spacer(modifier = Modifier.height(5.dp))
                                    }
                                    slidesState.keyEvent?.second?.let { name ->
                                        Text(
                                            name,
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = MaterialTheme.colorScheme.background,
                                            ),
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                }
            }
        }
    }
}