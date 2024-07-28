package app.pages.slides.screen

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.LoadingDialogComposable
import app.pages.slides.screen.composables.KeyMapComposable
import app.pages.slides.screen.composables.SlideChangeTapAreaComposable
import app.pages.slides.state.SlideChangeTap
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
        modifier = Modifier.fillMaxSize().focusRequester(requester).focusable()
            .onPreviewKeyEvent { event ->
                slidesViewModel.updateKeyEventDetails(
                    name = event.key.getDisplayName(),
                    symbol = event.key.getDisplaySymbol(),
                )

                when (event.key) {
                    Key.DirectionLeft -> {
                        slidesViewModel.changeSlide(SlideChangeTap.Left)
                        true
                    }

                    Key.DirectionRight -> {
                        slidesViewModel.changeSlide(SlideChangeTap.Right)
                        true
                    }

                    else -> {
                        false
                    }
                }
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
                KeyMapComposable(
                    modifier = Modifier.align(
                        Alignment.TopEnd,
                    ),
                    showKeyEvent = slidesState.showKeyEvent,
                    keyEvent = slidesState.keyEvent,
                    showKeyMap = details.showKeyMap,
                )
                if (details.allowSlideChangeOnTap) {
                    SlideChangeTapAreaComposable(
                        modifier = Modifier.fillMaxSize(),
                        onLeftTap = {
                            slidesViewModel.changeSlide(SlideChangeTap.Left)
                        },
                        onRightTap = {
                            slidesViewModel.changeSlide(SlideChangeTap.Right)
                        },
                    )
                }
            }
        }
    }
}