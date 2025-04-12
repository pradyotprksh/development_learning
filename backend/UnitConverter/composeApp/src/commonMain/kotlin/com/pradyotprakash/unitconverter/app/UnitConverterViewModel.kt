package com.pradyotprakash.unitconverter.app

import androidx.lifecycle.ViewModel
import com.pradyotprakash.unitconverter.core.models.request.LengthTypes
import com.pradyotprakash.unitconverter.core.models.request.TemperatureTypes
import com.pradyotprakash.unitconverter.core.models.request.Units
import com.pradyotprakash.unitconverter.core.models.request.WeightTypes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UnitConverterViewModel : ViewModel() {
    private val _unitConverterState = MutableStateFlow(UnitConverterState())
    val unitConverterStateState = _unitConverterState.asStateFlow()

    fun onFromTapped() {
        _unitConverterState.value = _unitConverterState.value.copy(
            fromExpanded = !_unitConverterState.value.fromExpanded,
        )
    }

    fun onToTapped() {
        _unitConverterState.value = _unitConverterState.value.copy(
            toExpanded = !_unitConverterState.value.toExpanded,
        )
    }

    fun onLengthFromSelection(lengthTypes: LengthTypes) {
        _unitConverterState.value = _unitConverterState.value.copy(
            lengthFromSelection = lengthTypes,
            fromExpanded = !_unitConverterState.value.fromExpanded,
        )
    }

    fun onWeightFromSelection(weightTypes: WeightTypes) {
        _unitConverterState.value = _unitConverterState.value.copy(
            weightFromSelection = weightTypes,
            fromExpanded = !_unitConverterState.value.fromExpanded,
        )
    }

    fun onTemperatureFromSelection(temperatureTypes: TemperatureTypes) {
        _unitConverterState.value = _unitConverterState.value.copy(
            temperatureFromSelection = temperatureTypes,
            fromExpanded = !_unitConverterState.value.fromExpanded,
        )
    }

    fun onLengthToSelection(lengthTypes: LengthTypes) {
        _unitConverterState.value = _unitConverterState.value.copy(
            lengthToSelection = lengthTypes,
            toExpanded = !_unitConverterState.value.toExpanded,
        )
    }

    fun onWeightToSelection(weightTypes: WeightTypes) {
        _unitConverterState.value = _unitConverterState.value.copy(
            weightToSelection = weightTypes,
            toExpanded = !_unitConverterState.value.toExpanded,
        )
    }

    fun onTemperatureToSelection(temperatureTypes: TemperatureTypes) {
        _unitConverterState.value = _unitConverterState.value.copy(
            temperatureToSelection = temperatureTypes,
            toExpanded = !_unitConverterState.value.toExpanded,
        )
    }

    fun convert(units: Units) {}
}