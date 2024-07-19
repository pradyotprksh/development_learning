package app.pages.drawBoard.viewModel

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
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
                currentHeight = uiComponent.height,
                currentWidth = uiComponent.width,
            )
        )
        _drawBoardState.update {
            it.copy(
                selectedUiComponentIndex = mutableSetOf(it.addedUiComponents.lastIndex)
            )
        }
    }

    fun updateSelectedUiComponent(index: Int) {
        _drawBoardState.update {
            it.copy(
                selectedUiComponentIndex = mutableSetOf(index)
            )
        }
    }

    fun deleteTheComponent(index: Int) {
        _drawBoardState.value.let { state ->
            val addedUiComponents = state.addedUiComponents
            addedUiComponents.removeAt(index)
            val selectedUiComponentIndex = state.selectedUiComponentIndex
            selectedUiComponentIndex.remove(index)
            _drawBoardState.update {
                it.copy(
                    addedUiComponents = addedUiComponents.toMutableList(),
                    selectedUiComponentIndex = selectedUiComponentIndex.toMutableSet(),
                )
            }
        }
    }

    fun changeInSize(drag: Float, direction: DragDirection, index: Int) {
        val mutableComponents = _drawBoardState.value.addedUiComponents.toMutableList()
        val removedItem = mutableComponents.removeAt(index)

        val width = if (direction == DragDirection.Vertical) {
            val newWidth = removedItem.currentWidth + drag.dp
            if (newWidth < removedItem.uiComponent.width) {
                removedItem.currentWidth
            } else {
                newWidth
            }
        } else removedItem.currentWidth
        val height = if (direction == DragDirection.Horizontal) {
            val newHeight = removedItem.currentHeight + drag.dp
            if (newHeight < removedItem.uiComponent.height) {
                removedItem.currentHeight
            } else {
                newHeight
            }
        } else removedItem.currentHeight

        val updatedComponent = removedItem.copy(
            currentWidth = width,
            currentHeight = height,
        )
        mutableComponents.add(index, updatedComponent)
        _drawBoardState.update {
            it.copy(
                addedUiComponents = mutableComponents,
            )
        }
    }
}