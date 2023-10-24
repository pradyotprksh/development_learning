package com.pradyotprakash.libraryowner.app.pages.splash.view.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun BackgroundImages(
    backgroundImage: List<String>,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        CoilImage(
            imageModel = { backgroundImage[0] },
            imageOptions = ImageOptions(
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.Center
            ),
            modifier = Modifier
                .weight(1 / 3f)
                .fillMaxSize()
        )
        CoilImage(
            imageModel = { backgroundImage[1] },
            imageOptions = ImageOptions(
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.Center
            ),
            modifier = Modifier
                .weight(1 / 3f)
                .fillMaxSize()
        )
        CoilImage(
            imageModel = { backgroundImage[2] },
            imageOptions = ImageOptions(
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.Center
            ),
            modifier = Modifier
                .weight(1 / 3f)
                .fillMaxSize()
        )
    }
}