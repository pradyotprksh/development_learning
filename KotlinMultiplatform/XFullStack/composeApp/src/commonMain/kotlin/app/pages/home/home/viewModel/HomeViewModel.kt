package app.pages.home.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.home.home.state.HomeState
import core.models.request.TweetRequest
import core.models.response.ClientResponse
import di.SharedModulesDi
import domain.repositories.chat.ChatRepository
import domain.repositories.request.RequestRepository
import domain.repositories.tweet.TweetRepository
import domain.repositories.user.current.CurrentUserRepository
import domain.repositories.view.ViewRepository
import domain.repositories.websocket.WebsocketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import utils.Constants.ConstValues.DEFAULT_PAGINATE_LIMIT
import utils.Constants.Keys.FOLLOWING_SCROLL_POSITION
import utils.Constants.Keys.FOR_YOU_SCROLL_POSITION
import utils.Constants.SuccessCode.FOLLOW_UPDATE_SUCCESS
import utils.Constants.SuccessCode.MESSAGE_UPDATE_SUCCESS
import utils.Constants.SuccessCode.TWEETS_DELETED_SUCCESS_CODE
import utils.Constants.SuccessCode.TWEETS_UPDATE_SUCCESS_CODE

class HomeViewModel(
    private val currentUserRepository: CurrentUserRepository = SharedModulesDi.Instance.currentUserRepository,
    private val tweetRepository: TweetRepository = SharedModulesDi.Instance.tweetRepository,
    private val requestRepository: RequestRepository = SharedModulesDi.Instance.requestRepository,
    private val websocketRepository: WebsocketRepository = SharedModulesDi.Instance.websocketRepository,
    private val viewRepository: ViewRepository = SharedModulesDi.Instance.viewRepository,
    private val chatRepository: ChatRepository = SharedModulesDi.Instance.chatRepository,
) : ViewModel() {
    private val _homeScreenState = MutableStateFlow(HomeState())
    val homeScreenState = _homeScreenState.asStateFlow()

    init {
        startSocketListener()
        getAllTweets()
        getAllFollowingTweets()
    }

    private fun startSocketListener() {
        viewModelScope.launch {
            websocketRepository.connectAndListen().mapNotNull { it }.collect {
                if (it == TWEETS_UPDATE_SUCCESS_CODE) {
                    updateAllTweets(
                        1,
                        _homeScreenState.value.forYouTweets.size,
                    )
                } else if (it.contains(TWEETS_DELETED_SUCCESS_CODE)) {
                    val split = it.split(" ")
                    val code = split.getOrNull(0)
                    val tweetId = split.getOrNull(1)
                    if (code == TWEETS_DELETED_SUCCESS_CODE) {
                        tweetId?.let { tweetRepository.deleteTweetById(tweetId) }
                    }
                } else if (it == FOLLOW_UPDATE_SUCCESS) {
                    updateAllTweets(
                        1,
                        _homeScreenState.value.forYouTweets.size,
                    )
                    updateUserInfo()
                } else if (it.contains(MESSAGE_UPDATE_SUCCESS)) {
                    val split = it.split(" ")
                    val code = split.getOrNull(0)
                    val chatId = split.getOrNull(1)
                    if (code == MESSAGE_UPDATE_SUCCESS) {
                        chatId?.let { updateChatDetails(chatId) }
                    }
                }
            }
        }
    }

    private suspend fun updateChatDetails(chatId: String) {
        chatRepository.updateMessage(chatId).collect {
            when (it) {
                is ClientResponse.Error -> showMessage(it.message)
                else -> {}
            }
        }
    }

    private suspend fun updateUserInfo() {
        currentUserRepository.updateUserInfo().collect {
            when (it) {
                is ClientResponse.Error -> showMessage(it.message)
                else -> {}
            }
        }
    }

    private fun showMessage(message: String) {
        _homeScreenState.value = _homeScreenState.value.copy(
            snackBarMessage = message,
        )
    }

    fun removeSnackBarMessage() {
        _homeScreenState.value = _homeScreenState.value.copy(
            snackBarMessage = null
        )
    }

    private fun updateLoaderState(showLoader: Boolean) {
        _homeScreenState.value = _homeScreenState.value.copy(
            showLoading = showLoader
        )
    }

    suspend fun initialSetup() {
        updateAllTweets(
            page = _homeScreenState.value.tweetPage,
            limit = DEFAULT_PAGINATE_LIMIT,
        )
    }

    fun updateTweetsPage() {
        if (_homeScreenState.value.isForYouNewTweetFound) {
            viewModelScope.launch {
                _homeScreenState.value = _homeScreenState.value.copy(
                    tweetPage = _homeScreenState.value.tweetPage + 1,
                )
                updateAllTweets(
                    page = _homeScreenState.value.tweetPage,
                    limit = 10,
                )
            }
        }
    }

    private suspend fun updateAllTweets(
        page: Int,
        limit: Int,
    ) {
        tweetRepository.updateAllTweets(
            page,
            limit,
        ).collect {
            when (it) {
                is ClientResponse.Error -> showMessage(it.message)
                ClientResponse.Idle -> {
                    updatePaginateState(true)
                    updateLoaderState(false)
                }

                ClientResponse.Loading -> {
                    updatePaginateState(false)
                    updateLoaderState(!_homeScreenState.value.firstLoadDone)
                }

                is ClientResponse.Success -> {
                    _homeScreenState.value = _homeScreenState.value.copy(
                        firstLoadDone = true,
                    )
                }
            }
        }
    }

    private fun updatePaginateState(value: Boolean) {
        _homeScreenState.value = _homeScreenState.value.copy(
            canPaginate = value,
        )
    }

    private fun getAllTweets() {
        viewModelScope.launch {
            tweetRepository.allTweetsChanges().collect {
                _homeScreenState.value = _homeScreenState.value.copy(
                    forYouTweets = it,
                    isForYouNewTweetFound = it.size != _homeScreenState.value.forYouTweets.size,
                )
            }
        }
    }

    private fun getAllFollowingTweets() {
        viewModelScope.launch {
            currentUserRepository.getUserId()?.let { userId ->
                tweetRepository.allFollowingTweetsChanges(userId).collect {
                    _homeScreenState.value = _homeScreenState.value.copy(
                        followingTweets = it,
                        isFollowingNewTweetFound = it.size != _homeScreenState.value.followingTweets.size,
                    )
                }
            }
        }
    }

    fun updatePollOption(tweetId: String, optionId: String) {
        viewModelScope.launch {
            requestRepository.savePollOptionRequest(tweetId, optionId)
        }
    }

    fun onLikeTweet(tweetId: String) {
        val tweetRequest = TweetRequest(
            tweet = null,
            media = emptyList(),
            gif = emptyList(),
            isAPoll = false,
            pollChoices = emptyList(),
            pollHour = null,
            pollMinute = null,
            pollSeconds = null,
            location = null,
            isScheduledTweet = false,
            scheduledOnTweet = null,
            isQuoteTweet = false,
            isLikedTweet = true,
            isRepostTweet = false,
            isACommentTweet = false,
            parentTweetId = tweetId,
        )
        viewModelScope.launch {
            requestRepository.saveTweetRequests(listOf(tweetRequest))
        }
    }

    fun updateViewForTweet(tweetId: String) {
        viewModelScope.launch {
            viewRepository.saveView(tweetId)
        }
    }

    suspend fun updateForYouScrollPosition(index: Int) {
        currentUserRepository.updateScrollPosition(
            FOR_YOU_SCROLL_POSITION,
            index,
        )
    }

    fun getForYouScrollPosition() = currentUserRepository.getScrollPosition(FOR_YOU_SCROLL_POSITION)

    suspend fun updateFollowingScrollPosition(index: Int) {
        currentUserRepository.updateScrollPosition(
            FOLLOWING_SCROLL_POSITION,
            index,
        )
    }

    fun getFollowingScrollPosition() =
        currentUserRepository.getScrollPosition(FOLLOWING_SCROLL_POSITION)

    fun bookmarkUpdate(tweetId: String) {
        viewModelScope.launch {
            requestRepository.saveBookmarkRequest(tweetId)
        }
    }
}