package com.pradyotprakash.notes.app.pages.splash.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.notes.app.composables.PageStateComposable
import com.pradyotprakash.notes.app.localization.TR
import com.pradyotprakash.notes.app.localization.Translation
import com.pradyotprakash.notes.app.pages.splash.viewmodel.SplashViewModel
import com.pradyotprakash.notes.app.utils.Assets

@Composable
fun SplashView(
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val error = splashViewModel.error.observeAsState("")

    PageStateComposable(
        errorMessage = error.value,
        dismissErrorAlert = splashViewModel::updateErrorState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                painter = painterResource(id = Assets.AppIcon.resourceId),
                contentDescription = Assets.AppIcon.imageDescription
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = Translation.getString(TR.appName),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}