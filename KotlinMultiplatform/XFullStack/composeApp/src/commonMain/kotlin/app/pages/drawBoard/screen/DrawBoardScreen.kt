package app.pages.drawBoard.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.draggable.DragTargetComposable
import app.composables.draggable.DraggableScreenComposable
import app.composables.draggable.DropItemComposable
import app.pages.drawBoard.screen.composables.UiComponentComposable
import app.pages.drawBoard.state.UiComponent
import app.pages.drawBoard.viewModel.DrawBoardViewModel
import utils.Localization

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawBoardScreen(
    showDrawBoardOption: Boolean,
    drawBoardViewModel: DrawBoardViewModel = viewModel { DrawBoardViewModel() },
    navigateBack: () -> Unit,
) {
    LaunchedEffect(showDrawBoardOption) {
        if (!showDrawBoardOption) {
            navigateBack()
        }
    }

    val drawBoardState = drawBoardViewModel.drawBoardState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        Localization.DRAW_BOARD,
                    )
                },
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
    ) { paddingValues ->
        DraggableScreenComposable {
            Row {
                LazyColumn(
                    modifier = Modifier.padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding(),
                        start = paddingValues.calculateStartPadding(LocalLayoutDirection.current) + 10.dp,
                        end = 10.dp,
                    )
                ) {
                    items(drawBoardState.value.components) { component ->
                        DragTargetComposable(
                            dataToDrop = component,
                            onDragEnd = {
                                drawBoardViewModel.updateDraggingState(false)
                            },
                            onDragStart = {
                                drawBoardViewModel.updateDraggingState(true)
                            },
                        ) {
                            UiComponentComposable(
                                component = component,
                            )
                        }
                    }
                }
                VerticalDivider(
                    modifier = Modifier.fillMaxHeight()
                )
                AnimatedVisibility(
                    visible = drawBoardState.value.isCurrentlyDraggable,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier.fillMaxSize().padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding(),
                        end = paddingValues.calculateEndPadding(LocalLayoutDirection.current) + 10.dp,
                        start = 10.dp,
                    )
                ) {
                    DropItemComposable<UiComponent>(
                        modifier = Modifier.fillMaxSize()
                    ) { isInbound, dragOffset, data ->
                        if (data != null && isInbound) {
                            drawBoardViewModel.addUiComponentToBoard(
                                data,
                                dragOffset,
                            )
                        }

                        Box(
                            modifier = Modifier.fillMaxSize().border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(size = 5.dp)
                            )
                        ) {
                            Text(
                                Localization.DROP_COMPONENT_HERE,
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }
                    }
                }
                drawBoardState.value.addedUiComponents.forEach { componentDetails ->
                    UiComponentComposable(
                        modifier = Modifier.offset(
                            x = componentDetails.offset.x.dp, y = componentDetails.offset.y.dp
                        ),
                        component = componentDetails.uiComponent,
                    )
                }
            }
        }
    }
}