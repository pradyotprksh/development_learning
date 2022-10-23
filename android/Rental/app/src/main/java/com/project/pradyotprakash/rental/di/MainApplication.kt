package com.project.pradyotprakash.rental.di

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


/**
 * The main application of the project.
 */
@HiltAndroidApp
class MainApplication : Application() {
    // TODO: AdMob
    // TODO: Location wise result for renter

    @Inject
    lateinit var firebaseAppCheck: FirebaseAppCheck

    override fun onCreate() {
        super.onCreate()

        firebaseInitializations()
        loggerInitialInitialization()
    }

    private fun loggerInitialInitialization() {
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    private fun firebaseInitializations() {
        FirebaseApp.initializeApp(this)

        setupFirebaseAppCheck()
    }

    private fun setupFirebaseAppCheck() {
        firebaseAppCheck.installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance(),
        )
        firebaseAppCheck.installAppCheckProviderFactory(
            SafetyNetAppCheckProviderFactory.getInstance(),
        )
    }
}