package com.pradyotprakash.libraryowner.di

import android.app.Application
import com.google.firebase.appcheck.ktx.appCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OwnerApplication : Application() {
    override fun onCreate() {
        setupFirebase()
        super.onCreate()
    }

    private fun setupFirebase() {
        Firebase.appCheck.installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance(),
        )
    }
}