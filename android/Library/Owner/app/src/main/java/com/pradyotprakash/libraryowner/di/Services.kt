package com.pradyotprakash.libraryowner.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.pradyotprakash.libraryowner.data.services.IpGeolocationService
import com.pradyotprakash.libraryowner.data.services.UnsplashService
import com.pradyotprakash.libraryowner.data.services.auth.FirebaseAuthenticationService
import com.pradyotprakash.libraryowner.data.services.auth.FirebaseAuthenticationServiceImplementation
import com.pradyotprakash.libraryowner.data.services.crashlytics.CrashlyticsService
import com.pradyotprakash.libraryowner.data.services.crashlytics.CrashlyticsServiceImplementation
import com.pradyotprakash.libraryowner.data.services.firestore.UserFirestoreService
import com.pradyotprakash.libraryowner.data.services.firestore.UserFirestoreServiceImplementation
import com.pradyotprakash.libraryowner.di.utils.IpGeolocationRetrofit
import com.pradyotprakash.libraryowner.di.utils.UnsplashRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Services {
    @Singleton
    @Provides
    fun provideUnsplashService(@UnsplashRetrofit retrofit: Retrofit): UnsplashService =
        retrofit.create(UnsplashService::class.java)

    @Singleton
    @Provides
    fun provideIpGeolocationService(@IpGeolocationRetrofit retrofit: Retrofit): IpGeolocationService =
        retrofit.create(IpGeolocationService::class.java)

    @Singleton
    @Provides
    fun provideCrashlyticsService(crashlytics: FirebaseCrashlytics): CrashlyticsService =
        CrashlyticsServiceImplementation(crashlytics)

    @Singleton
    @Provides
    fun provideAuthenticationService(
        firebaseAuth: FirebaseAuth
    ): FirebaseAuthenticationService = FirebaseAuthenticationServiceImplementation(firebaseAuth)

    @Singleton
    @Provides
    fun provideUserFirestoreService(
        firestore: FirebaseFirestore
    ): UserFirestoreService = UserFirestoreServiceImplementation(firestore)
}