package app.pages.bookmarks.state

import core.models.response.TweetResponse

data class BookmarksState(
    val bookmarks: List<TweetResponse> = emptyList(),
    val openDropDown: Boolean = false,
)