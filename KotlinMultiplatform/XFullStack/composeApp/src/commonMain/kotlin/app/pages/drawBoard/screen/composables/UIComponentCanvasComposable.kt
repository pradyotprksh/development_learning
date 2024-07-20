package app.pages.drawBoard.screen.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.pages.drawBoard.state.UiComponent
import utils.getBottomResizePointerIcon
import utils.getEndResizePointerIcon
import utils.getSelectComponentPointerIcon

@Composable
fun UIComponentCanvasComposable(
    modifier: Modifier = Modifier,
    component: UiComponent,
    updatedHeight: Dp? = null,
    updatedWidth: Dp? = null,
    isSelected: Boolean = false,
    isOnBoard: Boolean = false,
    onDeleteComponent: () -> Unit = {},
    onVerticalDrag: (Float) -> Unit = {},
    onHorizontalDrag: (Float) -> Unit = {},
) {
    var thickness by remember { mutableStateOf(component.borderWidth) }

    val eventModifier = modifier.pointerInput(Unit) {
        awaitPointerEventScope {
            while (true) {
                val event = awaitPointerEvent()
                if (event.type == PointerEventType.Enter) {
                    thickness = 2.dp
                } else if (event.type == PointerEventType.Exit) {
                    thickness = component.borderWidth
                }
            }
        }
    }.pointerHoverIcon(
        getSelectComponentPointerIcon() ?: PointerIcon.Default
    ).onPreviewKeyEvent {
        when {
            (it.key == Key.Backspace) -> {
                onDeleteComponent()
                true
            }

            else -> false
        }
    }

    val color =
        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
    val height = updatedHeight ?: component.height
    val width = updatedWidth ?: component.width

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.width(updatedWidth ?: component.width)
                .alpha(if (isOnBoard && isSelected) 1f else 0f).pointerHoverIcon(
                    getBottomResizePointerIcon() ?: PointerIcon.Default,
                ).draggable(
                    state = rememberDraggableState { delta ->
                        onHorizontalDrag(delta)
                    },
                    orientation = Orientation.Vertical,
                    reverseDirection = true,
                ),
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            VerticalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.height(updatedHeight ?: component.height)
                    .alpha(if (isOnBoard && isSelected) 1f else 0f).pointerHoverIcon(
                        getEndResizePointerIcon() ?: PointerIcon.Default,
                    ).draggable(
                        state = rememberDraggableState { delta ->
                            onVerticalDrag(delta)
                        },
                        orientation = Orientation.Horizontal,
                    ),
            )
            Spacer(modifier = Modifier.width(5.dp).alpha(if (isOnBoard && isSelected) 1f else 0f))
            Canvas(
                modifier = eventModifier.size(
                    height = height,
                    width = width,
                ),
            ) {
                when (component) {
                    is UiComponent.BoxComponent -> {
                        drawRect(
                            color = color,
                            size = size,
                            style = Stroke(
                                width = thickness.value,
                                pathEffect = PathEffect.cornerPathEffect(
                                    radius = 15f,
                                )
                            ),
                        )
                    }

                    is UiComponent.OvalComponent -> {
                        drawOval(
                            color = color,
                            size = size,
                            style = Stroke(
                                width = thickness.value,
                            ),
                        )
                    }

                    is UiComponent.LineComponent -> {
                        val canvasWidth = size.width
                        val canvasHeight = size.height
                        drawLine(
                            color = color,
                            start = Offset(x = canvasWidth, y = 0f),
                            end = Offset(x = 0f, y = canvasHeight),
                            strokeWidth = thickness.value,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(5.dp).alpha(if (isOnBoard && isSelected) 1f else 0f))
            VerticalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.height(updatedHeight ?: component.height)
                    .alpha(if (isOnBoard && isSelected) 1f else 0f).pointerHoverIcon(
                        getEndResizePointerIcon() ?: PointerIcon.Default,
                    ).draggable(
                        state = rememberDraggableState { delta ->
                            onVerticalDrag(delta)
                        },
                        orientation = Orientation.Horizontal,
                    ),
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.width(updatedWidth ?: component.width)
                .alpha(if (isOnBoard && isSelected) 1f else 0f).pointerHoverIcon(
                    getBottomResizePointerIcon() ?: PointerIcon.Default,
                ).draggable(
                    state = rememberDraggableState { delta ->
                        onHorizontalDrag(delta)
                    },
                    orientation = Orientation.Vertical,
                ),
        )
        Spacer(modifier = Modifier.height(5.dp))
    }
}