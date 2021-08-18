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

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider

/**
 * Callback for when OTP needs to be generated.
 */
interface OtpSentCallbacks {
    /**
     * OTP generation started.
     */
    fun onOtpInitiated()

    /**
     * OTP is sent on the specified number
     *
     * [verificationId] and [token] are the return values given when the OTP is sent.
     *
     * A custom [message] also will be returned to show the user that otp has been sent
     */
    fun onOtpSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken, message: String)

    /**
     * Return an error message whenever there is some error or exception
     *
     * [message] contains the message readable in human form
     */
    fun onError(message: String)
}

/**
 * Callbacks which are required when the OTP is being entered by the user.
 */
interface VerifyOtpCallbacks {
    /**
     * OTP verification has started.
     */
    fun otpVerificationInitiated()

    /**
     * If OTP was valid then this is called with the [user] object
     * which contains the details of the user from the firebase.
     */
    fun onVerified(user: FirebaseUser)

    /**
     * A callback which will be used to return any error
     *
     * [message] contains the message readable in human form
     */
    fun onError(message: String)
}
