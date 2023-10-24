package com.pradyotprakash.libraryowner.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

object Firebase {
    @Module
    @InstallIn(SingletonComponent::class)
    object Authentication {
        @Singleton
        @Provides
        fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object Crashlytics {
        @Singleton
        @Provides
        fun provideFirebaseCrashlytics(): FirebaseCrashlytics = Firebase.crashlytics
    }
}