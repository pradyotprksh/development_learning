package com.pradyotprakash.postscomments.app.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DeleteEditComposable(
    modifier: Modifier = Modifier,
    delete: () -> Unit,
    edit: () -> Unit,
) {
    Box(
        modifier = modifier
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
                onClick = delete
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = Icons.Default.Delete.name,
                    tint = MaterialTheme.colorScheme.error
                )
            }
            IconButton(
                onClick = edit
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = Icons.Default.Edit.name,
                )
            }
        }
    }
}