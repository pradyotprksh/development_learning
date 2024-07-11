package app.composables.draggable

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned

val LocalDragTargetInfo = compositionLocalOf { DragTargetInfo() }

@Composable
fun <T> DragTargetComposable(
    modifier: Modifier = Modifier,
    dataToDrop: T,
    onDragStart: () -> Unit,
    onDragEnd: () -> Unit,
    content: @Composable () -> Unit,
) {
    var currentPosition by remember { mutableStateOf(Offset.Zero) }
    val currentState = LocalDragTargetInfo.current

    Box(
        modifier = modifier.onGloballyPositioned {
            currentPosition = it.localToWindow(Offset.Zero)
        }.pointerInput(Unit) {
            detectDragGesturesAfterLongPress(
                onDragStart = {
                    onDragStart()
                    currentState.dataToDrop = dataToDrop
                    currentState.isDragging = true
                    currentState.dragPosition = currentPosition + it
                    currentState.draggableComposable = content
                },
                onDrag = { change, dragAmount ->
                    change.consume()
                    currentState.dragOffset += Offset(dragAmount.x, dragAmount.y)
                },
                onDragEnd = {
                    onDragEnd()
                    currentState.dragOffset = Offset.Zero
                    currentState.isDragging = false
                },
                onDragCancel = {
                    onDragEnd()
                    currentState.dragOffset = Offset.Zero
                    currentState.isDragging = false
                },
            )
        },
    ) {
        content()
    }
}