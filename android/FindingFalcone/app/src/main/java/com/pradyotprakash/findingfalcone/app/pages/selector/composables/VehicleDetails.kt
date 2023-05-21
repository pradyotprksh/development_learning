package com.pradyotprakash.findingfalcone.app.pages.selector.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pradyotprakash.findingfalcone.app.localization.TR
import com.pradyotprakash.findingfalcone.app.utils.Assets
import com.pradyotprakash.findingfalcone.domain.entity.PlanetsEntity
import com.pradyotprakash.findingfalcone.domain.entity.VehiclesEntity

@Composable
fun VehicleDetails(
    planet: PlanetsEntity,
    vehicle: VehiclesEntity,
    vehicleIcon: Assets,
    onVehicleSelect: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .border(
                width = 1.dp,
                color = if (planet.vehicleDetails == vehicle) Color.Green else MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(10)
            )
            .clickable {
                if (vehicle.total_no != 0L) {
                    onVehicleSelect(vehicle.name)
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = vehicleIcon.resourceId),
            contentDescription = vehicleIcon.imageDescription,
            modifier = Modifier.size(50.dp)
        )
        Box(modifier = Modifier.height(10.dp))
        Text(text = "${vehicle.name} (${vehicle.total_no})")
        Box(modifier = Modifier.height(10.dp))
        Text(text = "${TR.speed} ${vehicle.speed} | ${TR.distance} ${vehicle.max_distance}")
    }
}