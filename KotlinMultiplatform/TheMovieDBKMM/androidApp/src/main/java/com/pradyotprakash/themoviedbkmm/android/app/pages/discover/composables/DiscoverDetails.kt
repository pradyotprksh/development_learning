package com.pradyotprakash.themoviedbkmm.android.app.pages.discover.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun DiscoverDetails(
    title: String,
    image: String,
) {
    Column(
        modifier = Modifier.padding(
            all = 10.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = image,
                contentDescription = title,
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(10),
                    )
                    .border(
                        color = MaterialTheme.colors.primary,
                        width = 1.dp,
                        shape = RoundedCornerShape(10),
                    )
            )
            Box(modifier = Modifier.width(10.dp))
            Text(text = title)
        }
    }
}