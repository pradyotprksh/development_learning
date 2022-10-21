package com.project.pradyotprakash.rental.di

import com.project.pradyotprakash.rental.core.services.CrashlyticsService
import com.project.pradyotprakash.rental.core.services.FirebaseAuthenticationService
import com.project.pradyotprakash.rental.data.services.AuthenticationService
import com.project.pradyotprakash.rental.data.services.BasicService
import com.project.pradyotprakash.rental.data.services.PropertyService
import com.project.pradyotprakash.rental.data.services.SearchService
import com.project.pradyotprakash.rental.domain.repositories.AuthenticationRepository
import com.project.pradyotprakash.rental.domain.repositories.BasicRepository
import com.project.pradyotprakash.rental.domain.repositories.PropertyRepository
import com.project.pradyotprakash.rental.domain.repositories.SearchRepository
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
    fun providesBasicRepository(
        basicService: BasicService,
        crashlyticsService: CrashlyticsService
    ) = BasicRepository(basicService, crashlyticsService)

    @Singleton
    @Provides
    fun providesAuthenticationRepository(
        firebaseAuthenticationService: FirebaseAuthenticationService,
        authenticationService: AuthenticationService,
        crashlyticsService: CrashlyticsService,
    ) = AuthenticationRepository(
        firebaseAuthenticationService,
        authenticationService,
        crashlyticsService
    )

    @Singleton
    @Provides
    fun providesPropertyRepository(
        propertyService: PropertyService,
        crashlyticsService: CrashlyticsService
    ) = PropertyRepository(propertyService, crashlyticsService)

    @Singleton
    @Provides
    fun providesSearchRepository(
        searchService: SearchService,
        crashlyticsService: CrashlyticsService
    ) = SearchRepository(searchService, crashlyticsService)
}