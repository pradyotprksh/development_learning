package app.pages.profileDetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.profileDetails.state.ProfileDetailsState
import di.SharedModulesDi
import domain.repositories.tweet.TweetRepository
import domain.repositories.user.user.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import utils.Localization

class ProfileDetailsViewModel(
    private val userRepository: UserRepository = SharedModulesDi.Instance.userRepository,
    private val tweetRepository: TweetRepository = SharedModulesDi.Instance.tweetRepository,
) : ViewModel() {
    private val _profileDetailsState = MutableStateFlow(ProfileDetailsState())
    val profileDetailsState = _profileDetailsState.asStateFlow()

    fun initialSetup(userId: String?) {
        userId?.let {
            listenToProfileDetails(it)
            getTweets(it)
        }
    }

    private fun getTweets(userId: String) {
        listenToPosts(userId)
        listenToReplies(userId)
        listenToMedia(userId)
    }

    fun listenToLikes(userId: String) {
        viewModelScope.launch {
            tweetRepository.getAllUserLikes(userId).collect {
                _profileDetailsState.value = _profileDetailsState.value.copy(
                    likes = it,
                )
            }
        }
    }

    private fun listenToMedia(userId: String) {
        viewModelScope.launch {
            tweetRepository.getAllUserMedia(userId).collect {
                _profileDetailsState.value = _profileDetailsState.value.copy(
                    media = it,
                )
            }
        }
    }

    private fun listenToReplies(userId: String) {
        viewModelScope.launch {
            tweetRepository.getAllUserReplies(userId).collect {
                _profileDetailsState.value = _profileDetailsState.value.copy(
                    replies = it,
                )
            }
        }
    }

    private fun listenToPosts(userId: String) {
        viewModelScope.launch {
            tweetRepository.getAllUserPosts(userId).collect {
                _profileDetailsState.value = _profileDetailsState.value.copy(
                    posts = it,
                )
            }
        }
    }

    private fun listenToProfileDetails(userId: String) {
        viewModelScope.launch {
            userRepository.getUserInfoChanges(userId).collect {
                updateTabs(it?.isSameUser ?: false)
                _profileDetailsState.value = _profileDetailsState.value.copy(
                    userInfoResponse = it,
                )
            }
        }
    }

    private fun updateTabs(isSameUser: Boolean) {
        val tabs = mutableListOf<String>()
        tabs.add(Localization.POSTS)
        tabs.add(Localization.REPLIES)
        tabs.add(Localization.HIGHLIGHTS)
        if (isSameUser) {
            tabs.add(Localization.ARTICLES)
        }
        tabs.add(Localization.MEDIA)
        if (isSameUser) {
            tabs.add(Localization.LIKES)
        }

        _profileDetailsState.value = _profileDetailsState.value.copy(
            tabsDetails = tabs,
        )
    }

    private fun updateLoaderState(showLoader: Boolean) {
        _profileDetailsState.value = _profileDetailsState.value.copy(
            showLoading = showLoader
        )
    }

    private fun showMessage(message: String) {
        _profileDetailsState.value = _profileDetailsState.value.copy(
            snackBarMessage = message,
        )
    }

    fun removeSnackBarMessage() {
        _profileDetailsState.value = _profileDetailsState.value.copy(
            snackBarMessage = null
        )
    }

    fun tweetVisibility(tweetId: String) {}
}