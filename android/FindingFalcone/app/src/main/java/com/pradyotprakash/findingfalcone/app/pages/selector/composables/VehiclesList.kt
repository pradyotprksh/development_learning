package com.pradyotprakash.findingfalcone.app.pages.selector.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pradyotprakash.findingfalcone.app.utils.getSpaceship
import com.pradyotprakash.findingfalcone.domain.entity.PlanetsEntity
import com.pradyotprakash.findingfalcone.domain.entity.Vehicles

@Composable
fun VehiclesList(
    planet: PlanetsEntity,
    vehicles: Vehicles,
    onVehicleSelect: (String) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(50.dp))
        vehicles.forEachIndexed { index, vehicle ->
            AnimatedVisibility(visible = vehicle.max_distance >= planet.distance) {
                val vehicleIcon = getSpaceship(index)
                VehicleDetails(
                    planet = planet,
                    vehicle = vehicle,
                    vehicleIcon = vehicleIcon,
                ) {
                    onVehicleSelect(it)
                }
            }
        }
        Spacer(modifier = Modifier.width(50.dp))
    }
}