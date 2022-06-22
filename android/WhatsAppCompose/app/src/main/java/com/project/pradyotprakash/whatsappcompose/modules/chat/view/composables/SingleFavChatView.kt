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
package com.project.pradyotprakash.whatsappcompose.modules.chat.view.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.project.pradyotprakash.whatsappcompose.R
import com.project.pradyotprakash.whatsappcompose.models.ChatDetails
import com.project.pradyotprakash.whatsappcompose.ui.composables.SizedBox
import com.project.pradyotprakash.whatsappcompose.ui.theme.Action
import com.project.pradyotprakash.whatsappcompose.ui.theme.Notification
import com.project.pradyotprakash.whatsappcompose.ui.theme.black15
import com.project.pradyotprakash.whatsappcompose.ui.theme.black20Bold

/**
 * A single fav chat list view which will be used to show the fav chats to the current
 * user
 */

@ExperimentalCoilApi
@Composable
fun SingleFavChatView(
    userMessage: (String) -> Unit,
    singleChat: ChatDetails,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (singleChat.singleChatListDetails.userId.isNotEmpty()) {
                    userMessage(singleChat.singleChatListDetails.userId)
                }
            }
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            Card(
                shape = RoundedCornerShape(15.dp),
                backgroundColor = Action,
                elevation = 4.dp,
                border = if (!singleChat.chatLastMessageRead) BorderStroke(
                    width = 1.dp,
                    color = Notification
                ) else null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 5.dp)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = singleChat.singleChatListDetails.profilePic,
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = stringResource(id = R.string.image_description_network),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentScale = ContentScale.Crop
                )
            }
            if (!singleChat.chatLastMessageRead) {
                Card(
                    backgroundColor = Color.White,
                    modifier = Modifier
                        .padding(start = 10.dp, bottom = 15.dp)
                ) {
                    Text(
                        text = singleChat.lastMessage,
                        style = black15,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(3.dp)
                    )
                }
            }
        }
        SizedBox(height = 10)
        Text(
            text = singleChat.singleChatListDetails.name,
            style = black20Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}