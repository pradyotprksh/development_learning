package app.pages.directMessage.viewModel

import androidx.lifecycle.ViewModel
import app.pages.directMessage.state.DirectMessageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DirectMessageViewModel : ViewModel() {
    private val _directMessageStateState = MutableStateFlow(DirectMessageState())
    val directMessageStateState = _directMessageStateState.asStateFlow()
}