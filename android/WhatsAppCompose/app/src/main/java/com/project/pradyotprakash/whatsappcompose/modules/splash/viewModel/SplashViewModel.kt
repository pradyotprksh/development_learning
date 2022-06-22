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
package com.project.pradyotprakash.whatsappcompose.modules.splash.viewModel

import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.whatsappcompose.models.User
import com.project.pradyotprakash.whatsappcompose.utils.FirestoreCallbacks
import com.project.pradyotprakash.whatsappcompose.utils.FirestoreUtility
import com.project.pradyotprakash.whatsappcompose.utils.Utility

/**
 * View model for  SplashView.
 *
 * This will contain the business logic related to splash
 * and also might be required to change the UI state of it.
 */
class SplashViewModel : ViewModel() {
    private val firestoreUtility: FirestoreUtility = FirestoreUtility()

    /**
     * Check if user is logged in or not
     *
     * If logged in call the [home] function, otherwise
     * call the [authentication] function.
     */
    fun checkIfUserLoggedIn(home: () -> Unit, authentication: () -> Unit, formFill: () -> Unit) {
        if (firestoreUtility.firebaseAuth.currentUser != null) {
            firestoreUtility.checksForUserDetails(
                callbacks = object : FirestoreCallbacks {
                    override fun isFalse() {
                        home()
                    }

                    override fun userDetails(user: User) {
                        user.lastLoggedIn = Utility.currentTimeStamp()
                        user.appVersion = Utility.applicationVersion()
                        user.deviceId = Utility.getDeviceId()
                        user.deviceModel = Utility.deviceModel()
                        user.deviceOs = Utility.systemOS()

                        updateUserDetails(user, home, formFill, user.isDetailsAdded)
                    }

                    override fun onError(message: String) {
                        home()
                    }
                }
            )
        } else {
            authentication()
        }
    }

    /**
     * Update the user details
     */
    private fun updateUserDetails(
        user: User,
        home: () -> Unit,
        formFill: () -> Unit,
        isUserDetailsAdded: Boolean
    ) {
        firestoreUtility.setUserDetails(
            data = user,
            callbacks = object : FirestoreCallbacks {
                override fun isTrue() {
                    navigateTo(home, formFill, isUserDetailsAdded)
                }

                override fun onError(message: String) {
                    Utility.showMessage(message = message)
                    navigateTo(home, formFill, isUserDetailsAdded)
                }
            }
        )
    }

    /**
     * Navigate to depending on [isUserDetailsAdded]
     */
    private fun navigateTo(home: () -> Unit, formFill: () -> Unit, isUserDetailsAdded: Boolean) {
        if (isUserDetailsAdded) {
            home()
        } else {
            formFill()
        }
    }
}
