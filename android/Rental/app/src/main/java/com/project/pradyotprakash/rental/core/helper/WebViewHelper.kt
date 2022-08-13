package com.project.pradyotprakash.rental.core.helper

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

object WebViewHelper {
    fun launchWebView(context: Context, url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}