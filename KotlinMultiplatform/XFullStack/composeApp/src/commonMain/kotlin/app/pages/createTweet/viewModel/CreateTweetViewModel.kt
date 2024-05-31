package app.pages.createTweet.viewModel

import androidx.lifecycle.ViewModel
import app.pages.createTweet.state.CreateTweetState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateTweetViewModel : ViewModel() {
    private val _createTweetState = MutableStateFlow(CreateTweetState())
    val createTweetState = _createTweetState.asStateFlow()
}