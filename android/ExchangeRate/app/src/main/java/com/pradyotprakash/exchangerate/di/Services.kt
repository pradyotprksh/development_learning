package com.pradyotprakash.exchangerate.di

import com.pradyotprakash.exchangerate.data.services.ExchangeService
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
    fun provideExchangeService(retrofit: Retrofit): ExchangeService =
        retrofit.create(ExchangeService::class.java)
}