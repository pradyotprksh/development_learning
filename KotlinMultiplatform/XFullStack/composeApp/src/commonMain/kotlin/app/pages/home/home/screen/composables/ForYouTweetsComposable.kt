package app.pages.home.home.screen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import app.composables.tweet.ParentTweetReactionComposable
import app.composables.tweet.TweetComposable
import app.pages.home.home.state.TweetActions
import core.models.response.TweetResponse
import utils.Localization

@Composable
fun ForYouTweetsComposable(
    modifier: Modifier = Modifier,
    tweets: List<TweetResponse> = emptyList(),
    showLoading: Boolean = false,
    tweetsLazyColumnState: LazyListState,
    tweetActions: TweetActions,
    tweetVisibility: (String) -> Unit,
) {
    Box {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            state = tweetsLazyColumnState,
        ) {
            items(tweets.size) { index ->
                val tweet = tweets[index]
                val shownTweet =
                    if (tweet.aInnerTweet) if (tweet.parentTweetNotPresent) null else tweet.parentTweetDetails else tweet

                Column(
                    modifier = Modifier.fillMaxWidth()
                        .onGloballyPositioned { layoutCoordinates ->
                            val itemBounds = layoutCoordinates.boundsInParent()
                            val parentBounds =
                                layoutCoordinates.parentCoordinates?.boundsInParent()
                                    ?: Rect.Zero

                            if (parentBounds.left <= itemBounds.left && parentBounds.top <= itemBounds.top && parentBounds.right >= itemBounds.right && parentBounds.bottom >= itemBounds.bottom) {
                                tweetVisibility(tweet.id)
                            }
                        },
                ) {
                    if (index != 0) {
                        HorizontalDivider()
                    }
                    if (tweet.aInnerTweet) {
                        ParentTweetReactionComposable(
                            icon = if (tweet.isLikedTweet) Icons.Default.ThumbUp else Icons.Default.Loop,
                            text = Localization.format(
                                if (tweet.isLikedTweet) Localization.LIKED else Localization.REPOSTED,
                                tweet.createdBy?.name ?: "",
                            ),
                        )
                    }
                    if (shownTweet != null) {
                        TweetComposable(
                            modifier = Modifier.padding(
                                horizontal = 15.dp, vertical = 8.dp
                            ),
                            createdByProfilePicture = shownTweet.createdBy?.profilePicture,
                            createdByName = shownTweet.createdBy?.name ?: "",
                            createdByUsername = shownTweet.createdBy?.username ?: "",
                            tweetedOn = shownTweet.tweetedOn,
                            tweet = shownTweet.tweet,
                            tweetId = shownTweet.id,
                            commentCount = "${shownTweet.commentCount}",
                            retweetCount = "${shownTweet.retweetCount}",
                            likeCount = "${shownTweet.likesCount}",
                            views = "${shownTweet.views}",
                            isAPoll = shownTweet.isAPoll,
                            isQuoteTweet = shownTweet.isQuoteTweet,
                            pollChoices = shownTweet.pollChoices.toList(),
                            isPollingAllowed = shownTweet.isPollingAllowed,
                            pollingEndTime = shownTweet.pollingEndTime,
                            showTweetActions = true,
                            onPollSelection = { optionId ->
                                tweetActions.onPollSelection(
                                    shownTweet.id,
                                    optionId,
                                )
                            },
                            tweetActions = tweetActions,
                            isLikedByCurrentUser = shownTweet.isLikedByCurrentUser,
                            parentTweetDetails = tweet.parentTweetDetails,
                            isACommentTweet = tweet.isACommentTweet,
                        )
                    }
                    HorizontalDivider()
                }
            }
        }

        AnimatedVisibility(
            visible = showLoading, modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Box(
                modifier = Modifier.padding(top = 15.dp)
            ) {
                Box(
                    modifier = Modifier.size(40.dp).clip(CircleShape).background(
                        color = MaterialTheme.colorScheme.background
                    )
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center).size(25.dp),
                        strokeWidth = 2.dp,
                    )
                }
            }
        }
    }
}