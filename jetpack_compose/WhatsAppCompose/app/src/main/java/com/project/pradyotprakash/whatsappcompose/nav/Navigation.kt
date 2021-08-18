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
package com.project.pradyotprakash.whatsappcompose.nav

import androidx.navigation.NavHostController
import com.project.pradyotprakash.whatsappcompose.nav.Destination.Authentication
import com.project.pradyotprakash.whatsappcompose.nav.Destination.EditProfile
import com.project.pradyotprakash.whatsappcompose.nav.Destination.Home
import com.project.pradyotprakash.whatsappcompose.nav.Destination.Splash

/**
 * A set of destination used in the whole application
 */
object Destination {
    const val Splash = "splash"
    const val Authentication = "authentication"
    const val Home = "home"
    const val EditProfile = "edit-profile"
}

/**
 * Set of routes which will be passed to different composable so that
 * the routes which are required can be taken.
 */
class Action(navController: NavHostController) {
    val splash: () -> Unit = { navController.navigate(Splash) }
    val authentication: () -> Unit = { navController.navigate(Authentication) }
    val home: () -> Unit = {
        navController.navigate(Home) {
            popUpTo(Splash)
            popUpTo(Authentication)
        }
    }
    val editProfile: () -> Unit = { navController.navigate(EditProfile) }
    val navigateBack: () -> Unit = { navController.popBackStack() }
}
