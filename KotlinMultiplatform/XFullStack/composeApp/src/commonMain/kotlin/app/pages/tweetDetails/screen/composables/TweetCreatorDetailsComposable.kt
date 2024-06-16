package app.pages.tweetDetails.screen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.composables.FollowButtonComposable
import app.composables.ProfileImageComposable
import core.models.response.UserInfoResponse
import utils.Constants

@Composable
fun TweetCreatorDetailsComposable(
    modifier: Modifier = Modifier,
    createdBy: UserInfoResponse,
    isSameUser: Boolean,
    isFollowedByCurrentUser: Boolean,
    isFollowingCurrentUser: Boolean,
    followUpdate: () -> Unit,
    openProfileDetails: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProfileImageComposable(
            profileImage = createdBy.profilePicture, modifier = Modifier.size(40.dp).clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    openProfileDetails()
                },
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
        Column {
            Text(
                createdBy.name, style = MaterialTheme.typography.titleMedium
            )
            Text(
                "${Constants.ConstValues.USERNAME_PREFIX}${createdBy.username}",
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        FollowButtonComposable(
            isFollowingCurrentUser = isFollowingCurrentUser,
            isFollowedByCurrentUser = isFollowedByCurrentUser,
            isCurrentUser = isSameUser,
            onClick = followUpdate,
        )
        Spacer(modifier = Modifier.width(2.dp))
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = Icons.Default.Menu.name,
            )
        }
    }
}