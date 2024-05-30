package app.pages.home.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.home.home.state.HomeState
import core.models.response.ClientResponse
import di.ModulesDi
import domain.repositories.tweet.TweetRepository
import domain.repositories.websocket.WebsocketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.kodein.di.instance
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
                        _homeScreenState.value.forYouTweetPage
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
        updateForYouAllTweets(page = _homeScreenState.value.forYouTweetPage)
    }

    private suspend fun updateForYouAllTweets(page: Int) {
        tweetRepository.updateAllTweets(page).collect {
            when (it) {
                is ClientResponse.Error -> showMessage(it.message)
                ClientResponse.Idle -> updateLoaderState(false)
                ClientResponse.Loading -> updateLoaderState(true)
                is ClientResponse.Success -> {}
            }
        }
    }

    private suspend fun getAllTweets() {
        tweetRepository.allTweetsChanges().collect {
            _homeScreenState.value = _homeScreenState.value.copy(
                tweets = it
            )
        }
    }
}