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
package com.project.pradyotprakash.whatsappcompose.modules.chatUser.view.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.whatsappcompose.models.MessageDetails
import com.project.pradyotprakash.whatsappcompose.ui.composables.SizedBox
import com.project.pradyotprakash.whatsappcompose.ui.theme.Action
import com.project.pradyotprakash.whatsappcompose.ui.theme.black20
import com.project.pradyotprakash.whatsappcompose.ui.theme.white20

/**
 * A composable which shows the details of a single message
 */

@Composable
fun SingleMessage(message: MessageDetails) {
    Row(
        horizontalArrangement = if (message.messageByCurrentUser)
            Arrangement.End else Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth(
                if (message.messageByCurrentUser) 1f
                else 0.7f
            )
            .padding(bottom = 10.dp)
    ) {
        if (message.messageByCurrentUser)
            SizedBox(widthFloat = 0.3f)
        Card(
            elevation = 3.dp,
            backgroundColor = if (message.messageByCurrentUser)
                Color.White else Action,
            shape = RoundedCornerShape(30)
        ) {
            Text(
                text = message.message,
                style = if (message.messageByCurrentUser)
                    black20 else white20,
                modifier = Modifier.padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 15.dp,
                    bottom = 15.dp
                )
            )
        }
    }
}