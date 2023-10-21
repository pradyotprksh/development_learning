package com.pradyotprakash.libraryowner.app.pages.splash.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.libraryowner.app.pages.splash.viewmodel.SplashViewModel
import com.pradyotprakash.libraryowner.app.utils.Assets
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun SplashView(
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
    val backgroundImageUrl = splashViewModel.backgroundImageUrl.observeAsState("").value

    Box(modifier = Modifier.fillMaxSize()) {
        if (backgroundImageUrl.isBlank()) {
            Image(
                painter = painterResource(id = Assets.AppIcon.resourceId),
                contentDescription = Assets.AppIcon.imageDescription,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
                    .padding(100.dp)
            )
        } else {
            CoilImage(
                imageModel = { backgroundImageUrl },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillBounds,
                    alignment = Alignment.Center
                ),
                loading = {
                    Image(
                        painter = painterResource(id = Assets.AppIcon.resourceId),
                        contentDescription = Assets.AppIcon.imageDescription,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize()
                            .padding(100.dp)
                    )
                },
                failure = {
                    Image(
                        painter = painterResource(id = Assets.AppIcon.resourceId),
                        contentDescription = Assets.AppIcon.imageDescription,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize()
                            .padding(100.dp)
                    )
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}