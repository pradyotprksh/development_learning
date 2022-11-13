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
    // TODO: Permission handler doesn't call onGranted or onDenies on permission state change
    // TODO: Better the Python Backend Base URL update process
    // TODO: Add delete image feature for profile and property screen

    // TODO: Fix after adding details user gets logged out
    // TODO: Fix location permission flow, when user allow location then the API should be called for fetching the user details

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