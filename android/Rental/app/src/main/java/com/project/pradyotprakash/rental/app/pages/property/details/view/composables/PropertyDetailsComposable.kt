package com.project.pradyotprakash.rental.app.pages.property.details.view.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity

@Composable
fun PropertyDetailsComposable(property: PropertyEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = property.property_name,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = String.format(
                    TR.addressColon,
                    property.address.display_name
                )
            )
            property.property_created_by_details?.let { userDetails ->
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = String.format(
                        TR.createdByColon,
                        userDetails.fullName
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = String.format(
                    TR.createdOnColon,
                    property.createdOnTimeAgo
                )
            )
            if (property.isUpdated) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = String.format(
                        TR.updatedOnColon,
                        property.updatedOnTimeAgo
                    )
                )
            }
        }
    }
}