package app.pages.home.home.screen.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import app.composables.BoxedCircularProgressBarIndicator
import app.composables.tweet.TweetComposable
import app.pages.home.home.state.TweetActions
import core.models.response.TweetResponse

@Composable
fun ForYouFollowingTweetsComposable(
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

                Column(
                    modifier = Modifier.fillMaxWidth().onGloballyPositioned { layoutCoordinates ->
                        val itemBounds = layoutCoordinates.boundsInParent()
                        val parentBounds =
                            layoutCoordinates.parentCoordinates?.boundsInParent() ?: Rect.Zero

                        if (parentBounds.left <= itemBounds.left && parentBounds.top <= itemBounds.top && parentBounds.right >= itemBounds.right && parentBounds.bottom >= itemBounds.bottom) {
                            tweetVisibility(tweet.id)
                        }
                    },
                ) {
                    if (index != 0) {
                        HorizontalDivider()
                    }
                    TweetComposable(
                        modifier = Modifier.padding(
                            horizontal = 15.dp, vertical = 8.dp
                        ),
                        tweet = tweet,
                        showTweetActions = true,
                        tweetActions = tweetActions,
                    )
                    HorizontalDivider()
                }
            }
        }

        BoxedCircularProgressBarIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            visible = showLoading,
        )
    }
}