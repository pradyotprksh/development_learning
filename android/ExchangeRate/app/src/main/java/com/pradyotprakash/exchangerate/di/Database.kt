package com.pradyotprakash.exchangerate.di

import android.content.Context
import androidx.room.Room
import com.pradyotprakash.exchangerate.device.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Database {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "exchange"
    ).build()

    @Singleton
    @Provides
    fun provideExchangeRatesDatabase(
        appDatabase: AppDatabase,
    ) = appDatabase.exchangeRatesDao()

    @Singleton
    @Provides
    fun provideAllCurrenciesDatabase(
        appDatabase: AppDatabase,
    ) = appDatabase.allCurrenciesDao()
}