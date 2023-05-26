package com.pradyotprakash.postscomments.app.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PostDetailsComposable(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    isFromCurrentUser: Boolean = false,
    deletePost: () -> Unit = {},
    editPost: () -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .padding(
                top = 10.dp,
                bottom = 10.dp,
                start = 15.dp,
                end = 15.dp
            )
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick?.invoke()
                },
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
                DeleteEditComposable(
                    modifier = Modifier.align(Alignment.End),
                    delete = deletePost,
                    edit = editPost,
                )
            }
        }
    }
}