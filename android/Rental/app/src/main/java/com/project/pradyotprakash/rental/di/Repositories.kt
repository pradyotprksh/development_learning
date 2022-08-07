package com.project.pradyotprakash.rental.di

import com.project.pradyotprakash.rental.domain.repositories.BasicRepository
import com.project.pradyotprakash.rental.domain.usecase.BasicUseCase
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
    fun providesRepository(basicUseCase: BasicUseCase) = BasicRepository(basicUseCase)
}