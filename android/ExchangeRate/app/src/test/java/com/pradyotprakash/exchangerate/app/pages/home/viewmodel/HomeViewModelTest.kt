package com.pradyotprakash.exchangerate.app.pages.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pradyotprakash.exchangerate.core.models.LiveResponse
import com.pradyotprakash.exchangerate.core.toast.Toaster
import com.pradyotprakash.exchangerate.domain.usecases.ExchangeUsecase
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    private val exchangeUsecase: ExchangeUsecase = mockk()
    private val toaster: Toaster = mockk()

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(exchangeUsecase = exchangeUsecase, toaster = toaster)
    }

    @Test
    fun `check if all the initial values are set correctly`() {
        assert(homeViewModel.loading.value == false)
        assert(homeViewModel.error.value == "")
        assert(homeViewModel.amountText.value == "")
        assert(homeViewModel.currencyMenuVisible.value == false)
        assert(homeViewModel.allCurrencies.value?.isEmpty() == true)
        assert(homeViewModel.exchangeCurrencies.value?.isEmpty() == true)
        assert(homeViewModel.selectedCurrency.value == "AED")
    }

    @Test
    fun `updating currency menu visibility to correct value`() {
        assert(homeViewModel.currencyMenuVisible.value == false)
        homeViewModel.updateMenuVisibility()
        assert(homeViewModel.currencyMenuVisible.value == true)
        homeViewModel.updateMenuVisibility()
        assert(homeViewModel.currencyMenuVisible.value == false)
    }

    @Test
    fun `when update amount is called then correct value should be updated`() {
        homeViewModel.updateExchangeRates(liveResponse())

        homeViewModel.updateSelectedCurrency("INR")
        assert(homeViewModel.selectedCurrency.value == "INR")
        assert(homeViewModel.amountText.value == "")
        assert(
            homeViewModel.exchangeCurrencies.value == mapOf(
                "USD" to 12.0,
                "JPN" to 111.0,
                "AED" to 0.6
            )
        )

        homeViewModel.updateAmountValue("2")
        assert(homeViewModel.amountText.value == "2")
        assert(
            homeViewModel.exchangeCurrencies.value == mapOf(
                "USD" to 24.0,
                "JPN" to 222.0,
                "AED" to 1.2
            )
        )

        homeViewModel.updateSelectedCurrency("USD")
        assert(homeViewModel.selectedCurrency.value == "USD")
        assert(homeViewModel.amountText.value == "")
        assert(
            homeViewModel.exchangeCurrencies.value == mapOf(
                "USD" to 12.0,
                "JPN" to 111.0,
                "AED" to 0.6
            )
        )
    }

    private fun liveResponse() = LiveResponse(
        success = true,
        timestamp = 123456789,
        source = "INR",
        quotes = mapOf(
            "USD" to 12.0,
            "JPN" to 111.0,
            "AED" to 0.6
        )
    )
}