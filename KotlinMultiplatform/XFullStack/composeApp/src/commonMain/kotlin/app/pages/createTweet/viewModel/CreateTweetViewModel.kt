package app.pages.createTweet.viewModel

import androidx.lifecycle.ViewModel
import app.pages.createTweet.state.CreateTweetState
import app.pages.createTweet.state.TweetDetails
import di.ModulesDi
import domain.repositories.user.current.CurrentUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.kodein.di.instance

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
        tweets.removeAt(index)
        tweets.add(index, TweetDetails(tweet = value, index = index, isVisible = true))

        _createTweetState.value = _createTweetState.value.copy(
            tweets = tweets,
        )
    }
}