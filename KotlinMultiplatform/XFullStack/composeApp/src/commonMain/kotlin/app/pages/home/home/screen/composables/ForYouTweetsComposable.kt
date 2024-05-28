package app.pages.home.home.screen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import app.composables.ProfileImageComposable
import core.models.realm.TweetDB
import utils.Constants.ConstValues.USERNAME_PREFIX

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
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(
                            horizontal = 15.dp, vertical = 8.dp
                        )
                    ) {
                        ProfileImageComposable(
                            profileImage = tweet.createdBy?.profilePicture,
                            modifier = Modifier.size(40.dp)
                        )

                        Column(
                            modifier = Modifier.padding(
                                start = 10.dp,
                                end = 10.dp,
                            ).weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    tweet.createdBy?.name ?: "",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    "${USERNAME_PREFIX}${tweet.createdBy?.username ?: ""}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Box(
                                    modifier = Modifier.size(3.dp).clip(CircleShape).background(
                                        color = MaterialTheme.colorScheme.onBackground
                                    )
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    "${tweet.tweetedOn}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                            Text(
                                tweet.tweet,
                            )
                            TweetActionsComposable(
                                commentCount = "${tweet.commentCount}",
                                retweetCount = "${tweet.retweetCount}",
                                likeCount = "${tweet.likeCount}",
                                views = "${tweet.views}",
                                onBookmark = {},
                                onShare = {},
                            )
                        }
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