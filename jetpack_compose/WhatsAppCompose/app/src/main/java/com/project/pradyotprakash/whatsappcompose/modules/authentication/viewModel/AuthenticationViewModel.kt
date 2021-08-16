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
package com.project.pradyotprakash.whatsappcompose.modules.authentication.viewModel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider
import com.project.pradyotprakash.whatsappcompose.R
import com.project.pradyotprakash.whatsappcompose.utils.OtpSentCallbacks
import com.project.pradyotprakash.whatsappcompose.utils.PhoneAuthenticationUtility
import com.project.pradyotprakash.whatsappcompose.utils.VerifyOtpCallbacks
import java.lang.Exception
import kotlin.jvm.Throws

/**
 * A view model for the AuthenticationView which will do all the business logic and update the
 * ui state as per the requirement.
 */
class AuthenticationViewModel : ViewModel() {
    /**
     * Message to be shown to the user.
     */
    private val _message = MutableLiveData("")
    val message: LiveData<String> = _message

    /**
     * Is there is message to be shown to the user.
     */
    private val _showMessage = MutableLiveData(false)
    val showMessage: LiveData<Boolean> = _showMessage

    /**
     * Is a loader need to be shown to the user.
     */
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    /**
     * Is otp is sent to the number.
     */
    private val _otpSent = MutableLiveData(false)
    val otpSent: LiveData<Boolean> = _otpSent

    /**
     * Phone number entered by the user.
     */
    private val _phoneNumber = MutableLiveData("")
    val phoneNumber: LiveData<String> = _phoneNumber

    /**
     * OTP entered by the user.
     */
    private val _otp = MutableLiveData("")
    val otp: LiveData<String> = _otp

    var storedVerificationId: String? = null
    var resendToken: PhoneAuthProvider.ForceResendingToken? = null

    /**
     * Initiate the OTP for the entered number.
     *
     * [currentActivity] is the current activity which will be given to phone number authenticator.
     */
    fun sentOTPToPhoneNumber(currentActivity: AppCompatActivity?) {
        try {
            startPhoneOTP(currentActivity)
        } catch (exception: Exception) {
            _loading.value = false
            _showMessage.value = true
            _message.value = exception.localizedMessage
        }
    }

    /**
     * Verify the OTP code entered by the user.
     *
     * [currentActivity] is the current activity which will be given to phone number authenticator.
     *
     * [home] is the method which will be used to redirect the user to the home page if the
     * authentication was successful
     */
    fun verifyOTP(currentActivity: AppCompatActivity?, home: () -> Unit) {
        try {
            startOTPVerification(currentActivity, home)
        } catch (exception: IllegalArgumentException) {
            _loading.value = false
            _showMessage.value = true
            _message.value = exception.localizedMessage
        }
    }

    /**
     * Initiate the OTP for the entered number.
     *
     * [currentActivity] is the current activity which will be given to phone number authenticator.
     *
     * Throws an exception
     */
    @Throws(Exception::class)
    private fun startPhoneOTP(currentActivity: AppCompatActivity?) {
        val activity = currentActivity ?: throw IllegalArgumentException(
            currentActivity?.getString(
                R.string.something_went_wrong
            )
        )
        val number = phoneNumber.value
            ?: throw IllegalArgumentException(activity.getString(R.string.phone_number_empty))

        PhoneAuthenticationUtility.sendOtp(
            phoneNumber = number,
            activity = activity,
            callback = object : OtpSentCallbacks {
                override fun onOtpSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    _loading.value = false
                    storedVerificationId = verificationId
                    resendToken = token
                }

                override fun onError(message: String) {
                    _loading.value = false
                    _showMessage.value = true
                    _message.value = message
                }

                override fun onOtpInitiated() {
                    _loading.value = true
                }
            }
        )
    }

    /**
     * Start the otp verification
     *
     * [currentActivity] is the current activity which will be given to phone number authenticator.
     *
     * [home] is the method which will be used to redirect the user to the home page if the
     * authentication was successful
     *
     * Throws an exception
     */
    @Throws(Exception::class)
    private fun startOTPVerification(currentActivity: AppCompatActivity?, home: () -> Unit) {
        val verificationId = storedVerificationId ?: throw IllegalArgumentException(
            currentActivity?.getString(
                R.string.something_went_wrong
            )
        )
        val number = phoneNumber.value
            ?: throw IllegalArgumentException(
                currentActivity?.getString(
                    R.string.phone_number_empty
                )
            )
        val code = otp.value ?: throw IllegalArgumentException(
            "${
            currentActivity?.getString(
                R.string.otp_empty
            )
            } $number"
        )

        PhoneAuthenticationUtility.verifyOTP(
            verificationId = verificationId,
            code = code,
            callback = object : VerifyOtpCallbacks {
                override fun otpVerificationInitiated() {
                    _loading.value = true
                }

                override fun onVerified(user: FirebaseUser) {
                    home()
                }

                override fun onError(message: String) {
                    _loading.value = false
                    _showMessage.value = true
                    _message.value = message
                }
            }
        )
    }
}
