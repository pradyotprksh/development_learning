package com.pradyotprakash.unitconverter.app.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.pradyotprakash.unitconverter.core.models.request.LengthTypes
import com.pradyotprakash.unitconverter.core.models.request.TemperatureTypes
import com.pradyotprakash.unitconverter.core.models.request.Units
import com.pradyotprakash.unitconverter.core.models.request.WeightTypes
import com.pradyotprakash.unitconverter.utils.Localization

@Composable
fun UnitConverterComposable(
    unitType: Units,
    value: String,
    lengthFromSelection: LengthTypes,
    lengthToSelection: LengthTypes,
    weightFromSelection: WeightTypes,
    weightToSelection: WeightTypes,
    temperatureFromSelection: TemperatureTypes,
    temperatureToSelection: TemperatureTypes,
    errorMessage: String?,
    fromExpanded: Boolean,
    toExpanded: Boolean,
    loading: Boolean,
    onValueChange: (String) -> Unit,
    onFromTapped: () -> Unit,
    onToTapped: () -> Unit,
    onLengthFromSelection: (LengthTypes) -> Unit,
    onWeightFromSelection: (WeightTypes) -> Unit,
    onTemperatureFromSelection: (TemperatureTypes) -> Unit,
    onLengthToSelection: (LengthTypes) -> Unit,
    onWeightToSelection: (WeightTypes) -> Unit,
    onTemperatureToSelection: (TemperatureTypes) -> Unit,
    convert: () -> Unit,
) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
    ) {
        item {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                label = {
                    Text(
                        Localization.format(
                            Localization.ENTER_THE_VALUE,
                            unitType.humanReadable.lowercase(),
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                ),
                modifier = Modifier.fillMaxWidth(),
            )
        }
        item {
            Box(modifier = Modifier.height(10.dp))
        }
        item {
            UnitSelectorComposable(
                isFrom = true,
                unitType = unitType,
                lengthSelection = lengthFromSelection,
                weightSelection = weightFromSelection,
                temperatureSelection = temperatureFromSelection,
                fromExpanded = fromExpanded,
                toExpanded = toExpanded,
                onFromTapped = onFromTapped,
                onToTapped = onToTapped,
                onLengthSelection = onLengthFromSelection,
                onWeightSelection = onWeightFromSelection,
                onTemperatureSelection = onTemperatureFromSelection,
            )
        }
        item {
            Box(modifier = Modifier.height(10.dp))
        }
        item {
            UnitSelectorComposable(
                isFrom = false,
                unitType = unitType,
                lengthSelection = lengthToSelection,
                weightSelection = weightToSelection,
                temperatureSelection = temperatureToSelection,
                fromExpanded = fromExpanded,
                toExpanded = toExpanded,
                onFromTapped = onFromTapped,
                onToTapped = onToTapped,
                onLengthSelection = onLengthToSelection,
                onWeightSelection = onWeightToSelection,
                onTemperatureSelection = onTemperatureToSelection,
            )
        }
        item {
            Box(modifier = Modifier.height(10.dp))
        }
        item {
            if (loading) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = convert,
                ) {
                    Text(Localization.CONVERT)
                }
            }
        }
        item {
            Box(modifier = Modifier.height(10.dp))
            errorMessage?.let {
                Text(
                    it,
                    color = MaterialTheme.colors.error,
                )
            }
        }
    }
}
