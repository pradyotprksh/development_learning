package app.pages.bookmarks.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pages.bookmarks.state.BookmarksState
import core.models.request.TweetRequest
import di.SharedModulesDi
import domain.repositories.request.RequestRepository
import domain.repositories.tweet.TweetRepository
import domain.repositories.user.current.CurrentUserRepository
import domain.repositories.view.ViewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookmarksViewModel(
    private val tweetRepository: TweetRepository = SharedModulesDi.Instance.tweetRepository,
    private val requestRepository: RequestRepository = SharedModulesDi.Instance.requestRepository,
    private val currentUserRepository: CurrentUserRepository = SharedModulesDi.Instance.currentUserRepository,
    private val viewRepository: ViewRepository = SharedModulesDi.Instance.viewRepository,
) : ViewModel() {
    private val _bookmarksState = MutableStateFlow(BookmarksState())
    val bookmarksState = _bookmarksState.asStateFlow()

    init {
        listenToBookmarksTweetChanges()
    }

    private fun listenToBookmarksTweetChanges() {
        viewModelScope.launch {
            currentUserRepository.getUserId()?.let { userId ->
                tweetRepository.allBookmarksTweetsChanges(userId).collect {
                    _bookmarksState.value = _bookmarksState.value.copy(
                        bookmarks = it,
                    )
                }
            }
        }
    }

    fun bookmarkUpdate(tweetId: String) {
        viewModelScope.launch {
            requestRepository.saveBookmarkRequest(tweetId)
        }
    }

    fun updateViewForTweet(tweetId: String) {
        viewModelScope.launch {
            viewRepository.saveView(tweetId)
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

    fun updateDropDown() {
        _bookmarksState.value = _bookmarksState.value.copy(
            openDropDown = !_bookmarksState.value.openDropDown,
        )
    }

    fun clearAllBookmarks() {
        updateDropDown()
        _bookmarksState.value.bookmarks.map { it.id }.forEach { id ->
            bookmarkUpdate(id)
        }
    }
}