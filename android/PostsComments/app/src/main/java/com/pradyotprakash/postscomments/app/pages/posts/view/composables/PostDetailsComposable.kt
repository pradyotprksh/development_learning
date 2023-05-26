package com.pradyotprakash.postscomments.app.pages.posts.view.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PostDetailsComposable(
    title: String,
    text: String,
    isFromCurrentUser: Boolean = false,
    borderWidth: Int = 0,
    deletePost: () -> Unit = {},
    editPost: () -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .border(
                width = borderWidth.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(5)
            )
            .padding(
                top = 10.dp,
                bottom = 10.dp,
                start = 15.dp,
                end = 15.dp
            )
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().clickable {
                onClick?.invoke()
            }
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Box(modifier = Modifier.height(10.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium
            )
            Box(modifier = Modifier.height(10.dp))
            if (isFromCurrentUser) {
                Box(
                    modifier = Modifier
                        .align(Alignment.End)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(100),
                        )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                    ) {
                        IconButton(
                            onClick = deletePost
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = Icons.Default.Delete.name,
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                        IconButton(
                            onClick = editPost
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = Icons.Default.Edit.name,
                            )
                        }
                    }
                }
            }
        }
    }
}