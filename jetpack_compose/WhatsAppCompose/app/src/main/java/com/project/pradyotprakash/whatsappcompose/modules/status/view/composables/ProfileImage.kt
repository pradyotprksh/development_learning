package com.project.pradyotprakash.whatsappcompose.modules.status.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.project.pradyotprakash.whatsappcompose.R

/**
 * A composable which will be used to show the user profile image in a rectangle format.
 * With rounded boreder.
 */

@Composable
fun ProfileImage(url: String) {
    Image(
        painter = rememberImagePainter(
            data = url,
            builder = {
                crossfade(true)
                transformations(
                    RoundedCornersTransformation(
                        topLeft = 20f,
                        topRight = 20f,
                        bottomLeft = 20f,
                        bottomRight = 20f
                    )
                )
            }
        ),
        contentDescription = stringResource(id = R.string.image_description_network),
        modifier = Modifier
            .size(50.dp),
        contentScale = ContentScale.Crop
    )
}