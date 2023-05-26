package com.pradyotprakash.postscomments.di.services

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pradyotprakash.postscomments.core.services.AuthenticationService
import com.pradyotprakash.postscomments.data.repositories.AuthenticationDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

object FirebaseService {
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
        ): AuthenticationService =
            AuthenticationDataRepository(firebaseAuth)
    }
}