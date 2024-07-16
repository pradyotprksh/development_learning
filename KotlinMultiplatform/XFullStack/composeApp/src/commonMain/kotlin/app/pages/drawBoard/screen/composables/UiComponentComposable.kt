package app.pages.drawBoard.screen.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import app.pages.drawBoard.state.UiComponent
import utils.getBottomResizePointerIcon
import utils.getEndResizePointerIcon
import utils.getSelectComponentPointerIcon
import utils.getStartResizePointerIcon
import utils.getTopResizePointerIcon

@Composable
fun UiComponentComposable(
    modifier: Modifier = Modifier,
    component: UiComponent,
    isSelected: Boolean = false,
    isOnBoard: Boolean = false,
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
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.width(component.width)
                .alpha(if (isOnBoard && isSelected) 1f else 0f).pointerHoverIcon(
                    getTopResizePointerIcon() ?: PointerIcon.Default,
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
                modifier = Modifier.height(component.height)
                    .alpha(if (isOnBoard && isSelected) 1f else 0f).pointerHoverIcon(
                        getStartResizePointerIcon() ?: PointerIcon.Default,
                    ),
            )
            Spacer(modifier = Modifier.width(5.dp))
            if (component is UiComponent.LineComponent) {
                HorizontalDivider(
                    thickness = thickness + if (isSelected) 1.dp else 0.dp,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                    modifier = eventModifier.width(component.width),
                )
            } else {
                Box(
                    modifier = eventModifier.height(component.height).width(component.width).border(
                        width = thickness + if (isSelected) 1.dp else 0.dp,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                        shape = component.shape ?: RectangleShape,
                    ),
                )
            }
            Spacer(modifier = Modifier.width(5.dp).alpha(if (isOnBoard && isSelected) 1f else 0f))
            VerticalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.height(component.height)
                    .alpha(if (isOnBoard && isSelected) 1f else 0f).pointerHoverIcon(
                        getEndResizePointerIcon() ?: PointerIcon.Default,
                    ),
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.width(component.width)
                .alpha(if (isOnBoard && isSelected) 1f else 0f).pointerHoverIcon(
                    getBottomResizePointerIcon() ?: PointerIcon.Default,
                ),
        )
        Spacer(modifier = Modifier.height(5.dp))
    }
}