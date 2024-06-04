package app.pages.home.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.home.home.state.HomeState
import core.models.response.ClientResponse
import data.request.TweetRequest
import di.ModulesDi
import domain.repositories.tweet.TweetRepository
import domain.repositories.websocket.WebsocketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.kodein.di.instance
import utils.Constants.ConstValues.DEFAULT_PAGINATE_LIMIT
import utils.Constants.SuccessCode.TWEETS_UPDATE_SUCCESS_CODE

class HomeViewModel : ViewModel() {
    private val tweetRepository: TweetRepository by ModulesDi.di.instance()
    private val websocketRepository: WebsocketRepository by ModulesDi.di.instance()

    init {
        viewModelScope.launch {
            startTweetUpdateListener()
            getAllTweets()
        }
    }

    private fun startTweetUpdateListener() {
        viewModelScope.launch {
            websocketRepository.connectAndListen().collect {
                when (it) {
                    TWEETS_UPDATE_SUCCESS_CODE -> updateForYouAllTweets(
                        1,
                        _homeScreenState.value.forYouTweets.size,
                    )
                }
            }
        }
    }

    private val _homeScreenState = MutableStateFlow(HomeState())
    val homeScreenState = _homeScreenState.asStateFlow()

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
        updateForYouAllTweets(
            page = _homeScreenState.value.forYouTweetPage,
            limit = DEFAULT_PAGINATE_LIMIT,
        )
    }

    fun updateTweetsPage() {
        viewModelScope.launch {
            _homeScreenState.value = _homeScreenState.value.copy(
                forYouTweetPage = _homeScreenState.value.forYouTweetPage + 1,
            )
            updateForYouAllTweets(
                page = _homeScreenState.value.forYouTweetPage,
                limit = 10,
            )
        }
    }

    private suspend fun updateForYouAllTweets(
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

    private suspend fun getAllTweets() {
        tweetRepository.allTweetsChanges().collect {
            _homeScreenState.value = _homeScreenState.value.copy(
                forYouTweets = it
            )
        }
    }

    fun updatePollOption(tweetId: String, optionId: String) {
        viewModelScope.launch {
            tweetRepository.updateTweetPoll(tweetId, optionId).collect {
                when (it) {
                    is ClientResponse.Error -> showMessage(it.message)
                    ClientResponse.Idle -> updateLoaderState(false)
                    ClientResponse.Loading -> updateLoaderState(true)
                    is ClientResponse.Success -> {}
                }
            }
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
            tweetRepository.saveTweetRequests(listOf(tweetRequest))
        }
    }
}