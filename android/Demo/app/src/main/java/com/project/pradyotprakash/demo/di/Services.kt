package com.project.pradyotprakash.demo.di

import com.project.pradyotprakash.demo.data.services.BasicService
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
}