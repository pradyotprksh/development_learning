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

import android.os.Build
import android.util.Log
import com.project.pradyotprakash.whatsappcompose.BuildConfig
import com.project.pradyotprakash.whatsappcompose.utils.Constants.application

/**
 * An utility method which will contain the utility methods, helpful for doing the similar methods
 * throughout the application.
 *
 * This will help in better code maintenance.
 */
object Utility {
    /**
     * Show a log message in the console
     */
    fun showMessage(message: String) {
        Log.w(application, message)
    }

    /**
     * Get current time stamp
     */
    fun currentTimeStamp(): Long {
        return System.currentTimeMillis()
    }

    /**
     * Get application version
     */
    fun applicationVersion(): String {
        return "${BuildConfig.VERSION_NAME}:${BuildConfig.VERSION_CODE}"
    }

    /**
     * Get device id
     */
    fun getDeviceId(): String {
        return Build.ID
    }

    /**
     * Get device model
     */
    fun deviceModel(): String {
        return  "${Build.MODEL} ${Build.BRAND} ${Build.DEVICE}"
    }

    /**
     * Get phone OS
     */
    fun systemOS(): String {
        return  "${Build.ID} ${Build.VERSION.SDK_INT} ${Build.VERSION.CODENAME}"
    }
}
