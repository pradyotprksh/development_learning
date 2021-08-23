package com.project.pradyotprakash.whatsappcompose.modules.chatUser.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.project.pradyotprakash.whatsappcompose.R
import com.project.pradyotprakash.whatsappcompose.models.User
import com.project.pradyotprakash.whatsappcompose.modules.UserViewModel
import com.project.pradyotprakash.whatsappcompose.modules.chatUser.viewModel.ChatUserViewModel
import com.project.pradyotprakash.whatsappcompose.ui.composables.BackButton
import com.project.pradyotprakash.whatsappcompose.ui.composables.CircularIndicatorMessage
import com.project.pradyotprakash.whatsappcompose.ui.composables.SizedBox
import com.project.pradyotprakash.whatsappcompose.ui.composables.Snackbar
import com.project.pradyotprakash.whatsappcompose.ui.theme.WhatsAppComposeTheme
import com.project.pradyotprakash.whatsappcompose.ui.theme.action25Bold
import com.project.pradyotprakash.whatsappcompose.ui.theme.black20Bold
import com.project.pradyotprakash.whatsappcompose.utils.Utility
import kotlinx.coroutines.launch

/**
 * A composable which will be used to show the user the list of messages sent/received
 * between them.
 *
 * [userId] will be the id of the user to which the chat has to be shown.
 */

@Composable
fun ChatViewUser(
    userId: String,
    back: () -> Unit,
    chatUserViewModel: ChatUserViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = SnackbarHostState()

    val loading: Boolean by chatUserViewModel.loading.observeAsState(initial = false)
    val showMessage: Boolean by chatUserViewModel.showMessage.observeAsState(initial = false)
    val isChatHistoryPresent: Boolean by chatUserViewModel.isChatHistoryPresent.observeAsState(
        initial = false
    )
    val message: String by chatUserViewModel.message.observeAsState(initial = "")
    val selectedUserDetails: User by chatUserViewModel.selectedUserDetails.observeAsState(initial = User())

    /**
     * States from user view model
     */
    val loadingUser: Boolean by userViewModel.loading.observeAsState(initial = false)
    val showMessageUser: Boolean by userViewModel.showMessage.observeAsState(initial = false)
    val messageUser: String by userViewModel.message.observeAsState(initial = "")

    if (!chatUserViewModel.firstCallDone) {
        userViewModel.getUserDetails()
        chatUserViewModel.checkForUserAndChat(userId)
        chatUserViewModel.firstCallDone = true
    }

    /**
     * Show snackbar
     */
    val showSnackbar = { messageData: String ->
        coroutineScope.launch {
            when (snackbarHostState.showSnackbar(
                message = messageData,
            )) {
                SnackbarResult.Dismissed -> {
                    chatUserViewModel.snackbarDismissed()
                }
                SnackbarResult.ActionPerformed -> {
                    chatUserViewModel.snackbarDismissed()
                }
            }
        }
    }

    if (showMessage) {
        Utility.showMessage(message = message)
        showSnackbar(message)
    }
    if (showMessageUser) {
        Utility.showMessage(message = messageUser)
        showSnackbar(messageUser)
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
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            contentAlignment = Alignment.BottomCenter,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        ) {
                            Image(
                                painter = rememberImagePainter(
                                    data = selectedUserDetails.profilePic,
                                    builder = {
                                        crossfade(true)
                                    }
                                ),
                                contentDescription = stringResource(id = R.string.image_description_network),
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop,
                                alpha = 0.3f
                            )
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Bottom
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    BackButton(
                                        back = back,
                                        icon = Icons.Default.ArrowBack,
                                        topSpace = 0
                                    )
                                    SizedBox(width = 15)
                                    Text(text = selectedUserDetails.name, style = black20Bold)
                                }
                                SizedBox(height = 10)
                            }
                        }
                    }
                    if (!isChatHistoryPresent) {
                        Text(
                            text = stringResource(id = R.string.send_hi),
                            style = action25Bold,
                            modifier = Modifier.clickable {
                                chatUserViewModel.sendMessage(
                                    "\uD83D\uDC4B",
                                    sentTo = userId,
                                    isFirstMessage = true
                                )
                            }
                        )
                    }
                    if (loading || loadingUser) {
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