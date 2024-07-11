package app.pages.drawBoard.viewModel

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import app.pages.drawBoard.state.DrawBoardState
import app.pages.drawBoard.state.SelectedUiComponents
import app.pages.drawBoard.state.UiComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DrawBoardViewModel : ViewModel() {
    private val _drawBoardState = MutableStateFlow(DrawBoardState())
    val drawBoardState = _drawBoardState.asStateFlow()

    fun updateDraggingState(isDragging: Boolean) {
        _drawBoardState.update {
            it.copy(
                isCurrentlyDraggable = isDragging,
            )
        }
    }

    fun addUiComponentToBoard(uiComponent: UiComponent, offset: Offset) {
        _drawBoardState.value.addedUiComponents.add(
            SelectedUiComponents(
                offset = offset,
                uiComponent = uiComponent,
            )
        )
    }
}