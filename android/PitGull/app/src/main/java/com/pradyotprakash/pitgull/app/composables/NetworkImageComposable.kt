package com.pradyotprakash.pitgull.app.composables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.imageLoader
import com.pradyotprakash.pitgull.R
import com.pradyotprakash.pitgull.app.localizations.TR

@Composable
fun NetworkImageComposable(
    modifier: Modifier = Modifier,
    imageUrl: String,
    size: Dp,
    cornerSize: Dp,
    contentScale: ContentScale = ContentScale.Crop,
    error: Int = R.drawable.error,
) {
    val context = LocalContext.current

    AsyncImage(
        model = imageUrl,
        contentDescription = TR.onlineImageDescription,
        imageLoader = context.imageLoader,
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(cornerSize)),
        contentScale = contentScale,
        error = painterResource(id = error)
    )
}