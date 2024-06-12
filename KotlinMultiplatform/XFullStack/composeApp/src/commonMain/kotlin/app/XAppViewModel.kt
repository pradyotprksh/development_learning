package app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.models.response.ClientResponse
import di.SharedModulesDi
import domain.repositories.tweet.TweetRepository
import domain.repositories.view.ViewRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class XAppViewModel(
    private val tweetRepository: TweetRepository = SharedModulesDi.Instance.tweetRepository,
    private val viewRepository: ViewRepository = SharedModulesDi.Instance.viewRepository,
) : ViewModel() {
    private val _xAppState = MutableStateFlow(XAppState())
    val xAppState = _xAppState.asStateFlow()

    fun initSetup() {
        listenToAddTweet()
        listenToViewAddition()
    }

    @OptIn(FlowPreview::class)
    private fun listenToViewAddition() {
        viewModelScope.launch {
            viewRepository.listenOnViewAdd().debounce(1000).collect { viewsDb ->
                viewRepository.saveViews(viewsDb)
            }
        }
    }

    private fun listenToAddTweet() {
        viewModelScope.launch {
            tweetRepository.allTweetRequestChanges().collect { tweetRequestsDb ->
                tweetRequestsDb.forEach { requestDb ->
                    tweetRepository.uploadTweets(requestDb).collect {
                        when (it) {
                            is ClientResponse.Error -> showMessage(it.message)
                            is ClientResponse.Success -> {
                                it.data.message?.let { message -> showMessage(message) }
                            }

                            else -> {}
                        }
                    }
                }
            }
        }
    }

    private fun showMessage(message: String) {
        _xAppState.value = _xAppState.value.copy(
            snackBarMessage = message,
        )
    }

    fun removeSnackBarMessage() {
        _xAppState.value = _xAppState.value.copy(
            snackBarMessage = null
        )
    }
}