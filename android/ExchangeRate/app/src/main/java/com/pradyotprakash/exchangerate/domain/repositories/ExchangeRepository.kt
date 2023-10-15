package com.pradyotprakash.exchangerate.domain.repositories

import com.orhanobut.logger.Logger
import com.pradyotprakash.exchangerate.app.localization.TR
import com.pradyotprakash.exchangerate.core.models.AllCurrenciesResponse
import com.pradyotprakash.exchangerate.core.models.LiveResponse
import com.pradyotprakash.exchangerate.core.response.ExchangeRateException
import com.pradyotprakash.exchangerate.core.response.ExchangeRateResponse
import com.pradyotprakash.exchangerate.core.response.parseResponse
import com.pradyotprakash.exchangerate.core.utils.Utils
import com.pradyotprakash.exchangerate.data.services.ExchangeService
import com.pradyotprakash.exchangerate.device.dao.AllCurrenciesDao
import com.pradyotprakash.exchangerate.device.dao.ExchangeRatesDao
import com.pradyotprakash.exchangerate.device.entities.AllCurrencies
import com.pradyotprakash.exchangerate.device.entities.ExchangeRate

class ExchangeRepository(
    private val exchangeService: ExchangeService,
    private val exchangeRatesDao: ExchangeRatesDao,
    private val allCurrenciesDao: AllCurrenciesDao,
) {
    suspend fun getAllCurrencies(): ExchangeRateResponse<out AllCurrenciesResponse> {
        val allCurrencies = allCurrenciesDao.getCurrencies()
        return if (allCurrencies != null) {
            if (allCurrencies.currencies.isNotEmpty()) {
                val time = allCurrencies.time
                val diff =
                    Utils.calculateMinutesBetweenTimestamps(Utils.getCurrentTimestamp(), time)
                if (diff > 30) {
                    getAllCurrenciesFromRemote()
                } else {
                    getLocalAllCurrencies(allCurrencies.currencies)
                }
            } else {
                getAllCurrenciesFromRemote()
            }
        } else {
            getAllCurrenciesFromRemote()
        }
    }

    private suspend fun getAllCurrenciesFromRemote() = try {
        exchangeService.getAllList().parseResponse()
    } catch (e: Exception) {
        Logger.e(e.toString())
        responseFromLocal(e)
    }

    private fun responseFromLocal(e: Exception): ExchangeRateResponse<out AllCurrenciesResponse> {
        val allCurrencies = allCurrenciesDao.getCurrencies()
        return if (allCurrencies != null) {
            if (allCurrencies.currencies.isNotEmpty()) {
                getLocalAllCurrencies(allCurrencies.currencies)
            } else {
                getErrorResponse(e)
            }
        } else {
            getErrorResponse(e)
        }
    }

    private fun getLocalAllCurrencies(allCurrencies: Map<String, String>) =
        ExchangeRateResponse.Success(
            AllCurrenciesResponse(
                success = true,
                currencies = allCurrencies
            )
        )

    suspend fun getLiveValues(base: String): ExchangeRateResponse<out LiveResponse> {
        val rates = exchangeRatesDao.getRates(base)
        return if (rates != null) {
            if (rates.exchangeRates.isNotEmpty()) {
                val time = rates.time
                val diff =
                    Utils.calculateMinutesBetweenTimestamps(Utils.getCurrentTimestamp(), time)
                if (diff > 30) {
                    getLiveValuesFromRemote(base)
                } else {
                    getLocalLiveValues(rates, base)
                }
            } else {
                getLiveValuesFromRemote(base)
            }
        } else {
            getLiveValuesFromRemote(base)
        }
    }

    private suspend fun getLiveValuesFromRemote(base: String) = try {
        exchangeService.getLiveValue(base).parseResponse()
    } catch (e: Exception) {
        Logger.e(e.toString())
        val rates = exchangeRatesDao.getRates(base)
        if (rates != null) {
            if (rates.exchangeRates.isNotEmpty()) {
                getLocalLiveValues(rates, base)
            } else {
                getErrorResponse(e)
            }
        } else {
            getErrorResponse(e)
        }
    }

    private fun getLocalLiveValues(rates: ExchangeRate, base: String) =
        ExchangeRateResponse.Success(
            LiveResponse(
                success = true,
                timestamp = rates.time.toInt(),
                source = base,
                quotes = rates.exchangeRates
            )
        )

    private fun getErrorResponse(e: Exception) = ExchangeRateResponse.Error(
        ExchangeRateException(
            message = e.localizedMessage ?: TR.noDataFoundError
        )
    )

    fun updateLocalExchangeRates(base: String, data: LiveResponse) {
        val rates = exchangeRatesDao.getRates(base)
        if (rates != null) {
            if (rates.exchangeRates.isNotEmpty()) {
                val time = rates.time
                val diff =
                    Utils.calculateMinutesBetweenTimestamps(Utils.getCurrentTimestamp(), time)
                if (diff > 30) {
                    exchangeRatesDao.delete(rates)
                }
            }
        }

        exchangeRatesDao.insert(
            ExchangeRate(
                countryCode = base,
                time = Utils.getCurrentTimestamp(),
                exchangeRates = data.quotes ?: emptyMap()
            )
        )
    }

    fun updateLocalAllCurrencies(data: AllCurrenciesResponse) {
        allCurrenciesDao.insert(
            AllCurrencies(
                time = Utils.getCurrentTimestamp(),
                currencies = data.currencies ?: emptyMap(),
            )
        )
    }
}