package app.composables.tweet

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.composables.CircularDotComposable
import app.composables.ProfileImageComposable
import app.pages.home.home.state.TweetActions
import core.models.realm.PollChoicesDB
import utils.Constants.ConstValues.USERNAME_PREFIX

@Composable
fun TweetComposable(
    modifier: Modifier = Modifier,
    createdByProfilePicture: String?,
    createdByName: String,
    createdByUsername: String,
    tweetedOn: String,
    tweet: String,
    commentCount: String,
    retweetCount: String,
    likeCount: String,
    views: String,
    isAPoll: Boolean,
    pollChoices: List<PollChoicesDB>,
    isPollingAllowed: Boolean,
    pollingEndTime: String,
    onPollSelection: (String) -> Unit,
    tweetActions: TweetActions,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        ProfileImageComposable(
            profileImage = createdByProfilePicture,
            modifier = Modifier.size(40.dp).clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = tweetActions.profileImageClick,
            )
        )

        Column(
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
            ).weight(1f).clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = tweetActions.onTweetClick,
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    createdByName,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    "${USERNAME_PREFIX}${createdByUsername}",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(4.dp))
                CircularDotComposable(
                    modifier = Modifier.size(3.dp),
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    tweetedOn,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Text(
                tweet,
            )
            Spacer(modifier = Modifier.height(10.dp))
            if (isAPoll) {
                PollChoicesComposable(
                    modifier = Modifier.fillMaxWidth(),
                    pollChoices = pollChoices,
                    isPollingAllowed = isPollingAllowed,
                    pollingEndTime = pollingEndTime,
                    totalVotesOnPoll = pollChoices.sumOf { it.voteCount },
                    onPollSelection = onPollSelection,
                )
            }
            TweetActionsComposable(
                commentCount = commentCount,
                retweetCount = retweetCount,
                likeCount = likeCount,
                views = views,
                onBookmark = tweetActions.onBookmark,
                onShare = tweetActions.onShare,
            )
        }
    }
}