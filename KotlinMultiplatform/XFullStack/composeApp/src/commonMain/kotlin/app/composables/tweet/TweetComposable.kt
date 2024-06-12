package app.composables.tweet

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.composables.CircularDotComposable
import app.composables.ProfileImageComposable
import app.composables.richText.RichTextComposable
import app.composables.richText.RichTextDetails
import app.composables.richText.TextDetails
import app.pages.home.home.state.TweetActions
import core.models.response.PollChoicesResponse
import core.models.response.TweetResponse
import utils.Constants.ConstValues.USERNAME_PREFIX
import utils.Localization
import utils.Tags

@Composable
fun TweetComposable(
    modifier: Modifier = Modifier,
    createdByProfilePicture: String?,
    createdByName: String,
    createdByUsername: String,
    tweetedOn: String,
    tweet: String,
    tweetId: String,
    commentCount: String,
    retweetCount: String,
    likeCount: String,
    views: String,
    isAPoll: Boolean,
    isQuoteTweet: Boolean,
    isLikedByCurrentUser: Boolean,
    isACommentTweet: Boolean,
    pollChoices: List<PollChoicesResponse>,
    isPollingAllowed: Boolean,
    pollingEndTime: String,
    showTweetActions: Boolean,
    parentTweetDetails: TweetResponse?,
    onPollSelection: (String) -> Unit,
    tweetActions: TweetActions,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        ProfileImageComposable(
            profileImage = createdByProfilePicture, modifier = Modifier.size(40.dp).clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    tweetActions.profileImageClick()
                },
            )
        )

        Column(
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
            ).weight(1f).clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    tweetActions.onTweetClick(tweetId)
                },
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    createdByName, style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    "${USERNAME_PREFIX}${createdByUsername}",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(4.dp))
                if (tweetedOn.isNotBlank()) {
                    CircularDotComposable(
                        modifier = Modifier.size(3.dp),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        tweetedOn, style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            if (isACommentTweet) {
                parentTweetDetails?.createdBy?.let { createdBy ->
                    Spacer(modifier = Modifier.height(5.dp))
                    RichTextComposable(
                        richTextDetails = RichTextDetails(
                            texts = listOf(
                                TextDetails(
                                    text = Localization.REPLYING_TO,
                                ),
                                TextDetails(
                                    text = Localization.WHITE_SPACE,
                                ),
                                TextDetails(text = "$USERNAME_PREFIX${createdBy.username}",
                                    isClickable = true,
                                    spanStyle = SpanStyle(
                                        color = MaterialTheme.colorScheme.primary,
                                    ),
                                    tag = Tags.CreatedBy,
                                    actions = {}),
                            ),
                            textStyle = MaterialTheme.typography.labelMedium.copy(
                                color = MaterialTheme.colorScheme.onBackground
                            ),
                        ),
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                tweet,
            )
            if (isAPoll) {
                Spacer(modifier = Modifier.height(10.dp))
                PollChoicesComposable(
                    modifier = Modifier.fillMaxWidth(),
                    pollChoices = pollChoices,
                    isPollingAllowed = isPollingAllowed,
                    pollingEndTime = pollingEndTime,
                    totalVotesOnPoll = pollChoices.sumOf { it.voteCount },
                    onPollSelection = onPollSelection,
                )
            }
            if (parentTweetDetails != null && isQuoteTweet) {
                Spacer(modifier = Modifier.height(10.dp))
                TweetComposable(
                    modifier = Modifier.border(
                        width = Dp.Hairline,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        shape = RoundedCornerShape(10.dp)
                    ).padding(
                        horizontal = 15.dp, vertical = 8.dp
                    ),
                    createdByProfilePicture = parentTweetDetails.createdBy?.profilePicture,
                    createdByName = parentTweetDetails.createdBy?.name ?: "",
                    createdByUsername = parentTweetDetails.createdBy?.username ?: "",
                    tweetedOn = "",
                    tweet = parentTweetDetails.tweet,
                    tweetId = parentTweetDetails.id,
                    commentCount = "${parentTweetDetails.commentCount}",
                    retweetCount = "${parentTweetDetails.retweetCount}",
                    likeCount = "${parentTweetDetails.likesCount}",
                    views = "${parentTweetDetails.views}",
                    isAPoll = parentTweetDetails.isAPoll,
                    isQuoteTweet = false,
                    isLikedByCurrentUser = parentTweetDetails.isLikedByCurrentUser,
                    pollChoices = parentTweetDetails.pollChoices.toList(),
                    isPollingAllowed = parentTweetDetails.isPollingAllowed,
                    pollingEndTime = parentTweetDetails.pollingEndTime,
                    showTweetActions = false,
                    parentTweetDetails = null,
                    onPollSelection = {},
                    tweetActions = TweetActions(),
                    isACommentTweet = parentTweetDetails.isACommentTweet,
                )
            }
            if (showTweetActions) {
                TweetActionsComposable(
                    commentCount = commentCount,
                    retweetCount = retweetCount,
                    likeCount = likeCount,
                    views = views,
                    isLikedByCurrentUser = isLikedByCurrentUser,
                    bookmarkModifier = Modifier.size(15.dp),
                    shareModifier = Modifier.size(15.dp),
                    onBookmark = { tweetActions.onBookmark(tweetId) },
                    onShare = { tweetActions.onShare(tweetId) },
                    onAddComment = { tweetActions.onComment(tweetId) },
                    onRepost = { tweetActions.onRepost(tweetId) },
                    onLike = { tweetActions.onLike(tweetId) },
                    onViews = { tweetActions.onViews(tweetId) },
                )
            }
        }
    }
}