package com.project.pradyotprakash.rental.di

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.project.pradyotprakash.rental.core.services.AppCheckService
import com.project.pradyotprakash.rental.core.services.CrashlyticsService
import com.project.pradyotprakash.rental.core.services.FirebaseAuthenticationService
import com.project.pradyotprakash.rental.core.services.FirestoreService
import com.project.pradyotprakash.rental.core.services.StorageService
import com.project.pradyotprakash.rental.data.repositories.AppCheckRepository
import com.project.pradyotprakash.rental.data.repositories.CrashlyticsRepository
import com.project.pradyotprakash.rental.data.repositories.FirebaseAuthenticationDataRepository
import com.project.pradyotprakash.rental.data.repositories.FirestoreServiceRepository
import com.project.pradyotprakash.rental.data.repositories.StorageServiceRepository
import com.project.pradyotprakash.rental.data.services.AuthenticationService
import com.project.pradyotprakash.rental.data.services.BasicService
import com.project.pradyotprakash.rental.data.services.LocationService
import com.project.pradyotprakash.rental.data.services.PropertyService
import com.project.pradyotprakash.rental.data.services.SearchService
import com.project.pradyotprakash.rental.device.repositories.UserLocalServicesRepository
import com.project.pradyotprakash.rental.device.services.UserLocalServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Inject all the services required.
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitServices {
    @Singleton
    @Provides
    fun provideBasicService(retrofit: Retrofit): BasicService =
        retrofit.create(BasicService::class.java)

    @Singleton
    @Provides
    fun provideAuthenticationService(retrofit: Retrofit): AuthenticationService =
        retrofit.create(AuthenticationService::class.java)

    @Singleton
    @Provides
    fun providePropertyService(retrofit: Retrofit): PropertyService =
        retrofit.create(PropertyService::class.java)

    @Singleton
    @Provides
    fun provideSearchService(retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)

    @Singleton
    @Provides
    fun provideLocationService(retrofit: Retrofit): LocationService =
        retrofit.create(LocationService::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
object LocalStorageServices {
    @Singleton
    @Provides
    fun providesSharedPreference(
        @ApplicationContext context: Context,
    ): SharedPreferences =
        context.getSharedPreferences(Constants.sharedPreferenceName, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideUserLocalService(
        sharedPreferences: SharedPreferences,
    ): UserLocalServices = UserLocalServicesRepository(sharedPreferences)
}

object FirebaseServices {
    @Module
    @InstallIn(SingletonComponent::class)
    object Authentication {
        @Singleton
        @Provides
        fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

        @Singleton
        @Provides
        fun provideDataAuthenticationService(
            firebaseAuth: FirebaseAuth,
            crashlytics: CrashlyticsService
        ): FirebaseAuthenticationService =
            FirebaseAuthenticationDataRepository(firebaseAuth, crashlytics)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object Firestore {
        @Singleton
        @Provides
        fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

        @Singleton
        @Provides
        fun provideFirestoreService(firestore: FirebaseFirestore): FirestoreService =
            FirestoreServiceRepository(firestore)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object Storage {
        @Singleton
        @Provides
        fun provideFirebaseStorage(): FirebaseStorage = Firebase.storage

        @Singleton
        @Provides
        fun provideStorageService(crashlytics: CrashlyticsService): StorageService =
            StorageServiceRepository(crashlytics)

        @Singleton
        @Provides
        @Named(Constants.propertyStorageReference)
        fun providePropertyStorageReference(storage: FirebaseStorage): StorageReference =
            storage.getReference("${Constants.propertyStorageReference}/")

        @Singleton
        @Provides
        @Named(Constants.userStorageReference)
        fun provideUserStorageReference(storage: FirebaseStorage): StorageReference =
            storage.getReference("${Constants.userStorageReference}/")
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object Crashlytics {
        @Singleton
        @Provides
        fun provideFirebaseCrashlytics(): FirebaseCrashlytics = Firebase.crashlytics

        @Singleton
        @Provides
        fun provideCrashlyticsService(crashlytics: FirebaseCrashlytics): CrashlyticsService =
            CrashlyticsRepository(crashlytics)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object AppCheck {
        @Singleton
        @Provides
        fun provideFirebaseAppCheck(): FirebaseAppCheck = FirebaseAppCheck.getInstance()

        @Singleton
        @Provides
        fun provideAppCheckService(
            firebaseAppCheck: FirebaseAppCheck,
            crashlytics: CrashlyticsService
        ): AppCheckService =
            AppCheckRepository(firebaseAppCheck, crashlytics)
    }
}