package com.pradyotprakash.exchangerate.app.pages.home.view.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pradyotprakash.exchangerate.app.utils.ContentDescription.EXCHANGE_RATE
import java.math.RoundingMode

@Composable
fun ExchangeRateComposable(
    showCountryName: (String) -> Unit,
    countryCode: String,
    countryName: String,
    rate: Double,
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                showCountryName("$countryName ($countryCode)")
            }
            .semantics { contentDescription = EXCHANGE_RATE }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = countryCode,
                style = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.colorScheme.primary),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = rate.toBigDecimal().setScale(2, RoundingMode.FLOOR)?.toPlainString() ?: "0",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}