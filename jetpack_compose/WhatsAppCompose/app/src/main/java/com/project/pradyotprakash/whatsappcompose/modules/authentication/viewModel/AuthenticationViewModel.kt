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

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider
import com.project.pradyotprakash.whatsappcompose.R
import com.project.pradyotprakash.whatsappcompose.utils.FirestoreUtility
import com.project.pradyotprakash.whatsappcompose.utils.OtpSentCallbacks
import com.project.pradyotprakash.whatsappcompose.utils.PhoneAuthenticationUtility
import com.project.pradyotprakash.whatsappcompose.utils.VerifyOtpCallbacks
import io.github.farhanroy.cccp.CCPCountry
import io.github.farhanroy.cccp.getLibraryMasterCountriesEnglish

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
     * Update show message and value when the snack bar is dismissed.
     *
     * This is required to let the compose know that the snackbar is being dismissed so when
     * next time the snackbar needs to be re-build it will do that instead of not re-building it
     * since the value has not changed.
     */
    fun snackbarDismissed() {
        _showMessage.value = false
        _message.value = ""
    }

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
     * Selected country details
     */
    var country: CCPCountry = getLibraryMasterCountriesEnglish().first()

    /**
     * Phone number entered by the user.
     */
    private val _phoneNumber = MutableLiveData("")
    val phoneNumber: LiveData<String> = _phoneNumber

    /**
     * Whenever there is a change in the text field for phone number, then this method will
     * be used to update the value and the UI state.
     */
    fun onPhoneNumberChange(value: String) {
        _phoneNumber.value = value
    }

    /**
     * OTP entered by the user.
     */
    private val _otp = MutableLiveData("")
    val otp: LiveData<String> = _otp

    /**
     * Whenever there is a change in the text field for OTP, then this method will
     * be used to update the value and the UI state.
     */
    fun onOTPChange(value: String) {
        _otp.value = value
    }

    var storedVerificationId: String? = null

    /**
     * Initiate the OTP for the entered number.
     *
     * [currentActivity] is the current activity which will be given to phone number authenticator.
     */
    fun sendOTPToPhoneNumber(currentActivity: Activity?) {
        if (loading.value == true) return
        try {
            startPhoneOTP(currentActivity)
        } catch (exception: Exception) {
            _loading.value = false
            _showMessage.value = true
            _message.value = exception.localizedMessage ?: ""
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
    fun verifyOTP(currentActivity: Activity?, home: () -> Unit) {
        if (loading.value == true) return
        try {
            startOTPVerification(currentActivity, home)
        } catch (exception: IllegalArgumentException) {
            _loading.value = false
            _showMessage.value = true
            _message.value = exception.localizedMessage ?: ""
        }
    }

    /**
     * Initiate the OTP for the entered number.
     *
     * [currentActivity] is the current activity which will be given to phone number authenticator.
     */
    private fun startPhoneOTP(currentActivity: Activity?) {
        val activity = getActivity(currentActivity)
        val code = getCountryCode()
        val number = getPhoneNumber(activity)

        PhoneAuthenticationUtility.sendOtp(
            phoneNumber = "$code$number",
            activity = activity,
            callback = object : OtpSentCallbacks {
                override fun onOtpSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken,
                    message: String
                ) {
                    _loading.value = false
                    _otpSent.value = true
                    _showMessage.value = true
                    _message.value = message
                    storedVerificationId = verificationId
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
     */
    private fun startOTPVerification(
        currentActivity: Activity?,
        home: () -> Unit,
    ) {
        val activity = getActivity(currentActivity)
        val verificationId = getVerificationId(activity)
        val cCode = getCountryCode()
        val number = getPhoneNumber(activity)
        val code = getOtpCode(activity, cCode, number)

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

    /**
     * Returns a non-nullable activity
     *
     * Throws exception
     */
    @kotlin.jvm.Throws(IllegalArgumentException::class)
    private fun getActivity(currentActivity: Activity?): Activity {
        return currentActivity ?: throw IllegalArgumentException()
    }

    /**
     * Returns a non-nullable country code
     *
     * Throws exception
     */
    @kotlin.jvm.Throws(IllegalArgumentException::class)
    private fun getCountryCode(): String {
        return "+${country.phoneCode}"
    }

    /**
     * Returns a non-nullable phone number
     *
     * Throws exception
     */
    @kotlin.jvm.Throws(IllegalArgumentException::class)
    private fun getPhoneNumber(activity: Activity): String {
        val number = phoneNumber.value
            ?: throw IllegalArgumentException(activity.getString(R.string.phone_number_empty))
        if (number.isEmpty()) {
            throw IllegalArgumentException(activity.getString(R.string.phone_number_empty))
        }
        return number
    }

    /**
     * Returns a non-nullable verification id
     *
     * Throws exception
     */
    @kotlin.jvm.Throws(IllegalArgumentException::class)
    private fun getVerificationId(activity: Activity): String {
        return storedVerificationId ?: throw IllegalArgumentException(
            activity.getString(
                R.string.something_went_wrong
            )
        )
    }

    /**
     * Return a non-nullable OTP value
     *
     * Throws exception
     */
    @kotlin.jvm.Throws(IllegalArgumentException::class)
    private fun getOtpCode(activity: Activity, cCode: String, number: String): String {
        val code = otp.value ?: throw IllegalArgumentException(
            "${
                activity.getString(
                    R.string.otp_empty
                )
            } $cCode$number"
        )
        if (code.isEmpty()) {
            throw IllegalArgumentException(
                "${
                    activity.getString(
                        R.string.otp_empty
                    )
                } $cCode$number"
            )
        }
        return code
    }
}
