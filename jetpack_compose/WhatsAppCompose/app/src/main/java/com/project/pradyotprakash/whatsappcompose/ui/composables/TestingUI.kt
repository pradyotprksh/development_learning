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
package com.project.pradyotprakash.whatsappcompose.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.whatsappcompose.ui.theme.black20Bold
import com.project.pradyotprakash.whatsappcompose.ui.theme.lightGray15
import com.project.pradyotprakash.whatsappcompose.ui.theme.link15
import com.project.pradyotprakash.whatsappcompose.utils.Constants

/**
 * A testing composable to check ui before adding it to the main ui
 */

@Composable
fun Stack() {
    Box(
        contentAlignment = Alignment.TopStart
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(top = 10.dp)
        )
        Box(
            modifier = Modifier
                .size(145.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Black)
                .padding(top = 5.dp)
        )
        Box(
            modifier = Modifier
                .size(140.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Blue)
                .padding(top = 0.dp)
        )
    }
}

@Composable
fun UserDetails() {
    Card(
        backgroundColor = Color.White,
        elevation = 3.dp
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularUrlImage(url = Constants.defaultPic.first(), size = 50)
            SizedBox(width = 10)
            Column {
                Text(text = "User full name", style = black20Bold)
                SizedBox(height = 5)
                Text(text = "@username", style = link15)
                SizedBox(height = 5)
                Text(text = "Status", style = lightGray15)
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun StackPreview() {
    Stack()
}

@Preview(showBackground = false)
@Composable
fun UserDetailsPreview() {
    UserDetails()
}