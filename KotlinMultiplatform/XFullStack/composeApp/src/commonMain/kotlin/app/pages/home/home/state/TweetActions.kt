package app.pages.home.home.state

data class TweetActions(
    val profileImageClick: () -> Unit,
    val onTweetClick: (String) -> Unit,
    val onBookmark: (String) -> Unit,
    val onShare: (String) -> Unit,
    val onComment: (String) -> Unit,
    val onRepost: (String) -> Unit,
    val onLike: (String) -> Unit,
    val onViews: (String) -> Unit,
    val onPollSelection: (String, String) -> Unit,
) {
    constructor() : this(
        profileImageClick = {},
        onTweetClick = {},
        onComment = {},
        onRepost = {},
        onLike = {},
        onViews = {},
        onBookmark = {},
        onShare = {},
        onPollSelection = { _, _ -> },
    )
}
