package com.project.pradyotprakash.whatsappcompose.modules.edit_profile.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.pradyotprakash.whatsappcompose.R
import com.project.pradyotprakash.whatsappcompose.models.User
import com.project.pradyotprakash.whatsappcompose.modules.edit_profile.viewModel.EditProfileViewModel
import com.project.pradyotprakash.whatsappcompose.ui.composables.CircularIndicatorMessage
import com.project.pradyotprakash.whatsappcompose.ui.composables.Snackbar
import com.project.pradyotprakash.whatsappcompose.ui.theme.WhatsAppComposeTheme
import com.project.pradyotprakash.whatsappcompose.utils.Utility
import kotlinx.coroutines.launch

/**
 * An edit profile view which will help user to edit there details.
 *
 * Currently we allow to save few details like
 * Profile picture, name, username, status.
 *
 * This will help other users to verify the current user properly.
 */

@Composable
fun EditProfileView(
    back: () -> Unit,
    editProfileViewModel: EditProfileViewModel = viewModel()
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    editProfileViewModel.getUserDetails()

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = SnackbarHostState()

    val loading: Boolean by editProfileViewModel.loading.observeAsState(initial = false)
    val showMessage: Boolean by editProfileViewModel.showMessage.observeAsState(initial = false)
    val message: String by editProfileViewModel.message.observeAsState(initial = "")
    val userDetails: User by editProfileViewModel.userDetails.observeAsState(initial = User())

    /**
     * Show snackbar
     */
    val showSnackbar = {
        coroutineScope.launch {
            when (snackbarHostState.showSnackbar(
                message = message,
            )) {
                SnackbarResult.Dismissed -> {
                    editProfileViewModel.snackbarDismissed()
                }
                SnackbarResult.ActionPerformed -> {
                    editProfileViewModel.snackbarDismissed()
                }
            }
        }
    }

    if (showMessage) {
        Utility.showMessage(message = message)
        showSnackbar()
    }

    WhatsAppComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 15.dp, vertical = 50.dp)
                    ) {
                        IconButton(
                            onClick = { back() }
                        ) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.image_description),
                                modifier = Modifier.size(15.dp),
                                tint = Color.Black
                            )
                        }
                    }
                    if (loading) {
                        CircularIndicatorMessage(
                            message = stringResource(id = R.string.please_wait)
                        )
                    }
                }
                Snackbar(snackbarHostState)
            }
        }
    }
}