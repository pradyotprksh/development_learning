package com.project.pradyotprakash.rental.di

import com.project.pradyotprakash.rental.domain.services.BasicService
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
    fun provideApiService(retrofit: Retrofit): BasicService =
        retrofit.create(BasicService::class.java)
}