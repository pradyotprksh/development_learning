package app.pages.home.home.screen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import app.composables.ProfileImageComposable
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
    profileImageClick: () -> Unit,
    onTweetClick: () -> Unit,
    onBookmark: () -> Unit,
    onShare: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        ProfileImageComposable(
            profileImage = createdByProfilePicture,
            modifier = Modifier.size(40.dp).clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = profileImageClick,
            )
        )

        Column(
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
            ).weight(1f).clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onTweetClick,
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
                Box(
                    modifier = Modifier.size(3.dp).clip(CircleShape).background(
                        color = MaterialTheme.colorScheme.onBackground
                    )
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
            TweetActionsComposable(
                commentCount = commentCount,
                retweetCount = retweetCount,
                likeCount = likeCount,
                views = views,
                onBookmark = onBookmark,
                onShare = onShare,
            )
        }
    }
}