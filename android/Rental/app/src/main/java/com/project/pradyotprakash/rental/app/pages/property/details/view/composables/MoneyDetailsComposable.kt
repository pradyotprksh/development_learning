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
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity

@Composable
fun MoneyDetailsComposable(property: PropertyEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                TR.deposit,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Black
                ),
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = property.yearly_deposit)
            Spacer(modifier = Modifier.height(10.dp))
            Divider()
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                TR.rent,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Black
                ),
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = property.monthly_rent)
        }
    }
}