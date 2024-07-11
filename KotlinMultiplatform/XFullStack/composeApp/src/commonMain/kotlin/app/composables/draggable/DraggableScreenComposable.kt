package app.composables.draggable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

@Composable
fun DraggableScreenComposable(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val state = remember { DragTargetInfo() }

    CompositionLocalProvider(LocalDragTargetInfo provides state) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            content()

            if (state.isDragging) {
                var targetSize by remember { mutableStateOf(IntSize.Zero) }

                Box(
                    modifier = Modifier.graphicsLayer {
                        val offset = state.dragPosition + state.dragOffset
                        scaleX = 1f
                        scaleY = 1f
                        alpha = if (targetSize == IntSize.Zero) 0f else 1f
                        translationX = offset.x.minus(targetSize.width / 2)
                        translationY = offset.y.minus(targetSize.height / 2)
                    }.onGloballyPositioned {
                        targetSize = it.size
                    }
                ) {
                    state.draggableComposable?.invoke()
                }
            }
        }
    }
}