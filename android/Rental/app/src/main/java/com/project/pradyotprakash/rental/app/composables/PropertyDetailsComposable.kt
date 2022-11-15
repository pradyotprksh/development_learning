package com.project.pradyotprakash.rental.app.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity

@Composable
fun PropertyDetailsComposable(
    property: PropertyEntity,
    onClick: ((String) -> Unit)? = null,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                onClick?.let {
                    it(property.property_id)
                }
            }
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row {
                Text(
                    text = property.property_name,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = TR.isForRental,
                    color = if (property.isForRental) Color.Green else Color.Red
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = String.format(TR.addressColon, property.address?.display_name ?: ""))
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = String.format(
                    TR.propertyForColon,
                    property.propertyFor
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = property.furnishedType)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = String.format(
                    TR.bathrooms,
                    "${property.property_type} & ${property.number_of_bathrooms}"
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            property.distance?.let {
                Text(text = String.format(TR.kmsAway, property.distance))
            }
            property.property_images?.let { images ->
                if (images.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyRow {
                        items(images) { images ->
                            NetworkImageComposable(
                                imageUrl = images,
                                size = 50.dp,
                                cornerSize = 5.dp
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                    }
                }
            }
        }
    }
}