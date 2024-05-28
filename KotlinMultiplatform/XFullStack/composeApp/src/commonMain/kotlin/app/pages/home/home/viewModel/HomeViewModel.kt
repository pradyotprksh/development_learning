package app.pages.home.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.home.home.state.HomeState
import core.models.response.ClientResponse
import di.ModulesDi
import domain.repositories.tweet.TweetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.kodein.di.instance

class HomeViewModel : ViewModel() {
    private val tweetRepository: TweetRepository by ModulesDi.di.instance()

    init {
        viewModelScope.launch {
            getAllTweets()
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
        updateAllTweets(page = _homeScreenState.value.forYouTweetPage)
    }

    private suspend fun updateAllTweets(page: Int) {
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