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
package com.project.pradyotprakash.whatsappcompose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.project.pradyotprakash.whatsappcompose.modules.authentication.view.AuthenticationView
import com.project.pradyotprakash.whatsappcompose.modules.chatUser.view.ChatViewUser
import com.project.pradyotprakash.whatsappcompose.modules.formFill.view.FormFillView
import com.project.pradyotprakash.whatsappcompose.modules.home.view.HomeView
import com.project.pradyotprakash.whatsappcompose.modules.profile.view.ProfileView
import com.project.pradyotprakash.whatsappcompose.modules.splash.view.SplashView
import com.project.pradyotprakash.whatsappcompose.nav.Action
import com.project.pradyotprakash.whatsappcompose.nav.Destination.Authentication
import com.project.pradyotprakash.whatsappcompose.nav.Destination.ChatUser
import com.project.pradyotprakash.whatsappcompose.nav.Destination.FormFill
import com.project.pradyotprakash.whatsappcompose.nav.Destination.Home
import com.project.pradyotprakash.whatsappcompose.nav.Destination.Profile
import com.project.pradyotprakash.whatsappcompose.nav.Destination.Splash
import com.project.pradyotprakash.whatsappcompose.ui.theme.WhatsAppComposeTheme
import com.project.pradyotprakash.whatsappcompose.utils.Constants

/**
 * The main Navigation composable which will handle all the navigation stack.
 */
@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun NavComposeApp() {
    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }
    WhatsAppComposeTheme {
        NavHost(
            navController = navController,
            startDestination = Splash
        ) {
            composable(Splash) {
                SplashView(
                    home = actions.home,
                    authentication = actions.authentication,
                    formFill = actions.formFill
                )
            }
            composable(Authentication) {
                AuthenticationView(
                    home = actions.home,
                    formFill = actions.formFill
                )
            }
            composable(Home) {
                HomeView(
                    profile = actions.profile,
                    userMessage = actions.chatUser
                )
            }
            composable(FormFill) {
                FormFillView(home = actions.home)
            }
            composable(Profile) {
                ProfileView(
                    back = actions.navigateBack,
                    splash = actions.splash
                )
            }
            composable(ChatUser) {
                val userId = it.arguments?.getString(Constants.userId) ?: ""
                ChatViewUser(
                    userId = userId,
                    back = actions.navigateBack
                )
            }
        }
    }
}
