package com.project.pradyotprakash.rental.di

import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.project.pradyotprakash.rental.data.AppCheckRepository
import com.project.pradyotprakash.rental.data.FirebaseAuthenticationDataRepository
import com.project.pradyotprakash.rental.data.FirestoreServiceRepository
import com.project.pradyotprakash.rental.domain.services.AppCheckService
import com.project.pradyotprakash.rental.domain.services.AuthenticationService
import com.project.pradyotprakash.rental.domain.services.BasicService
import com.project.pradyotprakash.rental.domain.services.FirebaseAuthenticationService
import com.project.pradyotprakash.rental.domain.services.FirestoreService
import com.project.pradyotprakash.rental.domain.services.PropertyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Inject all the services required.
 */
@Module
@InstallIn(SingletonComponent::class)
object Services {
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
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun provideFirebaseAppCheck(): FirebaseAppCheck = FirebaseAppCheck.getInstance()

    @Singleton
    @Provides
    fun provideAppCheckService(firebaseAppCheck: FirebaseAppCheck): AppCheckService =
        AppCheckRepository(firebaseAppCheck)

    @Singleton
    @Provides
    fun provideDataAuthenticationService(firebaseAuth: FirebaseAuth): FirebaseAuthenticationService =
        FirebaseAuthenticationDataRepository(firebaseAuth)

    @Singleton
    @Provides
    fun provideFirestoreService(firestore: FirebaseFirestore): FirestoreService =
        FirestoreServiceRepository(firestore)
}