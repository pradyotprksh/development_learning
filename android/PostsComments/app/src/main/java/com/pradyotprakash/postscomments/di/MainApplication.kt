package com.pradyotprakash.postscomments.di

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        loggerInitialInitialization()
    }

    private fun loggerInitialInitialization() {
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}