package app.pages.createTweet.screen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Gif
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Poll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateTweetOptionsComposable(
    progress: Float,
    enableAddNewTweetButton: Boolean,
    tweetAction: (@Composable () -> Unit)? = null,
    onImageClick: () -> Unit,
    onGifClick: () -> Unit,
    onPollClick: () -> Unit,
    onLocationClick: () -> Unit,
    onAddTweetClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(
            vertical = 10.dp,
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        IconButton(
            onClick = onImageClick,
        ) {
            Icon(
                imageVector = Icons.Default.Image,
                contentDescription = Icons.Default.Image.name,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
        IconButton(
            onClick = onGifClick,
        ) {
            Icon(
                imageVector = Icons.Default.Gif,
                contentDescription = Icons.Default.Gif.name,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
        IconButton(
            onClick = onPollClick,
        ) {
            Icon(
                imageVector = Icons.Default.Poll,
                contentDescription = Icons.Default.Poll.name,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
        IconButton(
            onClick = onLocationClick,
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = Icons.Default.LocationOn.name,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            progress = { progress },
            trackColor = MaterialTheme.colorScheme.outlineVariant,
            strokeWidth = 2.dp,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier.height(24.dp).width(DividerDefaults.Thickness).background(
                color = DividerDefaults.color,
            ),
        )
        if (tweetAction != null) {
            tweetAction()
        } else {
            IconButton(
                onClick = onAddTweetClick,
                enabled = enableAddNewTweetButton,
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = Icons.Default.AddCircle.name,
                    tint = if (enableAddNewTweetButton) MaterialTheme.colorScheme.primary else LocalContentColor.current,
                )
            }
        }
    }
}