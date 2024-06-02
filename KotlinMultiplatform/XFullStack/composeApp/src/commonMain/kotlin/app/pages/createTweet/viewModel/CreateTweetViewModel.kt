package app.pages.createTweet.viewModel

import androidx.lifecycle.ViewModel
import app.pages.createTweet.state.CreateTweetState
import core.parser.parseToTweetRequest
import di.ModulesDi
import domain.repositories.user.current.CurrentUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.kodein.di.instance
import utils.Constants.ConstValues.MAX_TWEET_CREATION_LIMIT
import utils.Constants.ConstValues.TWEET_MAX_LENGTH

class CreateTweetViewModel : ViewModel() {
    private val currentUserRepository: CurrentUserRepository by ModulesDi.di.instance()

    private val _createTweetState = MutableStateFlow(CreateTweetState())
    val createTweetState = _createTweetState.asStateFlow()

    suspend fun initialSetup() {
        getCurrentUserInfo()
    }

    private suspend fun getCurrentUserInfo() {
        currentUserRepository.getUserId()?.let { currentUserId ->
            currentUserRepository.userInfoChanges(currentUserId).collect {
                if (it != null) {
                    _createTweetState.value = _createTweetState.value.copy(
                        profileImage = it.profilePicture,
                    )
                }
            }
        }
    }

    fun updateTweet(value: String, index: Int) {
        val tweets = _createTweetState.value.tweets.toMutableList()
        if (value.length < TWEET_MAX_LENGTH) {
            val deletedTweet = tweets.removeAt(index)
            tweets.add(
                index,
                deletedTweet.copy(
                    tweet = value,
                ),
            )

            _createTweetState.value = _createTweetState.value.copy(
                tweets = tweets,
                currentSelectedTweetLength = value.length,
            )

            multipleTweetsToTweet()
            updateAddTweetButton()
        }
    }

    private fun updateAddTweetButton() {
        val isVisibleAndEmpty =
            _createTweetState.value.tweets.filter { it.isVisible && it.tweet.isBlank() }
        val isVisibleAndNotEmpty =
            _createTweetState.value.tweets.filter { it.isVisible && it.tweet.isNotBlank() }
        if (isVisibleAndEmpty.isNotEmpty()) {
            _createTweetState.value = _createTweetState.value.copy(
                enableAddNewTweetButton = false,
            )
        } else {
            if (isVisibleAndNotEmpty.isNotEmpty()) {
                val totalVisible = _createTweetState.value.tweets.filter { it.isVisible }
                _createTweetState.value = _createTweetState.value.copy(
                    enableAddNewTweetButton = totalVisible.size < MAX_TWEET_CREATION_LIMIT,
                )
            }
        }
    }

    fun focusChange(index: Int) {
        val tweetDetails = _createTweetState.value.tweets[index]
        _createTweetState.value = _createTweetState.value.copy(
            currentSelectedTweetLength = tweetDetails.tweet.length,
            currentFocusedTweetIndex = index,
        )

        multipleTweetsToTweet()
        updateAddTweetButton()
    }

    fun addNewTweetField() {
        val tweets = _createTweetState.value.tweets.toMutableList()
        val lastEnabledTweet = tweets.findLast { it.isVisible }
        lastEnabledTweet?.let { details ->
            val nextIndex = details.index + 1
            if (nextIndex < MAX_TWEET_CREATION_LIMIT) {
                val deletedTweet = tweets.removeAt(nextIndex)
                tweets.add(
                    nextIndex,
                    deletedTweet.copy(
                        isVisible = true,
                    ),
                )
                _createTweetState.value = _createTweetState.value.copy(
                    tweets = tweets,
                )

                multipleTweetsToTweet()
                updateAddTweetButton()
            }
        }
    }

    fun deleteTweet(index: Int) {
        if (index > 0) {
            val tweets = _createTweetState.value.tweets.toMutableList()
            val deletedTweet = tweets.removeAt(index)
            tweets.add(
                index,
                deletedTweet.copy(
                    isVisible = false,
                ),
            )

            _createTweetState.value = _createTweetState.value.copy(
                tweets = tweets,
                currentSelectedTweetLength = 0,
            )

            multipleTweetsToTweet()
            updateAddTweetButton()
        }
    }

    private fun multipleTweetsToTweet() {
        _createTweetState.value = _createTweetState.value.copy(
            multipleTweets = _createTweetState.value.tweets.filter { it.tweet.isNotBlank() }.size > 1,
        )
    }

    fun updateCurrentTweetToPoll() {
        val tweets = _createTweetState.value.tweets.toMutableList()
        val currentFocusedTweetIndex = _createTweetState.value.currentFocusedTweetIndex
        val deletedTweet = tweets.removeAt(currentFocusedTweetIndex)
        tweets.add(
            currentFocusedTweetIndex,
            deletedTweet.copy(isAPoll = true, pollChoices = List(size = 2, init = { "" })),
        )
        _createTweetState.value = _createTweetState.value.copy(
            tweets = tweets,
        )
    }

    fun createTweet() {
        val tweetRequests = _createTweetState.value.tweets.filter {
            it.shouldSelectThisTweet()
        }.map { it.parseToTweetRequest() }
    }
}