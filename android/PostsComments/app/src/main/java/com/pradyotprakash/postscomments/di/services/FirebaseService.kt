package com.pradyotprakash.postscomments.di.services

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.pradyotprakash.postscomments.core.services.AuthenticationService
import com.pradyotprakash.postscomments.core.services.PostService
import com.pradyotprakash.postscomments.core.services.UserService
import com.pradyotprakash.postscomments.data.repositories.AuthenticationDataRepository
import com.pradyotprakash.postscomments.data.repositories.PostDataRepository
import com.pradyotprakash.postscomments.data.repositories.UserDataRepository
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

    @Module
    @InstallIn(SingletonComponent::class)
    object Firestore {
        @Singleton
        @Provides
        fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

        @Singleton
        @Provides
        fun providePostService(
            firestore: FirebaseFirestore,
        ): PostService =
            PostDataRepository(firestore)

        @Singleton
        @Provides
        fun provideUserService(
            firestore: FirebaseFirestore,
        ): UserService =
            UserDataRepository(firestore)
    }
}