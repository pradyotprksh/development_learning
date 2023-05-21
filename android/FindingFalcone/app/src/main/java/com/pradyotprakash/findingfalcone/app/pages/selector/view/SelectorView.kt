package com.pradyotprakash.findingfalcone.app.pages.selector.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.findingfalcone.app.composables.PageStateComposable
import com.pradyotprakash.findingfalcone.app.localization.TR
import com.pradyotprakash.findingfalcone.app.pages.selector.composables.PlanetWithVehiclesDetails
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
    val planets by selectorViewModel.planets.observeAsState(emptyList())
    val vehicles by selectorViewModel.vehicles.observeAsState(emptyList())
    val isArmySelected by selectorViewModel.isArmySelected.observeAsState(false)
    val totalTime by selectorViewModel.totalTime.observeAsState(0)
    val findResult by selectorViewModel.findResult.observeAsState()

    PageStateComposable(
        isLoading = loading,
        errorMessage = error,
        retryOperation = selectorViewModel::retry,
        dismissErrorAlert = selectorViewModel::updateErrorState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = TR.selectPlanetsVehicles,
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = Color.Black,
                            ),
                        )
                    },
                    actions = {
                        Text(
                            text = "${TR.timeTaken} $totalTime",
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = Color.Black,
                            ),
                        )
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
            },
            floatingActionButton = {
                if (isArmySelected) {
                    FloatingActionButton(
                        onClick = {
                            selectorViewModel.sendTheArmy()
                        }
                    ) {
                        Text(
                            text = TR.sendArmy,
                            modifier = Modifier.padding(
                                all = 15.dp
                            )
                        )
                    }
                }
            },
            content = { padding ->
                findResult?.let {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        if (it.isFound) {
                            Text(text = TR.successMessage)
                        } else {
                            Text(text = TR.errorMessage)
                        }
                        Box(modifier = Modifier.height(10.dp))
                        Text(
                            text = "${TR.timeTaken} $totalTime",
                        )
                        if (it.isFound) {
                            Text(
                                text = "${TR.planetFound} ${it.planet_name}",
                            )
                        }
                        Box(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = {
                                selectorViewModel.retry()
                            }
                        ) {
                            Text(text = TR.startAgain)
                        }
                    }
                }

                if (planets.isNotEmpty() && vehicles.isNotEmpty() && findResult == null) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(
                                bottom = padding.calculateBottomPadding(),
                                top = padding.calculateTopPadding(),
                            )
                            .fillMaxSize(),
                    ) {
                        items(planets.size) { index ->
                            val planetIcon = getPlanet(selectorViewModel.randomList[index])
                            val planet = planets[index]
                            PlanetWithVehiclesDetails(
                                planet = planet,
                                planetIcon = planetIcon,
                                vehicles = vehicles,
                                onVehicleSelect = { vehicleName ->
                                    selectorViewModel.selectVehicle(index, vehicleName)
                                },
                                onPlanetSelect = {
                                    selectorViewModel.selectPlanet(index)
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}