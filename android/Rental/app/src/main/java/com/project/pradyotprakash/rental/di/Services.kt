package com.project.pradyotprakash.rental.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.pradyotprakash.rental.data.FirebaseAuthenticationDataRepository
import com.project.pradyotprakash.rental.domain.services.AuthenticationService
import com.project.pradyotprakash.rental.domain.services.BasicService
import com.project.pradyotprakash.rental.domain.services.FirebaseAuthenticationService
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
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Singleton
    @Provides
    fun provideDataAuthenticationService(firebaseAuth: FirebaseAuth): FirebaseAuthenticationService =
        FirebaseAuthenticationDataRepository(firebaseAuth)
}