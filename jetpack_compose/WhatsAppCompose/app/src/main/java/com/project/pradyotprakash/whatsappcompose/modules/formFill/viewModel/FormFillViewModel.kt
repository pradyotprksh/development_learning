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
package com.project.pradyotprakash.whatsappcompose.modules.formFill.viewModel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.whatsappcompose.R
import com.project.pradyotprakash.whatsappcompose.models.User
import com.project.pradyotprakash.whatsappcompose.utils.FirestoreCallbacks
import com.project.pradyotprakash.whatsappcompose.utils.FirestoreUtility

/**
 * A view model for the FormFillView which will do all the business logic and update the
 * ui state as per the requirement.
 */
class FormFillViewModel : ViewModel() {
    private val firestoreUtility: FirestoreUtility = FirestoreUtility()

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
     * Full name of the user
     */
    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    /**
     * Whenever there is a change in the text field for name, then this method will
     * be used to update the value and the UI state.
     */
    fun onNameChange(value: String) {
        _name.value = value
    }

    /**
     * Username of the user
     */
    private val _username = MutableLiveData("")
    val username: LiveData<String> = _username

    /**
     * Whenever there is a change in the text field for username, then this method will
     * be used to update the value and the UI state.
     */
    fun onUsernameChange(value: String) {
        _username.value = value
    }

    /**
     * Email id of the user
     */
    private val _emailID = MutableLiveData("")
    val emailID: LiveData<String> = _emailID

    /**
     * Whenever there is a change in the text field for email id, then this method will
     * be used to update the value and the UI state.
     */
    fun onEmailIdChange(value: String) {
        _emailID.value = value
    }

    /**
     * Status of the user
     */
    private val _status = MutableLiveData("")
    val status: LiveData<String> = _status

    /**
     * Whenever there is a change in the text field for status, then this method will
     * be used to update the value and the UI state.
     */
    fun onStatusChange(value: String) {
        _status.value = value
    }

    /**
     * Update user details
     */
    fun updateUserDetails(currentActivity: Activity?, home: () -> Unit) {
        if (loading.value == true) return
        try {
            _loading.value = true
            startUserDetailsUpdate(currentActivity, home)
        } catch (exception: Exception) {
            _loading.value = false
            _showMessage.value = true
            _message.value = exception.localizedMessage ?: ""
        }
    }

    /**
     * Update the user details to the database
     */
    private fun startUserDetailsUpdate(currentActivity: Activity?, home: () -> Unit) {
        val activity = getActivity(currentActivity)
        val name = getName(activity)
        val username = getUsername(activity)
        val emailId = getEmailId(activity)
        val status = getStatus(activity)

        firestoreUtility.checksForUserDetails(
            callbacks = object : FirestoreCallbacks {
                override fun isTrue() {
                    _loading.value = false
                }

                override fun isFalse() {
                    _loading.value = false
                    _showMessage.value = true
                    _message.value = activity.getString(R.string.something_went_wrong)
                }

                override fun userDetails(user: User) {
                    user.name = name
                    user.userName = username
                    user.emailId = emailId
                    user.status = status
                    user.isDetailsAdded = true

                    updateUserDetail(user, home)
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
     * Update the user details finally on firestore after getting the current details
     */
    private fun updateUserDetail(user: User, home: () -> Unit) {
        firestoreUtility.setUserDetails(
            data = user,
            callbacks = object : FirestoreCallbacks {
                override fun isTrue() {
                    _loading.value = false
                    home()
                }

                override fun isFalse() {
                    _loading.value = false
                    home()
                }

                override fun userDetails(user: User) {
                    _loading.value = false
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
     * Returns a non-nullable name
     *
     * Throws exception
     */
    @kotlin.jvm.Throws(IllegalArgumentException::class)
    private fun getName(activity: Activity): String {
        val name = _name.value
            ?: throw IllegalArgumentException(activity.getString(R.string.name_empty))
        if (name.isEmpty()) {
            throw IllegalArgumentException(activity.getString(R.string.name_empty))
        }
        return name
    }

    /**
     * Returns a non-nullable username
     *
     * Throws exception
     */
    @kotlin.jvm.Throws(IllegalArgumentException::class)
    private fun getUsername(activity: Activity): String {
        val username = _username.value
            ?: throw IllegalArgumentException(activity.getString(R.string.username_empty))
        if (username.isEmpty()) {
            throw IllegalArgumentException(activity.getString(R.string.username_empty))
        }
        return username
    }

    /**
     * Returns a non-nullable email id
     *
     * Throws exception
     */
    @kotlin.jvm.Throws(IllegalArgumentException::class)
    private fun getEmailId(activity: Activity): String {
        val emailID = _emailID.value
            ?: throw IllegalArgumentException(activity.getString(R.string.email_empty))
        if (emailID.isEmpty()) {
            throw IllegalArgumentException(activity.getString(R.string.email_empty))
        }
        return emailID
    }

    /**
     * Returns a non-nullable status
     *
     * Throws exception
     */
    @kotlin.jvm.Throws(IllegalArgumentException::class)
    private fun getStatus(activity: Activity): String {
        val status = _status.value
            ?: throw IllegalArgumentException(activity.getString(R.string.status_empty))
        if (status.isEmpty()) {
            throw IllegalArgumentException(activity.getString(R.string.status_empty))
        }
        return status
    }
}