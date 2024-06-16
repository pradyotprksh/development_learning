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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.composables.CircularDotComposable
import app.composables.ProfileImageComposable
import app.composables.UsernameClickableComposable
import app.pages.home.home.state.TweetActions
import core.models.response.TweetResponse
import utils.Constants.ConstValues.USERNAME_PREFIX
import utils.Localization

@Composable
fun TweetComposable(
    modifier: Modifier = Modifier,
    tweet: TweetResponse,
    showTweetActions: Boolean,
    tweetActions: TweetActions = TweetActions(),
) {
    val parentTweetDetails = tweet.parentTweetDetails
    val shownTweet = if (tweet.aInnerTweet) parentTweetDetails else tweet
    val shownTweetCreatedBy = shownTweet?.createdBy

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        if (tweet.aInnerTweet) {
            tweet.createdBy?.name?.let { name ->
                ParentTweetReactionComposable(
                    icon = if (tweet.isLikedTweet) Icons.Default.ThumbUp else Icons.Default.Loop,
                    text = Localization.format(
                        if (tweet.isLikedTweet) Localization.LIKED else Localization.REPOSTED,
                        name,
                    ),
                )
            }
        }
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            shownTweetCreatedBy?.let {
                ProfileImageComposable(
                    profileImage = shownTweetCreatedBy.profilePicture,
                    modifier = Modifier.size(40.dp).clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            tweetActions.profileImageClick(shownTweetCreatedBy.id)
                        },
                    )
                )
            }

            Column(
                modifier = Modifier.padding(
                    start = 10.dp,
                    end = 10.dp,
                ).weight(1f).clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        tweetActions.onTweetClick(shownTweet?.id ?: "")
                    },
                )
            ) {
                shownTweetCreatedBy?.let {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            shownTweetCreatedBy.name, style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            "${USERNAME_PREFIX}${shownTweetCreatedBy.username}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        CircularDotComposable(
                            modifier = Modifier.size(3.dp),
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            shownTweet.tweetedOn, style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                if (tweet.isACommentTweet) {
                    parentTweetDetails?.createdBy?.let { createdBy ->
                        Spacer(modifier = Modifier.height(5.dp))
                        UsernameClickableComposable(
                            text = Localization.REPLYING_TO,
                            username = createdBy.username,
                            onClick = {
                                tweetActions.profileImageClick(createdBy.id)
                            },
                        )
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                shownTweet?.let {
                    Text(
                        shownTweet.tweet,
                    )
                    if (shownTweet.isAPoll) {
                        Spacer(modifier = Modifier.height(10.dp))
                        PollChoicesComposable(
                            modifier = Modifier.fillMaxWidth(),
                            tweetId = shownTweet.id,
                            pollChoices = shownTweet.pollChoices,
                            isPollingAllowed = shownTweet.isPollingAllowed,
                            pollingEndTime = shownTweet.pollingEndTime,
                            totalVotesOnPoll = shownTweet.pollChoices.sumOf { it.voteCount },
                            onPollSelection = tweetActions.onPollSelection,
                        )
                    }

                    if (parentTweetDetails != null && tweet.isQuoteTweet) {
                        Spacer(modifier = Modifier.height(10.dp))
                        TweetComposable(
                            modifier = Modifier.border(
                                width = Dp.Hairline,
                                color = MaterialTheme.colorScheme.outlineVariant,
                                shape = RoundedCornerShape(10.dp)
                            ).padding(
                                horizontal = 15.dp, vertical = 8.dp
                            ),
                            tweet = parentTweetDetails,
                            showTweetActions = false,
                        )
                    }
                }
                if (showTweetActions) {
                    shownTweet?.let {
                        TweetActionsComposable(
                            commentCount = shownTweet.commentCount.toString(),
                            retweetCount = shownTweet.retweetCount.toString(),
                            likeCount = shownTweet.likesCount.toString(),
                            views = shownTweet.views.toString(),
                            isLikedByCurrentUser = shownTweet.isLikedByCurrentUser,
                            isBookmarkedByCurrentUser = shownTweet.isBookmarkedByCurrentUser,
                            bookmarkModifier = Modifier.size(15.dp),
                            shareModifier = Modifier.size(15.dp),
                            onBookmark = { tweetActions.onBookmark(shownTweet.id) },
                            onShare = { tweetActions.onShare(shownTweet.id) },
                            onAddComment = { tweetActions.onComment(shownTweet.id) },
                            onRepost = { tweetActions.onRepost(shownTweet.id) },
                            onLike = { tweetActions.onLike(shownTweet.id) },
                            onViews = { tweetActions.onViews(shownTweet.id) },
                        )
                    }
                }
            }
        }
    }
}