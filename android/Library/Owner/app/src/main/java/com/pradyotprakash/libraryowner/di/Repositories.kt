package com.pradyotprakash.libraryowner.di

import com.pradyotprakash.libraryowner.data.repositories.AppConfigRepositoryImplementation
import com.pradyotprakash.libraryowner.data.repositories.AuthenticationRepositoryImplementation
import com.pradyotprakash.libraryowner.data.repositories.IpGeolocationRepositoryImplementation
import com.pradyotprakash.libraryowner.data.repositories.UnsplashRepositoryImplementation
import com.pradyotprakash.libraryowner.data.repositories.UserFirestoreRepositoryImplementation
import com.pradyotprakash.libraryowner.data.services.IpGeolocationService
import com.pradyotprakash.libraryowner.data.services.UnsplashService
import com.pradyotprakash.libraryowner.data.services.appconfig.AppConfigService
import com.pradyotprakash.libraryowner.data.services.auth.FirebaseAuthenticationService
import com.pradyotprakash.libraryowner.data.services.crashlytics.CrashlyticsService
import com.pradyotprakash.libraryowner.data.services.firestore.UserFirestoreService
import com.pradyotprakash.libraryowner.domain.repositories.AppConfigRepository
import com.pradyotprakash.libraryowner.domain.repositories.AuthenticationRepository
import com.pradyotprakash.libraryowner.domain.repositories.IpGeolocationRepository
import com.pradyotprakash.libraryowner.domain.repositories.UnsplashRepository
import com.pradyotprakash.libraryowner.domain.repositories.UserFirestoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Repositories {
    @Singleton
    @Provides
    fun providesUnsplashRepository(
        unsplashService: UnsplashService,
        crashlyticsService: CrashlyticsService,
    ): UnsplashRepository = UnsplashRepositoryImplementation(unsplashService, crashlyticsService)

    @Singleton
    @Provides
    fun providesIpGeolocationRepository(
        ipGeolocationService: IpGeolocationService,
        crashlyticsService: CrashlyticsService,
    ): IpGeolocationRepository =
        IpGeolocationRepositoryImplementation(ipGeolocationService, crashlyticsService)

    @Singleton
    @Provides
    fun providesAuthenticationRepository(
        firebaseAuthenticationService: FirebaseAuthenticationService,
    ): AuthenticationRepository =
        AuthenticationRepositoryImplementation(firebaseAuthenticationService)

    @Singleton
    @Provides
    fun providesAppConfigRepository(
        appConfigService: AppConfigService,
    ): AppConfigRepository = AppConfigRepositoryImplementation(appConfigService)

    @Singleton
    @Provides
    fun providesUserFirestoreRepository(
        userFirestoreService: UserFirestoreService,
    ): UserFirestoreRepository = UserFirestoreRepositoryImplementation(userFirestoreService)
}