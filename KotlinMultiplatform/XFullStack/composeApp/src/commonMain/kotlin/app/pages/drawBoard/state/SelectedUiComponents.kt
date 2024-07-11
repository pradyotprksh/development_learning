package app.pages.drawBoard.state

import androidx.compose.ui.geometry.Offset

data class SelectedUiComponents(
    val offset: Offset,
    val uiComponent: UiComponent,
)
