package com.project.pradyotprakash.rental.app.pages.property.details.view.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.rental.app.localization.TR

@Composable
fun OtherDetailsComposable(
    perks: String,
    agreement: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            if (perks.isNotEmpty()) {
                Text(
                    TR.propertyPerks,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Black
                    ),
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    perks
                )
                if (agreement.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            if (agreement.isNotEmpty()) {
                if (perks.isNotEmpty()) {
                    Divider()
                    Spacer(modifier = Modifier.height(10.dp))
                }

                Text(
                    TR.propertyAgreements,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Black
                    ),
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    agreement
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}