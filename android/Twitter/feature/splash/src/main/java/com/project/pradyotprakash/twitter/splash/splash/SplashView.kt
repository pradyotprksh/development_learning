package com.project.pradyotprakash.twitter.splash.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.pradyotprakash.twitter.twiteme.composables.TwitterLoader

@Composable
fun SplashView(splashViewModel: SplashViewModel) {
    splashViewModel.navigateToAuthOptionScreen()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        TwitterLoader(footerMessage = "Loading...")
    }
}