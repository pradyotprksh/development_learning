package com.pradyotprakash.libraryowner.app.pages.welcome.view

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.pradyotprakash.libraryowner.R
import com.pradyotprakash.libraryowner.app.composables.PageStateComposable
import com.pradyotprakash.libraryowner.app.localization.TR
import com.pradyotprakash.libraryowner.app.pages.welcome.viewmodel.WelcomeViewModel

@Composable
fun WelcomeView(welcomeViewModel: WelcomeViewModel = hiltViewModel()) {
    val signInLauncher = rememberLauncherForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        welcomeViewModel.firebaseAuthResponse(res)
    }

    val loading = welcomeViewModel.loading.observeAsState(false)
    val error = welcomeViewModel.error.observeAsState("")

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = welcomeViewModel::updateErrorState
    ) {
        Scaffold { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(
                        top = paddingValues.calculateTopPadding() + 15.dp,
                        bottom = paddingValues.calculateBottomPadding() + 15.dp,
                        start = 15.dp,
                        end = 15.dp
                    ),
            ) {
                Text(
                    text = TR.welcomeMessage,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        val providers = arrayListOf(
                            AuthUI.IdpConfig.EmailBuilder().build(),
                            AuthUI.IdpConfig.PhoneBuilder().build(),
                            AuthUI.IdpConfig.GoogleBuilder().build()
                        )
                        val signInIntent = AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setLogo(R.drawable.app_icon)
                            .build()
                        signInLauncher.launch(signInIntent)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(TR.joinUs)
                }
            }
        }
    }
}