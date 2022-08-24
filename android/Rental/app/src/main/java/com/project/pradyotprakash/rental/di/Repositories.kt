package com.project.pradyotprakash.rental.di

import com.project.pradyotprakash.rental.domain.repositories.AuthenticationRepository
import com.project.pradyotprakash.rental.domain.repositories.BasicRepository
import com.project.pradyotprakash.rental.domain.services.AuthenticationService
import com.project.pradyotprakash.rental.domain.services.BasicService
import com.project.pradyotprakash.rental.domain.services.FirebaseAuthenticationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Inject all the repositories required.
 */
@Module
@InstallIn(SingletonComponent::class)
object Repositories {
    @Singleton
    @Provides
    fun providesBasicRepository(basicService: BasicService) = BasicRepository(basicService)

    @Singleton
    @Provides
    fun providesAuthenticationRepository(
        firebaseAuthenticationService: FirebaseAuthenticationService,
        authenticationService: AuthenticationService
    ) = AuthenticationRepository(firebaseAuthenticationService, authenticationService)
}