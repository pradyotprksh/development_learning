package com.pradyotprakash.exchangerate.app.pages.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.exchangerate.app.localization.TR
import com.pradyotprakash.exchangerate.app.pages.home.view.composables.AmountEntryComposable
import com.pradyotprakash.exchangerate.app.pages.home.view.composables.ExchangeRateComposable
import com.pradyotprakash.exchangerate.app.pages.home.view.composables.PageStateComposable
import com.pradyotprakash.exchangerate.app.pages.home.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val loading = homeViewModel.loading.observeAsState(false)
    val error = homeViewModel.error.observeAsState("")
    val amountText = homeViewModel.amountText.observeAsState("")
    val currencyMenuVisible = homeViewModel.currencyMenuVisible.observeAsState(false)
    val allCurrencies = homeViewModel.allCurrencies.observeAsState(emptyMap())
    val exchangeCurrencies = homeViewModel.exchangeCurrencies.observeAsState(emptyMap()).value
    val selectedCurrency = homeViewModel.selectedCurrency.observeAsState("")

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = homeViewModel::updateErrorState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = TR.appName)
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding(),
                        start = 15.dp,
                        end = 15.dp
                    )
            ) {
                AmountEntryComposable(
                    amountText = amountText.value,
                    selectedCurrency = selectedCurrency.value,
                    currencyMenuVisible = currencyMenuVisible.value,
                    allCurrencies = allCurrencies.value,
                    updateAmountValue = homeViewModel::updateAmountValue,
                    updateMenuVisibility = homeViewModel::updateMenuVisibility,
                    updateSelectedCurrency = homeViewModel::updateSelectedCurrency
                )

                if (!loading.value) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(
                            top = 15.dp,
                            bottom = 15.dp
                        )
                    ) {
                        items(exchangeCurrencies.size) {
                            val country = exchangeCurrencies.keys.toList()[it]
                            val rate = exchangeCurrencies[country]
                            val countryCode = country.replace(selectedCurrency.value, "")
                            val countryName = allCurrencies.value[countryCode]

                            ExchangeRateComposable(
                                showCountryName = homeViewModel::showCountryName,
                                countryCode = countryCode,
                                countryName = countryName ?: "",
                                rate = rate ?: 0.0
                            )
                        }
                    }
                }
            }
        }
    }
}