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
package com.project.pradyotprakash.whatsappcompose.modules.search.view.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.whatsappcompose.ui.composables.CircularUrlImage
import com.project.pradyotprakash.whatsappcompose.ui.composables.SizedBox
import com.project.pradyotprakash.whatsappcompose.ui.theme.black20Bold
import com.project.pradyotprakash.whatsappcompose.ui.theme.lightGray15
import com.project.pradyotprakash.whatsappcompose.ui.theme.link15

/**
 * A single card for the user to show the basic details.
 *
 * This will be used to show the current user about another user only the few details which are
 * required in a list mostly.
 */

@Composable
fun SingleUserCard(
    profileImage: String,
    title: String,
    subTitle: String = "",
    thirdLine: String = "",
    userId: String = "",
    userMessage: (String) -> Unit
) {
    Card(
        backgroundColor = Color.White,
        elevation = 3.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                userMessage(userId)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CircularUrlImage(url = profileImage, size = 50)
            SizedBox(width = 10)
            Column {
                Text(text = title, style = black20Bold)
                if (subTitle.isNotEmpty() || thirdLine.isNotEmpty()) {
                    SizedBox(height = 5)
                }
                if (subTitle.isNotEmpty()) {
                    Text(text = "@$subTitle", style = link15)
                }
                if (subTitle.isNotEmpty() || thirdLine.isNotEmpty()) {
                    SizedBox(height = 5)
                }
                if (thirdLine.isNotEmpty()) {
                    Text(text = thirdLine, style = lightGray15)
                }
            }
        }
    }
}