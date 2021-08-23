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
package com.project.pradyotprakash.whatsappcompose.modules.chat.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.pradyotprakash.whatsappcompose.R
import com.project.pradyotprakash.whatsappcompose.models.ChatDetails
import com.project.pradyotprakash.whatsappcompose.modules.chat.viewModel.ChatViewModel
import com.project.pradyotprakash.whatsappcompose.ui.composables.CircularIndicatorMessage
import com.project.pradyotprakash.whatsappcompose.ui.composables.SizedBox
import com.project.pradyotprakash.whatsappcompose.ui.composables.Snackbar
import com.project.pradyotprakash.whatsappcompose.ui.theme.Notification
import com.project.pradyotprakash.whatsappcompose.ui.theme.WhatsAppComposeTheme
import com.project.pradyotprakash.whatsappcompose.ui.theme.black20Bold
import com.project.pradyotprakash.whatsappcompose.ui.theme.gray15
import com.project.pradyotprakash.whatsappcompose.ui.theme.lightGray15
import com.project.pradyotprakash.whatsappcompose.utils.Utility
import kotlinx.coroutines.launch

/**
 * A chat view which will allow user to see the list of chats and fav chats
 */

@Composable
fun ChatView(chatViewModel: ChatViewModel = viewModel()) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = SnackbarHostState()

    val loading: Boolean by chatViewModel.loading.observeAsState(initial = false)
    val showMessage: Boolean by chatViewModel.showMessage.observeAsState(initial = false)
    val message: String by chatViewModel.message.observeAsState(initial = "")
    val chats: List<ChatDetails> by chatViewModel.chats.observeAsState(initial = emptyList())

    if (!chatViewModel.firstCallDone) {
        chatViewModel.getChatList()
    }

    /**
     * Show snackbar
     */
    val showSnackbar = {
        coroutineScope.launch {
            when (snackbarHostState.showSnackbar(
                message = message,
            )) {
                SnackbarResult.Dismissed -> {
                    chatViewModel.snackbarDismissed()
                }
                SnackbarResult.ActionPerformed -> {
                    chatViewModel.snackbarDismissed()
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
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        item {
                            Text(text = stringResource(id = R.string.recents), style = black20Bold)
                            SizedBox(height = 10)
                        }
                        items(chats) { singleChat ->
                            SizedBox(height = 5)
                            Card(
                                elevation = 5.dp,
                                backgroundColor = Color.White,
                                border = if (singleChat.isLastMessageRead) BorderStroke(
                                    width = 1.dp,
                                    color = Notification
                                ) else null
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        Text(
                                            text = singleChat.otherUserDetails.name,
                                            style = black20Bold
                                        )
                                        SizedBox(height = 2)
                                        if (singleChat.isLastMessageByCurrentUser) {
                                            Text(
                                                text = "${stringResource(id = R.string.you)} ${singleChat.lastMessage}",
                                                style = gray15
                                            )
                                        } else {
                                            Text(text = singleChat.lastMessage, style = gray15)
                                        }
                                    }
                                    SizedBox(width = 10)
                                    Text(
                                        text = singleChat.lastMessageSentOnString,
                                        style = lightGray15
                                    )
                                }
                            }
                            SizedBox(height = 5)
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