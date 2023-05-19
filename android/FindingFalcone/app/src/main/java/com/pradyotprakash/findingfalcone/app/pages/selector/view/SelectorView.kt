package com.pradyotprakash.findingfalcone.app.pages.selector.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.findingfalcone.app.composables.ConfirmationDialog
import com.pradyotprakash.findingfalcone.app.composables.HeaderComposable
import com.pradyotprakash.findingfalcone.app.composables.PageStateComposable
import com.pradyotprakash.findingfalcone.app.localization.TR
import com.pradyotprakash.findingfalcone.app.pages.selector.composables.PlanetDetails
import com.pradyotprakash.findingfalcone.app.pages.selector.viewmodel.SelectorViewModel
import com.pradyotprakash.findingfalcone.app.utils.getPlanet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorView(
    selectorViewModel: SelectorViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        selectorViewModel.getDetails()
    }

    val loading by selectorViewModel.loading.observeAsState(false)
    val error by selectorViewModel.error.observeAsState("")
    val confirmationDialog by selectorViewModel.confirmationDialog.observeAsState(
        ConfirmationDialog()
    )
    val planets by selectorViewModel.planets.observeAsState(emptyList())
    val vehicles by selectorViewModel.vehicles.observeAsState(emptyList())
    val planetsSelected by selectorViewModel.planetsSelected.observeAsState(false)

    PageStateComposable(
        isLoading = loading,
        errorMessage = error,
        retryOperation = selectorViewModel::retry,
        dismissErrorAlert = selectorViewModel::updateErrorState,
        confirmationDialog = confirmationDialog,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = TR.appName)
                    }
                )
            },
            floatingActionButton = {
                if (planetsSelected) {
                    FloatingActionButton(
                        onClick = {}
                    ) {
                        Text(
                            text = TR.selectShips,
                            modifier = Modifier.padding(
                                all = 15.dp
                            )
                        )
                    }
                }
            },
            content = { padding ->
                Column(
                    modifier = Modifier.padding(
                        bottom = padding.calculateBottomPadding(),
                        top = padding.calculateTopPadding(),
                    ),
                ) {
                    if (planets.isNotEmpty()) {
                        HeaderComposable(title = TR.selectPlanets)
                        LazyVerticalGrid(
                            modifier = Modifier
                                .padding(
                                    start = 15.dp,
                                    bottom = 15.dp,
                                    top = 15.dp,
                                    end = 15.dp,
                                )
                                .fillMaxSize(),
                            columns = GridCells.Fixed(2),
                        ) {
                            items(planets.size) { index ->
                                val planetIcon = getPlanet(selectorViewModel.randomList[index])
                                val planet = planets[index]
                                PlanetDetails(planet = planet, planetIcon = planetIcon) {
                                    selectorViewModel.selectPlanet(index)
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}