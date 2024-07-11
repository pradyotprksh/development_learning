package app.pages.drawBoard.screen.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import app.pages.drawBoard.state.UiComponents

@Composable
fun UiComponentComposable(
    modifier: Modifier = Modifier,
    component: UiComponents,
) {
    var thickness by remember { mutableStateOf(component.borderWidth) }

    val eventModifier = Modifier.pointerInput(Unit) {
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
    }

    Column {
        Spacer(modifier = Modifier.height(5.dp))
        if (component is UiComponents.LineComponent) {
            HorizontalDivider(
                thickness = thickness,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = eventModifier.width(component.width),
            )
        } else {
            Box(
                modifier = eventModifier.height(component.height).width(component.width).border(
                    width = thickness,
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = component.shape ?: RectangleShape,
                ),
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
    }
}