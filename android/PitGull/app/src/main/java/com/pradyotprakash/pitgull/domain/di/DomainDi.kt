package com.pradyotprakash.pitgull.domain.di

import com.pradyotprakash.pitgull.data.services.PullRequestService
import com.pradyotprakash.pitgull.domain.repositories.PullRequestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainDi {
    @Singleton
    @Provides
    fun providesBasicRepository(
        pullRequestService: PullRequestService
    ) = PullRequestRepository(pullRequestService = pullRequestService)
}