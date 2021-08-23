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
package com.project.pradyotprakash.whatsappcompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),
    h1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    h3 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
)

val white30Bold = TextStyle(
    color = Color.White,
    fontSize = 30.sp,
    fontWeight = FontWeight.Bold,
)

val black20Bold = TextStyle(
    color = Color.Black,
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold,
)

val black15Bold = TextStyle(
    color = Color.Black,
    fontSize = 15.sp,
    fontWeight = FontWeight.Bold,
)

val actionBold = TextStyle(
    fontWeight = FontWeight.Bold,
    color = Action
)

val gray15 = TextStyle(
    color = Color.Gray,
    fontSize = 15.sp,
)

val gray12Italic = TextStyle(
    color = Color.Gray,
    fontSize = 15.sp,
    fontStyle = FontStyle.Italic
)

val black15 = TextStyle(
    color = Color.Black,
    fontSize = 15.sp,
)

val gray20Bold = TextStyle(
    color = Color.Gray,
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold,
)

val white20Bold = TextStyle(
    color = Color.White,
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold,
)

val white20Bold60 = TextStyle(
    color = Color.White.copy(alpha = 0.6f),
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold,
)

val link15 = TextStyle(
    color = Color.Blue,
    fontSize = 20.sp,
)

val lightGray15 = TextStyle(
    color = Color.LightGray,
    fontSize = 15.sp,
)

val red30Bold = TextStyle(
    color = Color.Red,
    fontSize = 30.sp,
    fontWeight = FontWeight.Bold
)

val action25Bold = TextStyle(
    color = Action,
    fontSize = 25.sp,
    fontWeight = FontWeight.Bold
)
