package com.pradyotprakash.findingfalcone.app.pages.splash.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.findingfalcone.app.localization.TR
import com.pradyotprakash.findingfalcone.app.localization.Translation
import com.pradyotprakash.findingfalcone.app.pages.splash.viewmodel.SplashViewModel
import com.pradyotprakash.findingfalcone.app.utils.Assets

@Composable
fun SplashView(
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        splashViewModel.startJourney()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        AnimatedVisibility(visible = true) {
            Image(
                painter = painterResource(id = Assets.AppIcon.resourceId),
                contentDescription = Assets.AppIcon.imageDescription
            )
        }
        Box(modifier = Modifier.height(10.dp))
        Text(
            text = Translation.getString(TR.appName),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.ExtraBold
        )
    }
}