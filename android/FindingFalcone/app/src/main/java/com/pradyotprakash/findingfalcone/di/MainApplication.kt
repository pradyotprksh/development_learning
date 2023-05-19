package com.pradyotprakash.findingfalcone.di

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp

/**
 * The main application of the project.
 */
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