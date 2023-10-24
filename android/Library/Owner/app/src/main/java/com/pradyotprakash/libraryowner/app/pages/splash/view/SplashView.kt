package com.pradyotprakash.libraryowner.app.pages.splash.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.libraryowner.app.pages.splash.view.composables.BackgroundImages
import com.pradyotprakash.libraryowner.app.pages.splash.viewmodel.SplashViewModel
import com.pradyotprakash.libraryowner.app.utils.Assets

@Composable
fun SplashView(
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = Unit) {
        splashViewModel.getBackgroundImage()
    }

    val backgroundImageUrls = splashViewModel.backgroundImageUrls.observeAsState(emptyList()).value

    Box(modifier = Modifier.fillMaxSize()) {
        if (backgroundImageUrls.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                BackgroundImages(
                    backgroundImage = backgroundImageUrls.subList(
                        fromIndex = 0,
                        toIndex = 3
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1 / 3f)
                )
                BackgroundImages(
                    backgroundImage = backgroundImageUrls.subList(
                        fromIndex = 3,
                        toIndex = 6
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1 / 3f)
                )
                BackgroundImages(
                    backgroundImage = backgroundImageUrls.subList(
                        fromIndex = 6,
                        toIndex = 9
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1 / 3f)
                )
            }
        }
        Image(
            painter = painterResource(id = Assets.AppIcon.resourceId),
            contentDescription = Assets.AppIcon.imageDescription,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
                .padding(100.dp)
        )
    }
}