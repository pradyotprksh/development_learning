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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import com.project.pradyotprakash.whatsappcompose.R
import com.project.pradyotprakash.whatsappcompose.models.MessageDetails
import com.project.pradyotprakash.whatsappcompose.models.User
import com.project.pradyotprakash.whatsappcompose.modules.UserViewModel
import com.project.pradyotprakash.whatsappcompose.modules.chatUser.view.composables.SendHi
import com.project.pradyotprakash.whatsappcompose.modules.chatUser.view.composables.SingleMessage
import com.project.pradyotprakash.whatsappcompose.modules.chatUser.view.composables.TopBarDetails
import com.project.pradyotprakash.whatsappcompose.modules.chatUser.viewModel.ChatUserViewModel
import com.project.pradyotprakash.whatsappcompose.ui.composables.CircularIndicatorMessage
import com.project.pradyotprakash.whatsappcompose.ui.composables.Snackbar
import com.project.pradyotprakash.whatsappcompose.ui.theme.Notification
import com.project.pradyotprakash.whatsappcompose.ui.theme.WhatsAppComposeTheme
import com.project.pradyotprakash.whatsappcompose.ui.theme.white20
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
    val typedMessage: String by chatUserViewModel.typedMessage.observeAsState(initial = "")
    val selectedUserDetails: User by chatUserViewModel.selectedUserDetails.observeAsState(initial = User())
    val messages: List<MessageDetails> by chatUserViewModel.messages.observeAsState(initial = listOf())

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
                    Box(
                        contentAlignment = Alignment.BottomCenter,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            TopBarDetails(
                                selectedUserDetails = selectedUserDetails,
                                back = back,
                                isFavorite = chatUserViewModel.currentChatDetails.chatIsFavourite,
                                changFav = {
                                    chatUserViewModel.updateFav(userId = userId)
                                },
                                showFav = isChatHistoryPresent
                            )
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(weight = 0.83f, fill = true),
                                contentPadding = PaddingValues(
                                    bottom = 85.dp,
                                    top = 15.dp,
                                    start = 15.dp,
                                    end = 15.dp
                                ),
                                reverseLayout = true
                            ) {
                                items(messages) { message ->
                                    SingleMessage(message = message)
                                }
                            }
                        }
                        if (isChatHistoryPresent) {
                            TextField(
                                value = typedMessage,
                                onValueChange = { chatUserViewModel.updateTypedMessage(it) },
                                colors = TextFieldDefaults.textFieldColors(
                                    textColor = Color.White,
                                    disabledTextColor = Color.Gray,
                                    backgroundColor = Notification,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.message_help),
                                        style = white20
                                    )
                                },
                                textStyle = white20,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Send,
                                    capitalization = KeyboardCapitalization.Sentences
                                ),
                                keyboardActions = KeyboardActions(
                                    onSend = {
                                        chatUserViewModel.sendAMessage(sentTo = userId)
                                    }
                                ),
                                shape = RoundedCornerShape(50),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp)
                            )
                        }
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