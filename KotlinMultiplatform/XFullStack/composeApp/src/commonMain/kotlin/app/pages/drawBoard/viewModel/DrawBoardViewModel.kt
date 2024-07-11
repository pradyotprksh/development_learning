package app.pages.drawBoard.viewModel

import androidx.lifecycle.ViewModel
import app.pages.drawBoard.state.DrawBoardState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DrawBoardViewModel : ViewModel() {
    private val _drawBoardState = MutableStateFlow(DrawBoardState())
    val drawBoardState = _drawBoardState.asStateFlow()
}