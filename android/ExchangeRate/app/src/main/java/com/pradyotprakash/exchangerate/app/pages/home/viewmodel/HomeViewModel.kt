package com.pradyotprakash.exchangerate.app.pages.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.exchangerate.core.models.LiveResponse
import com.pradyotprakash.exchangerate.core.response.ExchangeRateResponse
import com.pradyotprakash.exchangerate.core.toast.Toaster
import com.pradyotprakash.exchangerate.domain.usecases.ExchangeUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val exchangeUsecase: ExchangeUsecase,
    private val toaster: Toaster,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _amountText = MutableLiveData("")
    val amountText: LiveData<String>
        get() = _amountText
    private val _currencyMenuVisible = MutableLiveData(false)
    val currencyMenuVisible: LiveData<Boolean>
        get() = _currencyMenuVisible
    private val _allCurrencies = MutableLiveData(emptyMap<String, String>())
    val allCurrencies: LiveData<Map<String, String>>
        get() = _allCurrencies
    private val _selectedCurrency = MutableLiveData("AED")
    val selectedCurrency: LiveData<String>
        get() = _selectedCurrency
    private val _responseExchangeCurrencies = MutableLiveData(emptyMap<String, Double>())
    private val _exchangeCurrencies = MutableLiveData(emptyMap<String, Double>())
    val exchangeCurrencies: LiveData<Map<String, Double>>
        get() = _exchangeCurrencies

    init {
        getAllCurrencies()
    }

    private fun getAllCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            exchangeUsecase.getAllCurrencies().collect { response ->
                when (response) {
                    is ExchangeRateResponse.Error -> updateErrorState(response.exception.message)
                    is ExchangeRateResponse.Idle -> {}
                    is ExchangeRateResponse.Loading -> withContext(Dispatchers.Main) {
                        _loading.value = true
                    }

                    is ExchangeRateResponse.Success -> {
                        withContext(Dispatchers.Main) {
                            _allCurrencies.value = response.data.currencies
                            _selectedCurrency.value = response.data.currencies?.keys?.first()
                        }
                        exchangeUsecase.updateLocalAllCurrencies(response.data)
                        getLiveValue()
                    }
                }
            }
        }
    }

    private fun getLiveValue() {
        _selectedCurrency.value?.let { base ->
            viewModelScope.launch(Dispatchers.IO) {
                exchangeUsecase.getLiveValues(base).collect { response ->
                    when (response) {
                        is ExchangeRateResponse.Error -> updateErrorState(response.exception.message)
                        is ExchangeRateResponse.Idle -> withContext(Dispatchers.Main) {
                            _loading.value = false
                        }

                        is ExchangeRateResponse.Loading -> withContext(Dispatchers.Main) {
                            _loading.value = true
                        }

                        is ExchangeRateResponse.Success -> {
                            withContext(Dispatchers.Main) {
                                updateExchangeRates(response.data)
                            }
                            exchangeUsecase.updateLocalExchangeRates(base, response.data)
                        }
                    }
                }
            }
        }
    }

    fun updateExchangeRates(data: LiveResponse) {
        _responseExchangeCurrencies.value = data.quotes
        _exchangeCurrencies.value = data.quotes
    }

    fun updateErrorState(message: String? = "") {
        viewModelScope.launch {
            _loading.value = false
            _errorText.value = message
            _responseExchangeCurrencies.value = emptyMap()
            _exchangeCurrencies.value = emptyMap()
        }
    }

    fun updateAmountValue(value: String) {
        if (value.trim().isNotBlank()) {
            value.toDoubleOrNull()?.let { updateTheExchangeValue(it) }
        } else {
            _responseExchangeCurrencies.value?.let { _exchangeCurrencies.value = it }
        }
        _amountText.value = value
    }

    private fun updateTheExchangeValue(amount: Double) {
        if (amount <= 0) return
        _responseExchangeCurrencies.value?.let { rates ->
            val newRates = mutableMapOf<String, Double>()
            rates.forEach { (k, v) ->
                val newRate = v * amount
                if (newRate > Double.MAX_VALUE) {
                    newRates[k] = v
                } else {
                    newRates[k] = v * amount
                }
            }
            _exchangeCurrencies.value = newRates
        }
    }

    fun updateMenuVisibility() {
        _currencyMenuVisible.value = !(_currencyMenuVisible.value ?: false)
    }

    fun updateSelectedCurrency(currency: String) {
        _selectedCurrency.value = currency
        updateAmountValue("")
        getLiveValue()
    }

    fun showCountryName(countryCode: String) {
        toaster.toast(countryCode)
    }
}