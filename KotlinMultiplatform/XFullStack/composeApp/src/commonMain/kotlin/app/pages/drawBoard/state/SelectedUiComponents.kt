package app.pages.drawBoard.state

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp

data class SelectedUiComponents(
    val offset: Offset,
    val currentHeight: Dp,
    val currentWidth: Dp,
    val uiComponent: UiComponent,
)
