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
package com.project.pradyotprakash.whatsappcompose.utils

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

/**
 * A phone authentication utility method which will be helpful for doing all the authentication
 * related to phone login.
 */
object PhoneAuthenticationUtility {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Send the OTP.
     *
     * This method will initiate the OTP from the firebase.
     *
     * [phoneNumber] will be the number to which the OTP will be sent
     *
     * [activity] will be the current activity of the screen
     *
     * [callback] will be used to update the code if needed to
     */
    fun sendOtp(
        phoneNumber: String,
        activity: AppCompatActivity,
        callback: OtpSentCallbacks
    ) {
        callback.onOtpInitiated()

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                callback.onOtpSent(verificationId = verificationId, token = token)
            }

            override fun onCodeAutoRetrievalTimeOut(verificationId: String) {
            }

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                callback.onError(exception.localizedMessage ?: "")
            }
        }

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(
                callbacks
            )
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    /**
     * Verify the OTP.
     *
     * After OTP is sent this method will be used to check if the OTP entered by the user is
     * valid or not.
     *
     * [verificationId] will be the ID which was generated when the OTP was going to be
     * initiated
     *
     * [code] is the OTP entered by the User
     *
     * [callback] will be used to update the code if needed to
     */
    fun verifyOTP(
        verificationId: String,
        code: String,
        callback: VerifyOtpCallbacks
    ) {
        callback.otpVerificationInitiated()

        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    user?.let { callback.onVerified(user = it) }
                } else {
                    task.exception?.localizedMessage?.let { callback.onError(message = it) }
                }
            }
    }
}
