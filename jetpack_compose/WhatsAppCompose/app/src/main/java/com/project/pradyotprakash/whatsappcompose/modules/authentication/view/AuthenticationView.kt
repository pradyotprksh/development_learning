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
package com.project.pradyotprakash.whatsappcompose.modules.authentication.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.pradyotprakash.whatsappcompose.modules.authentication.viewModel.AuthenticationViewModel
import com.project.pradyotprakash.whatsappcompose.ui.theme.WhatsAppComposeTheme

/**
 * The authentication view which will be used to give the user an option to authenticate
 * themselves and start using the application.
 *
 * Will be using phone number login to authenticate the user. This will be similar to WhatsApp.
 *
 * [authenticationViewModel] will be used to do all the business logic and update the view
 * state when required.
 */

@Composable
fun AuthenticationView(authenticationViewModel: AuthenticationViewModel = viewModel()) {
    val loading: Boolean by authenticationViewModel.loading.observeAsState(initial = false)
    val showMessage: Boolean by authenticationViewModel.showMessage.observeAsState(initial = false)
    val otpSent: Boolean by authenticationViewModel.otpSent.observeAsState(initial = false)
    val message: String by authenticationViewModel.message.observeAsState(initial = "")
    val phoneNumber: String by authenticationViewModel.phoneNumber.observeAsState(initial = "")
    val otp: String by authenticationViewModel.otp.observeAsState(initial = "")

    WhatsAppComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                if (loading) {
                    CircularProgressIndicator()
                }
                if (showMessage) {
                }
            }
        }
    }
}
