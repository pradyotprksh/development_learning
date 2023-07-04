package com.pradyotprakash.notes.domain.di

import com.pradyotprakash.notes.device.dao.UserDao
import com.pradyotprakash.notes.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Singleton
    @Provides
    fun provideUserRepository(
        userDao: UserDao,
    ) = UserRepository(
        userDao = userDao,
    )
}