package com.project.pradyotprakash.twitter.splash.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.twitter.twiteme.theme.DodgeBlue

@Composable
fun SplashView() {
    /*Box(
        modifier = Modifier
            .background(DodgeBlue)
            .fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher_round),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size(120.dp)
        )
    }*/
    Column {
        CircularProgressIndicator(
            color = DodgeBlue,
            strokeWidth = 2.dp
        )
        Text(
            "Loading..."
        )
    }
}