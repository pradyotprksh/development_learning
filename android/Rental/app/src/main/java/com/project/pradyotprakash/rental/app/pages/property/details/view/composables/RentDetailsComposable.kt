package com.project.pradyotprakash.rental.app.pages.property.details.view.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity

@Composable
fun RentDetailsComposable(property: PropertyEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = TR.isForRental,
                color = if (property.isForRental) Color.Green else Color.Red
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = String.format(TR.propertyForColon, property.propertyFor))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = property.furnishedType)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = String.format(TR.bathrooms, "${property.property_type} & ${property.number_of_bathrooms}"))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "${TR.wherePropertyIs} ${property.propertyLocation}")
        }
    }
}