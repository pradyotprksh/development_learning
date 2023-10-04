package com.pradyotprakash.libraryowner.app.pages.splash.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.libraryowner.app.pages.splash.viewmodel.SplashViewModel
import com.pradyotprakash.libraryowner.app.utils.Assets

@Composable
fun SplashView(
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
    Box(modifier = Modifier.fillMaxSize()) {
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