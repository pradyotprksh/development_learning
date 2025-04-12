package com.pradyotprakash.unitconverter.app

import com.pradyotprakash.unitconverter.core.models.request.LengthTypes
import com.pradyotprakash.unitconverter.core.models.request.TemperatureTypes
import com.pradyotprakash.unitconverter.core.models.request.Units
import com.pradyotprakash.unitconverter.core.models.request.WeightTypes

data class UnitConverterState(
    val tabs: List<Units> = Units.entries,
    val value: String = "",
    val fromExpanded: Boolean = false,
    val toExpanded: Boolean = false,
    val lengthFromSelection: LengthTypes = LengthTypes.MILIMETER,
    val lengthToSelection: LengthTypes = LengthTypes.MILIMETER,
    val weightFromSelection: WeightTypes = WeightTypes.GRAM,
    val weightToSelection: WeightTypes = WeightTypes.GRAM,
    val temperatureFromSelection: TemperatureTypes = TemperatureTypes.KELVIN,
    val temperatureToSelection: TemperatureTypes = TemperatureTypes.KELVIN,
    val result: String? = null,
    val errorMessage: String? = null,
    val showLoading: Boolean = false,
)