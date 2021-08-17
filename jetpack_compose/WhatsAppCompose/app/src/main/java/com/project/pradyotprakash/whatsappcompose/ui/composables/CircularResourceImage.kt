package com.project.pradyotprakash.whatsappcompose.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.whatsappcompose.R

/**
 * A circular image which takes [resourceId] which will be the resource id of the image.
 * This will be used for showing images which are stored in the code.
 *
 * And the size of the image will be [size] in dp.
 */

@Composable
fun CircularResourceImage(resourceId: Int, size: Int) {
    Image(
        painter = painterResource(id = resourceId),
        contentDescription = stringResource(id = R.string.image_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(size.dp).clip(CircleShape)
    )
}