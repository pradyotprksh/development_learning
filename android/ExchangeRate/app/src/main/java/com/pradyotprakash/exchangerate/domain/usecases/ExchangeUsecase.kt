package com.pradyotprakash.exchangerate.domain.usecases

import com.pradyotprakash.exchangerate.core.models.AllCurrenciesResponse
import com.pradyotprakash.exchangerate.core.models.LiveResponse
import com.pradyotprakash.exchangerate.core.response.ExchangeRateResponse
import com.pradyotprakash.exchangerate.domain.repositories.ExchangeRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ExchangeUsecase @Inject constructor(
    private val exchangeRepository: ExchangeRepository,
) {
    suspend fun getAllCurrencies() = flow {
        emit(ExchangeRateResponse.Loading)
        emit(exchangeRepository.getAllCurrencies())
        emit(ExchangeRateResponse.Idle)
    }

    suspend fun getLiveValues(base: String) = flow {
        emit(ExchangeRateResponse.Loading)
        emit(exchangeRepository.getLiveValues(base))
        emit(ExchangeRateResponse.Idle)
    }

    fun updateLocalExchangeRates(base: String, data: LiveResponse) {
        exchangeRepository.updateLocalExchangeRates(base, data)
    }

    fun updateLocalAllCurrencies(data: AllCurrenciesResponse) {
        exchangeRepository.updateLocalAllCurrencies(data)
    }
}