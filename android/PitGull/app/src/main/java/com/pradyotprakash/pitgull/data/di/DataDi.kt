package com.pradyotprakash.pitgull.data.di

import com.pradyotprakash.pitgull.data.services.PullRequestService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataDi {
    @Singleton
    @Provides
    fun provideBasicService(retrofit: Retrofit): PullRequestService =
        retrofit.create(PullRequestService::class.java)
}