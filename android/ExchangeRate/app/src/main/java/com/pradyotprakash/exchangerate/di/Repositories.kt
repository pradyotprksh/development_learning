package com.pradyotprakash.exchangerate.di

import com.pradyotprakash.exchangerate.data.services.ExchangeService
import com.pradyotprakash.exchangerate.device.dao.AllCurrenciesDao
import com.pradyotprakash.exchangerate.device.dao.ExchangeRatesDao
import com.pradyotprakash.exchangerate.domain.repositories.ExchangeRepository
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
    fun providesExchangeRepository(
        exchangeService: ExchangeService,
        exchangeRatesDao: ExchangeRatesDao,
        allCurrenciesDao: AllCurrenciesDao,
    ) = ExchangeRepository(
        exchangeService,
        exchangeRatesDao,
        allCurrenciesDao,
    )
}