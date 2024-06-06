package app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.models.response.ClientResponse
import core.parser.parseToTweetRequest
import di.ModulesDi
import domain.repositories.tweet.TweetRepository
import domain.repositories.view.ViewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.kodein.di.instance

class XAppViewModel : ViewModel() {
    private val tweetRepository: TweetRepository by ModulesDi.di.instance()
    private val viewRepository: ViewRepository by ModulesDi.di.instance()

    private val _xAppState = MutableStateFlow(XAppState())
    val xAppState = _xAppState.asStateFlow()

    fun initSetup() {
        listenToAddTweet()
        listenToViewAddition()
    }

    private fun listenToViewAddition() {
        viewModelScope.launch {
            viewRepository.listenOnViewAdd().collect { viewsDb ->
                if (viewsDb.size > 10) {
                    viewRepository.saveViews(viewsDb)
                }
            }
        }
    }

    private fun listenToAddTweet() {
        viewModelScope.launch {
            tweetRepository.allTweetRequestChanges().collect { tweetRequestsDb ->
                tweetRequestsDb.forEach { requestDb ->
                    val tweetRequest = requestDb.parseToTweetRequest()
                    if (tweetRequest.isNotEmpty()) {
                        tweetRepository.uploadTweets(tweetRequest).collect {
                            when (it) {
                                is ClientResponse.Error -> showMessage(it.message)
                                is ClientResponse.Success -> {
                                    tweetRepository.deleteTweetRequest(requestDb.requestId.toHexString())
                                    it.data.message?.let { message -> showMessage(message) }
                                }

                                else -> {}
                            }
                        }
                    } else {
                        tweetRepository.deleteTweetRequest(requestDb.requestId.toHexString())
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