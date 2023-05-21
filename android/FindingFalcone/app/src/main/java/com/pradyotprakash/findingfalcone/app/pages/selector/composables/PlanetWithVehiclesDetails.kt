package com.pradyotprakash.findingfalcone.app.pages.selector.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pradyotprakash.findingfalcone.app.utils.Assets
import com.pradyotprakash.findingfalcone.domain.entity.PlanetsEntity
import com.pradyotprakash.findingfalcone.domain.entity.Vehicles

@Composable
fun PlanetWithVehiclesDetails(
    planet: PlanetsEntity,
    vehicles: Vehicles,
    planetIcon: Assets,
    onVehicleSelect: (String) -> Unit,
    onPlanetSelect: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            PlanetDetails(planet = planet, planetIcon = planetIcon) {
                onPlanetSelect()
            }
            if (planet.selected) {
                VehiclesList(planet = planet, vehicles = vehicles) {
                    onVehicleSelect(it)
                }
            }
        }
    }
}