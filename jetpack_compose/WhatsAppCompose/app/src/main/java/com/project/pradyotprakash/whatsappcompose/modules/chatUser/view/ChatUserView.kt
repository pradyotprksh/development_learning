/*
 * MIT License
 *
 * Copyright (c) 2021 Pradyot Prakash
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
*/
package com.project.pradyotprakash.whatsappcompose.modules.chatUser.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import com.project.pradyotprakash.whatsappcompose.R
import com.project.pradyotprakash.whatsappcompose.models.MessageDetailsFirestore
import com.project.pradyotprakash.whatsappcompose.models.User
import com.project.pradyotprakash.whatsappcompose.modules.UserViewModel
import com.project.pradyotprakash.whatsappcompose.modules.chatUser.view.composables.SendHi
import com.project.pradyotprakash.whatsappcompose.modules.chatUser.view.composables.TopBarDetails
import com.project.pradyotprakash.whatsappcompose.modules.chatUser.viewModel.ChatUserViewModel
import com.project.pradyotprakash.whatsappcompose.ui.composables.CircularIndicatorMessage
import com.project.pradyotprakash.whatsappcompose.ui.composables.Snackbar
import com.project.pradyotprakash.whatsappcompose.ui.theme.WhatsAppComposeTheme
import com.project.pradyotprakash.whatsappcompose.utils.Utility
import kotlinx.coroutines.launch

/**
 * A composable which will be used to show the user the list of messages sent/received
 * between them.
 *
 * [userId] will be the id of the user to which the chat has to be shown.
 */

@ExperimentalCoilApi
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
    val messages: List<MessageDetailsFirestore> by chatUserViewModel.messages.observeAsState(initial = listOf())

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
                        TopBarDetails(
                            selectedUserDetails = selectedUserDetails,
                            back = back
                        )
                    }
                    if (!isChatHistoryPresent) {
                        SendHi {
                            chatUserViewModel.sendMessage(
                                "\uD83D\uDC4B",
                                sentTo = userId,
                                isFirstMessage = true
                            )
                        }
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