package com.pradyotprakash.unitconverter.app.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pradyotprakash.unitconverter.core.models.request.LengthTypes
import com.pradyotprakash.unitconverter.core.models.request.TemperatureTypes
import com.pradyotprakash.unitconverter.core.models.request.Units
import com.pradyotprakash.unitconverter.core.models.request.WeightTypes
import com.pradyotprakash.unitconverter.utils.Localization

@Composable
fun UnitSelectorComposable(
    isFrom: Boolean,
    unitType: Units,
    lengthSelection: LengthTypes,
    weightSelection: WeightTypes,
    temperatureSelection: TemperatureTypes,
    fromExpanded: Boolean,
    toExpanded: Boolean,
    onFromTapped: () -> Unit,
    onToTapped: () -> Unit,
    onLengthSelection: (LengthTypes) -> Unit,
    onWeightSelection: (WeightTypes) -> Unit,
    onTemperatureSelection: (TemperatureTypes) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        OutlinedTextField(
            value = when (unitType) {
                Units.LENGTH -> lengthSelection.abbreviation
                Units.WEIGHT -> weightSelection.abbreviation
                Units.TEMPERATURE -> temperatureSelection.abbreviation
            },
            onValueChange = {},
            label = {
                Text(
                    if (isFrom)
                        Localization.UNIT_TO_CONVERT_FROM
                    else
                        Localization.UNIT_TO_CONVERT_TO
                )
            },
            enabled = false,
            modifier = Modifier.fillMaxWidth().clickable {
                if (isFrom) {
                    onFromTapped()
                } else {
                    onToTapped()
                }
            },
        )
        DropdownMenu(
            expanded = if (isFrom) fromExpanded else toExpanded,
            onDismissRequest = {
                if (isFrom) {
                    onFromTapped()
                } else {
                    onToTapped()
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            when (unitType) {
                Units.LENGTH -> LengthTypes.entries.forEach {
                    DropdownMenuItem(
                        onClick = {
                            onLengthSelection(it)
                        },
                    ) {
                        Text(it.abbreviation)
                    }
                }

                Units.WEIGHT -> WeightTypes.entries.forEach {
                    DropdownMenuItem(
                        onClick = {
                            onWeightSelection(it)
                        },
                    ) {
                        Text(it.abbreviation)
                    }
                }

                Units.TEMPERATURE -> TemperatureTypes.entries.forEach {
                    DropdownMenuItem(
                        onClick = {
                            onTemperatureSelection(it)
                        },
                    ) {
                        Text(it.abbreviation)
                    }
                }
            }
        }
    }
}