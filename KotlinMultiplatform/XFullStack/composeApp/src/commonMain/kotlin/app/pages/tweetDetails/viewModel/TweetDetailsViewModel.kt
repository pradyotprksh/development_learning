package app.pages.tweetDetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.tweetDetails.state.TweetDetailsState
import core.models.request.TweetRequest
import di.SharedModulesDi
import domain.repositories.tweet.TweetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import utils.Constants.ConstValues.TWEET_MAX_LENGTH

class TweetDetailsViewModel(
    private val tweetRepository: TweetRepository = SharedModulesDi.Instance.tweetRepository,
) : ViewModel() {
    private val _tweetDetailsScreenState = MutableStateFlow(TweetDetailsState())
    val tweetDetailsScreen = _tweetDetailsScreenState.asStateFlow()

    fun initialSetup(tweetId: String) {
        listenToTweetDetails(tweetId)
        listenToTweetReplies(tweetId)
    }

    private fun listenToTweetDetails(tweetId: String) {
        viewModelScope.launch {
            tweetRepository.getTweetChanges(tweetId).collect {
                _tweetDetailsScreenState.value = tweetDetailsScreen.value.copy(
                    tweet = it,
                )
            }
        }
    }

    private fun listenToTweetReplies(tweetId: String) {
        viewModelScope.launch {
            tweetRepository.getAllTweetsReplyFor(tweetId).collect {
                _tweetDetailsScreenState.value = tweetDetailsScreen.value.copy(
                    replies = it,
                )
            }
        }
    }

    fun removeSnackBarMessage() {
        _tweetDetailsScreenState.value = _tweetDetailsScreenState.value.copy(
            snackBarMessage = null
        )
    }

    fun updateTweetReply(value: String) {
        if (value.length <= TWEET_MAX_LENGTH) {
            _tweetDetailsScreenState.value = _tweetDetailsScreenState.value.copy(
                replyTweet = value,
            )
        }
    }

    fun replyTextFieldFocusChanged(focused: Boolean) {
        _tweetDetailsScreenState.value = _tweetDetailsScreenState.value.copy(
            isReplyTweetFocused = focused,
        )
    }

    fun replyToTweet(tweetId: String) {
        val replyTweet = _tweetDetailsScreenState.value.replyTweet
        if (replyTweet.isNotBlank()) {
            viewModelScope.launch {
                tweetRepository.saveTweetRequests(
                    listOf(
                        TweetRequest(
                            tweet = replyTweet.trim(),
                            isACommentTweet = true,
                            parentTweetId = tweetId,
                        ),
                    )
                )
                updateTweetReply("")
            }
        }
    }
}