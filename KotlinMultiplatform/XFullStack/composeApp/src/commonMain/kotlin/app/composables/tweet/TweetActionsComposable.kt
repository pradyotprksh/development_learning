package app.composables.tweet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.ThumbsUpDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.composables.IconTextButtonComposable

@Composable
fun TweetActionsComposable(
    modifier: Modifier = Modifier,
    commentCount: String,
    retweetCount: String,
    likeCount: String,
    views: String,
    isLikedByCurrentUser: Boolean,
    isBookmarkedByCurrentUser: Boolean,
    showViews: Boolean = true,
    bookmarkModifier: Modifier = Modifier,
    shareModifier: Modifier = Modifier,
    onAddComment: () -> Unit,
    onRepost: () -> Unit,
    onLike: () -> Unit,
    onViews: () -> Unit,
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
            onClick = onAddComment,
        )
        Spacer(modifier = Modifier.weight(1f))
        IconTextButtonComposable(
            text = retweetCount,
            icon = Icons.Default.Loop,
            onClick = onRepost,
        )
        Spacer(modifier = Modifier.weight(1f))
        IconTextButtonComposable(
            text = likeCount,
            icon = if (isLikedByCurrentUser) Icons.Default.ThumbUp else Icons.Default.ThumbsUpDown,
            onClick = onLike,
            tint = if (isLikedByCurrentUser) MaterialTheme.colorScheme.primary else null
        )
        if (showViews) {
            Spacer(modifier = Modifier.weight(1f))
            IconTextButtonComposable(
                text = views,
                icon = Icons.Default.AutoGraph,
                onClick = onViews,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onBookmark,
        ) {
            val icon =
                if (isBookmarkedByCurrentUser) Icons.Default.BookmarkAdded else Icons.Default.BookmarkAdd
            Icon(
                imageVector = icon,
                contentDescription = icon.name,
                modifier = bookmarkModifier,
                tint = if (isBookmarkedByCurrentUser) MaterialTheme.colorScheme.primary else LocalContentColor.current
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onShare,
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = Icons.Default.Share.name,
                modifier = shareModifier,
            )
        }
    }
}