package com.project.pradyotprakash.rental.di

import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.project.pradyotprakash.rental.data.repositories.AppCheckRepository
import com.project.pradyotprakash.rental.data.repositories.FirebaseAuthenticationDataRepository
import com.project.pradyotprakash.rental.data.repositories.FirestoreServiceRepository
import com.project.pradyotprakash.rental.data.repositories.StorageServiceRepository
import com.project.pradyotprakash.rental.core.services.AppCheckService
import com.project.pradyotprakash.rental.data.services.AuthenticationService
import com.project.pradyotprakash.rental.data.services.BasicService
import com.project.pradyotprakash.rental.core.services.FirebaseAuthenticationService
import com.project.pradyotprakash.rental.core.services.FirestoreService
import com.project.pradyotprakash.rental.data.services.PropertyService
import com.project.pradyotprakash.rental.core.services.StorageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
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
    fun provideFirebaseStorage(): FirebaseStorage = Firebase.storage

    @Singleton
    @Provides
    @Named(Constants.propertyStorageReference)
    fun providePropertyStorageReference(storage: FirebaseStorage): StorageReference =
        storage.getReference("${Constants.propertyStorageReference}/")

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

    @Singleton
    @Provides
    fun provideStorageService(): StorageService = StorageServiceRepository()
}