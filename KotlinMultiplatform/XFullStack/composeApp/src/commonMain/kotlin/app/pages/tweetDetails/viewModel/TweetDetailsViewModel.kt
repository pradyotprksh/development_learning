package app.pages.tweetDetails.viewModel

import androidx.lifecycle.ViewModel
import app.pages.tweetDetails.state.TweetDetailsState
import di.SharedModulesDi
import domain.repositories.tweet.TweetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TweetDetailsViewModel(
    private val tweetRepository: TweetRepository = SharedModulesDi.Instance.tweetRepository,
) : ViewModel() {
    private val _tweetDetailsScreenState = MutableStateFlow(TweetDetailsState())
    val tweetDetailsScreen = _tweetDetailsScreenState.asStateFlow()

    suspend fun initialSetup(tweetId: String) {
        listenToTweetDetails(tweetId)
    }

    private suspend fun listenToTweetDetails(tweetId: String) {
        tweetRepository.getTweetChanges(tweetId).collect {
            _tweetDetailsScreenState.value = tweetDetailsScreen.value.copy(
                tweet = it,
            )
        }
    }

    private fun updateLoaderState(showLoader: Boolean) {
        _tweetDetailsScreenState.value = _tweetDetailsScreenState.value.copy(
            showLoading = showLoader
        )
    }

    fun removeSnackBarMessage() {
        _tweetDetailsScreenState.value = _tweetDetailsScreenState.value.copy(
            snackBarMessage = null
        )
    }
}