package com.project.pradyotprakash.rental.di

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * The main application of the project.
 */
@HiltAndroidApp
class MainApplication : Application() {
    @Inject
    lateinit var firebaseAppCheck: FirebaseAppCheck

    override fun onCreate() {
        super.onCreate()

        firebaseInitializations()
    }

    private fun firebaseInitializations() {
        FirebaseApp.initializeApp(this)
        firebaseAppCheck.installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance(),
        )
        firebaseAppCheck.installAppCheckProviderFactory(
            SafetyNetAppCheckProviderFactory.getInstance(),
        )
    }
}