package com.pradyotprakash.libraryowner.di

import com.pradyotprakash.libraryowner.data.repositories.UnsplashRepositoryImplementation
import com.pradyotprakash.libraryowner.data.services.UnsplashService
import com.pradyotprakash.libraryowner.domain.repositories.UnsplashRepository
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
        unsplashService: UnsplashService
    ): UnsplashRepository = UnsplashRepositoryImplementation(unsplashService)
}