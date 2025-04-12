package com.pradyotprakash.unitconverter.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pradyotprakash.unitconverter.app.composables.UnitConverterComposable
import com.pradyotprakash.unitconverter.core.models.request.Units
import com.pradyotprakash.unitconverter.utils.Localization
import kotlinx.coroutines.launch

@Composable
fun UnitConverterApp(
    unitConverterViewModel: UnitConverterViewModel = viewModel { UnitConverterViewModel() },
) {
    val unitConverterStateState by unitConverterViewModel.unitConverterStateState.collectAsState()
    val pagerState = rememberPagerState(
        pageCount = {
            unitConverterStateState.tabs.size
        },
    )
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        Localization.APP_NAME,
                    )
                },
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            ) {
                unitConverterStateState.tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(text = tab.humanReadable)
                        }
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
            ) { index ->
                val unitType = when (index) {
                    0 -> Units.LENGTH
                    1 -> Units.WEIGHT
                    2 -> Units.TEMPERATURE
                    else -> Units.LENGTH
                }

                UnitConverterComposable(
                    unitType = unitType,
                    value = unitConverterStateState.value,
                    lengthFromSelection = unitConverterStateState.lengthFromSelection,
                    lengthToSelection = unitConverterStateState.lengthToSelection,
                    weightFromSelection = unitConverterStateState.weightFromSelection,
                    weightToSelection = unitConverterStateState.weightToSelection,
                    temperatureFromSelection = unitConverterStateState.temperatureFromSelection,
                    temperatureToSelection = unitConverterStateState.temperatureToSelection,
                    fromExpanded = unitConverterStateState.fromExpanded,
                    toExpanded = unitConverterStateState.toExpanded,
                    onFromTapped = unitConverterViewModel::onFromTapped,
                    onToTapped = unitConverterViewModel::onToTapped,
                    onLengthFromSelection = unitConverterViewModel::onLengthFromSelection,
                    onWeightFromSelection = unitConverterViewModel::onWeightFromSelection,
                    onTemperatureFromSelection = unitConverterViewModel::onTemperatureFromSelection,
                    onLengthToSelection = unitConverterViewModel::onLengthToSelection,
                    onWeightToSelection = unitConverterViewModel::onWeightToSelection,
                    onTemperatureToSelection = unitConverterViewModel::onTemperatureToSelection,
                    convert = {
                        unitConverterViewModel.convert(unitType)
                    },
                )
            }
        }
    }
}