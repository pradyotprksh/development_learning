package app.pages.home.message.screen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.composables.CircularDotComposable
import app.composables.ProfileImageComposable
import utils.Constants.ConstValues.USERNAME_PREFIX

@Composable
fun ChatDetailsComposable(
    modifier: Modifier = Modifier,
    openDirectMessage: () -> Unit,
    usersProfilePicture: List<String>,
    userNames: String,
    username: String?,
    lastMessage: String?,
    messageOn: String?,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().clickable {
            openDirectMessage()
        },
    ) {
        if (usersProfilePicture.size < 2) {
            ProfileImageComposable(
                profileImage = usersProfilePicture.firstOrNull(),
                modifier = Modifier.size(60.dp),
            )
        } else {
            GroupProfileImagesComposable(
                usersProfilePicture = usersProfilePicture,
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    userNames, style = MaterialTheme.typography.titleMedium
                )
                username?.let {
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        "$USERNAME_PREFIX${username}", style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                CircularDotComposable(
                    modifier = Modifier.size(3.dp),
                )
                messageOn?.let {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        messageOn, style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            lastMessage?.let {
                Text(
                    lastMessage, style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}