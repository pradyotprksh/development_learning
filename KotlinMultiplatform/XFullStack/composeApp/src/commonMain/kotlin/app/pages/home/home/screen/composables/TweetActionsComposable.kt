package app.pages.home.home.screen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TweetActionsComposable(
    modifier: Modifier = Modifier,
    commentCount: String,
    retweetCount: String,
    likeCount: String,
    views: String,
    onBookmark: () -> Unit,
    onShare: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        IconTextButtonComposable(
            text = commentCount,
            icon = Icons.Default.AddComment,
            onClick = {},
        )
        Spacer(modifier = Modifier.weight(1f))
        IconTextButtonComposable(
            text = retweetCount,
            icon = Icons.Default.Loop,
            onClick = {},
        )
        Spacer(modifier = Modifier.weight(1f))
        IconTextButtonComposable(
            text = likeCount,
            icon = Icons.Default.HeartBroken,
            onClick = {},
        )
        Spacer(modifier = Modifier.weight(1f))
        IconTextButtonComposable(
            text = views,
            icon = Icons.Default.AutoGraph,
            onClick = {},
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onBookmark,
        ) {
            Icon(
                imageVector = Icons.Default.BookmarkBorder,
                contentDescription = Icons.Default.BookmarkBorder.name,
                modifier = Modifier.size(15.dp),
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onShare,
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = Icons.Default.Share.name,
                modifier = Modifier.size(15.dp),
            )
        }
    }
}