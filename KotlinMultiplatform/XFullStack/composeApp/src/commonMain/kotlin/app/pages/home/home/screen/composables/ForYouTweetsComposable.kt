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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import core.models.realm.TweetDB

@Composable
fun ForYouTweetsComposable(
    modifier: Modifier = Modifier,
    tweets: List<TweetDB> = emptyList(),
    showLoading: Boolean = false,
) {
    Box {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(tweets.size) { index ->
                val tweet = tweets[index]

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (index != 0) {
                        HorizontalDivider()
                    }
                    TweetComposable(
                        modifier = Modifier.padding(
                            horizontal = 15.dp, vertical = 8.dp
                        ),
                        createdByProfilePicture = tweet.createdBy?.profilePicture,
                        createdByName = tweet.createdBy?.name ?: "",
                        createdByUsername = tweet.createdBy?.username ?: "",
                        tweetedOn = tweet.tweetedOn,
                        tweet = tweet.tweet,
                        commentCount = "${tweet.commentCount}",
                        retweetCount = "${tweet.retweetCount}",
                        likeCount = "${tweet.likeCount}",
                        views = "${tweet.views}",
                        profileImageClick = {},
                        onTweetClick = {},
                        onBookmark = {},
                        onShare = {},
                    )
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