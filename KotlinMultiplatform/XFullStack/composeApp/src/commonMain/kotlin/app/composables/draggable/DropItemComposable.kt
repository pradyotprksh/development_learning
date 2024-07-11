package app.composables.draggable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned

@Composable
inline fun <reified T> DropItemComposable(
    modifier: Modifier = Modifier,
    content: @Composable (BoxScope.(isInbound: Boolean, dragOffset: Offset, data: T?) -> Unit),
) {
    val dragInfo = LocalDragTargetInfo.current
    val dragPosition = dragInfo.dragPosition
    val dragOffset = dragInfo.dragOffset
    var isCurrentDropTarget by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.onGloballyPositioned {
            it.boundsInWindow().let { rect ->
                isCurrentDropTarget = rect.contains(dragPosition + dragOffset)
            }
        },
    ) {
        val data =
            if (isCurrentDropTarget && !dragInfo.isDragging) if (dragInfo.dataToDrop is T) dragInfo.dataToDrop as T? else null else null
        content(isCurrentDropTarget, dragPosition + dragOffset, data)
    }
}