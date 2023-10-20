package com.pradyotprakash.libraryowner.di

import com.pradyotprakash.libraryowner.data.services.UnsplashService
import com.pradyotprakash.libraryowner.di.utils.UnsplashRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Services {
    @Singleton
    @Provides
    fun provideUnsplashService(@UnsplashRetrofit retrofit: Retrofit): UnsplashService =
        retrofit.create(UnsplashService::class.java)
}