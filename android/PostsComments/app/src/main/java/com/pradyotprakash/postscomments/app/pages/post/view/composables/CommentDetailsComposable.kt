package com.pradyotprakash.postscomments.app.pages.post.view.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pradyotprakash.postscomments.app.composables.DeleteEditComposable

@Composable
fun CommentDetailsComposable(
    comment: String,
    userName: String?,
    isByCurrentUser: Boolean,
    deleteComment: () -> Unit,
    editComment: () -> Unit,
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(5)
            )
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp,
                    bottom = 10.dp,
                    start = 15.dp,
                    end = 15.dp
                )
        ) {
            userName?.let {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.titleMedium
                )
                Box(modifier = Modifier.height(5.dp))
            }
            Text(text = comment)
            if (isByCurrentUser) {
                DeleteEditComposable(
                    modifier = Modifier.align(Alignment.End),
                    delete = deleteComment,
                    edit = editComment,
                )
            }
        }
    }
}