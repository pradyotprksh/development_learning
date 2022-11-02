package com.project.pradyotprakash.rental.app.pages.welcome.view

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.project.pradyotprakash.rental.R
import com.project.pradyotprakash.rental.app.composables.PageStateComposable
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.pages.welcome.viewmodel.WelcomeViewModel

/**
 * A welcome screen for the user, this will help the user to
 * select the option to authenticate themselves.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
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
        Column {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = welcomeViewModel::navigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = Icons.Default.Phone.name,
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors()
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(top = 10.dp, bottom = 20.dp),
            ) {
                Text(
                    text = String.format(TR.welcomeMessage, welcomeViewModel.userType.name),
                    style = MaterialTheme.typography.bodyLarge,
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
                    Row {
                        Text(TR.authenticateYourself)
                    }
                }
            }
        }
    }
}