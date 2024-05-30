package app.pages.home.home.state

data class TweetActions(
    val profileImageClick: () -> Unit,
    val onTweetClick: () -> Unit,
    val onBookmark: () -> Unit,
    val onShare: () -> Unit,
    val onPollSelection: (String) -> Unit,
)
