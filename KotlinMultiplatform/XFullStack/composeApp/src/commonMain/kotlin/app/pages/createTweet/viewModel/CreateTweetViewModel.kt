package app.pages.createTweet.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.createTweet.state.CreateTweetState
import app.pages.createTweet.state.TweetDetails
import core.models.request.TweetRequest
import di.SharedModulesDi
import domain.repositories.request.RequestRepository
import domain.repositories.tweet.TweetRepository
import domain.repositories.user.current.CurrentUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import utils.Constants.ConstValues.MAX_POLL_CHOICE_LENGTH
import utils.Constants.ConstValues.MAX_TWEET_CREATION_LIMIT
import utils.Constants.ConstValues.TWEET_MAX_LENGTH
import utils.Localization

class CreateTweetViewModel(
    private val currentUserRepository: CurrentUserRepository = SharedModulesDi.Instance.currentUserRepository,
    private val tweetRepository: TweetRepository = SharedModulesDi.Instance.tweetRepository,
    private val requestRepository: RequestRepository = SharedModulesDi.Instance.requestRepository,
) : ViewModel() {
    private val _createTweetState = MutableStateFlow(CreateTweetState())
    val createTweetState = _createTweetState.asStateFlow()

    fun initialSetup(parentTweetId: String?, isRetweet: Boolean, isReply: Boolean) {
        viewModelScope.launch {
            parentTweetId?.let { getParentTweetDetails(it, isRetweet, isReply) }
            getCurrentUserInfo()
        }
    }

    private suspend fun getParentTweetDetails(
        tweetId: String,
        isRetweet: Boolean,
        isReply: Boolean,
    ) {
        tweetRepository.getTweetDetails(tweetId)?.let { parentTweetDetails ->
            val tweets = _createTweetState.value.tweets.toMutableList()
            val firstTweet = tweets.removeAt(0)
            tweets.add(
                0, firstTweet.copy(
                    parentTweetId = tweetId,
                    parentTweetDetails = parentTweetDetails,
                    isRepostTweet = isRetweet,
                    isReplyTweet = isReply,
                    label = if (isRetweet) Localization.ADD_A_COMMENT else Localization.POST_YOUR_REPLY,
                )
            )
            _createTweetState.value = _createTweetState.value.copy(
                tweets = tweets,
                enableAddNewTweetButton = isRetweet,
                showAddNewTweetButton = !isRetweet,
            )
        }
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
        val repostTweets = mutableListOf<TweetDetails>()
        if (value.length <= TWEET_MAX_LENGTH) {
            val deletedTweet = tweets.removeAt(index)
            val isQuoteTweet =
                deletedTweet.parentTweetId != null && deletedTweet.parentTweetDetails != null && value.isNotBlank() && !deletedTweet.isReplyTweet
            val isRepostTweet =
                deletedTweet.parentTweetId != null && deletedTweet.parentTweetDetails != null && value.isBlank() && !deletedTweet.isReplyTweet
            tweets.add(
                index,
                deletedTweet.copy(
                    tweet = value,
                    isQuoteTweet = isQuoteTweet,
                    isRepostTweet = isRepostTweet,
                ),
            )

            if (isRepostTweet) {
                repostTweets.add(tweets[0])
                repeat((1..<MAX_TWEET_CREATION_LIMIT).count()) {
                    repostTweets.add(TweetDetails())
                }
            }

            _createTweetState.value = _createTweetState.value.copy(
                tweets = if (isRepostTweet) repostTweets else tweets,
                currentSelectedTweetLength = value.length,
                showAddNewTweetButton = !isRepostTweet,
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

    fun updateCurrentTweetPollStatus(isAPoll: Boolean) {
        val currentFocusedTweetIndex = _createTweetState.value.currentFocusedTweetIndex
        if (currentFocusedTweetIndex >= 0) {
            val tweets = _createTweetState.value.tweets.toMutableList()
            val deletedTweet = tweets.removeAt(currentFocusedTweetIndex)
            tweets.add(
                currentFocusedTweetIndex,
                deletedTweet.copy(
                    isAPoll = isAPoll,
                    pollChoices = List(
                        size = 2, init = { "" },
                    ),
                    label = if (isAPoll) Localization.ASK_A_QUESTION else if (deletedTweet.index == 0) Localization.WHATS_HAPPENING else Localization.ADD_ANOTHER_TWEET,
                ),
            )
            _createTweetState.value = _createTweetState.value.copy(
                tweets = tweets,
            )
        }
    }

    fun addNewPollChoice(index: Int) {
        val tweets = _createTweetState.value.tweets.toMutableList()
        val deletedTweet = tweets.removeAt(index)
        tweets.add(
            index,
            deletedTweet.copy(
                pollChoices = deletedTweet.pollChoices + "",
            ),
        )
        _createTweetState.value = _createTweetState.value.copy(
            tweets = tweets,
        )
    }

    fun updatePollChoiceValue(tweetIndex: Int, choiceIndex: Int, value: String) {
        if (value.length <= MAX_POLL_CHOICE_LENGTH) {
            val tweets = _createTweetState.value.tweets.toMutableList()
            val deletedTweet = tweets.removeAt(tweetIndex)
            val newChoices = deletedTweet.pollChoices.toMutableList()
            newChoices.removeAt(choiceIndex)
            newChoices.add(choiceIndex, value)
            tweets.add(
                tweetIndex,
                deletedTweet.copy(
                    pollChoices = newChoices,
                ),
            )
            _createTweetState.value = _createTweetState.value.copy(
                tweets = tweets,
            )
        }
    }

    fun createTweet(navigateBack: () -> Unit) {
        val tweetRequests = createTweetState.value.tweets.filter {
            it.shouldSelectThisTweet()
        }
        if (tweetRequests.isNotEmpty()) {
            viewModelScope.launch {
                requestRepository.saveTweetRequests(
                    tweetRequests.map {
                        TweetRequest(
                            tweet = it.tweet.trim(),
                            media = it.media,
                            gif = it.gifs,
                            isAPoll = it.isAPoll,
                            pollChoices = it.pollChoices,
                            pollHour = it.pollHour,
                            pollMinute = it.pollMinute,
                            pollSeconds = it.pollSeconds,
                            location = it.location,
                            isScheduledTweet = it.isScheduledTweet,
                            scheduledOnTweet = it.scheduledOnTweet,
                            isQuoteTweet = it.isQuoteTweet,
                            isRepostTweet = it.isRepostTweet,
                            isACommentTweet = it.isReplyTweet,
                            parentTweetId = it.parentTweetId,
                        )
                    },
                )
                navigateBack()
            }
        }
    }

    fun updatePollTime(index: Int, hour: Long?, minute: Long?, seconds: Long?) {
        val tweets = _createTweetState.value.tweets.toMutableList()
        val deletedTweet = tweets.removeAt(index)
        tweets.add(
            index,
            deletedTweet.copy(
                pollHour = hour,
                pollMinute = minute,
                pollSeconds = seconds,
            ),
        )
        _createTweetState.value = _createTweetState.value.copy(
            tweets = tweets,
        )
    }
}