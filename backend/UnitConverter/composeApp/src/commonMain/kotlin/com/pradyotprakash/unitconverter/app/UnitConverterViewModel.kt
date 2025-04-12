package com.pradyotprakash.unitconverter.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.unitconverter.core.models.request.LengthTypes
import com.pradyotprakash.unitconverter.core.models.request.TemperatureTypes
import com.pradyotprakash.unitconverter.core.models.request.Units
import com.pradyotprakash.unitconverter.core.models.request.WeightTypes
import com.pradyotprakash.unitconverter.core.models.response.ClientResponse
import com.pradyotprakash.unitconverter.di.SharedModulesDi
import com.pradyotprakash.unitconverter.domain.repositories.converter.UnitConverterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UnitConverterViewModel(
    private val unitConverterRepository: UnitConverterRepository = SharedModulesDi.Instance.unitConverterRepository,
) : ViewModel() {
    private val _unitConverterState = MutableStateFlow(UnitConverterState())
    val unitConverterStateState = _unitConverterState.asStateFlow()

    fun onFromTapped() {
        _unitConverterState.update {
            it.copy(
                fromExpanded = !_unitConverterState.value.fromExpanded,
                toExpanded = false,
            )
        }
    }

    fun onToTapped() {
        _unitConverterState.update {
            it.copy(
                toExpanded = !_unitConverterState.value.toExpanded,
                fromExpanded = false,
            )
        }
    }

    fun onLengthFromSelection(lengthTypes: LengthTypes) {
        _unitConverterState.update {
            it.copy(
                lengthFromSelection = lengthTypes,
                fromExpanded = !_unitConverterState.value.fromExpanded,
            )
        }
    }

    fun onWeightFromSelection(weightTypes: WeightTypes) {
        _unitConverterState.update {
            it.copy(
                weightFromSelection = weightTypes,
                fromExpanded = !_unitConverterState.value.fromExpanded,
            )
        }
    }

    fun onTemperatureFromSelection(temperatureTypes: TemperatureTypes) {
        _unitConverterState.update {
            it.copy(
                temperatureFromSelection = temperatureTypes,
                fromExpanded = !_unitConverterState.value.fromExpanded,
            )
        }
    }

    fun onLengthToSelection(lengthTypes: LengthTypes) {
        _unitConverterState.update {
            it.copy(
                lengthToSelection = lengthTypes,
                toExpanded = !_unitConverterState.value.toExpanded,
            )
        }
    }

    fun onWeightToSelection(weightTypes: WeightTypes) {
        _unitConverterState.update {
            it.copy(
                weightToSelection = weightTypes,
                toExpanded = !_unitConverterState.value.toExpanded,
            )
        }
    }

    fun onTemperatureToSelection(temperatureTypes: TemperatureTypes) {
        _unitConverterState.update {
            it.copy(
                temperatureToSelection = temperatureTypes,
                toExpanded = !_unitConverterState.value.toExpanded,
            )
        }
    }

    fun onValueChange(value: String) {
        _unitConverterState.update {
            it.copy(
                value = value,
                errorMessage = null,
            )
        }
    }

    fun convert(units: Units) {
        viewModelScope.launch {
            when (units) {
                Units.LENGTH -> unitConverterRepository.unitConverterLength(
                    value = _unitConverterState.value.value,
                    from = _unitConverterState.value.lengthFromSelection,
                    to = _unitConverterState.value.lengthToSelection,
                )

                Units.WEIGHT -> unitConverterRepository.unitConverterWeight(
                    value = _unitConverterState.value.value,
                    from = _unitConverterState.value.weightFromSelection,
                    to = _unitConverterState.value.weightToSelection,
                )

                Units.TEMPERATURE -> unitConverterRepository.unitConverterTemperature(
                    value = _unitConverterState.value.value,
                    from = _unitConverterState.value.temperatureFromSelection,
                    to = _unitConverterState.value.temperatureToSelection,
                )
            }.collect { result ->
                when (result) {
                    is ClientResponse.Loading -> updateLoaderState(true)
                    is ClientResponse.Error -> showMessage(result.message)
                    is ClientResponse.Idle -> updateLoaderState(false)
                    is ClientResponse.Success -> {
                        _unitConverterState.update {
                            it.copy(
                                showLoading = false,
                                result = result.data.data?.humanReadable,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun showMessage(message: String) {
        _unitConverterState.update {
            it.copy(
                errorMessage = message,
            )
        }
    }

    private fun updateLoaderState(showLoader: Boolean) {
        _unitConverterState.update {
            it.copy(
                showLoading = showLoader,
            )
        }
    }

    fun updateStateOnPageChange() {
        _unitConverterState.update {
            it.copy(
                showLoading = false,
                result = null,
                errorMessage = null,
                value = "",
            )
        }
    }
}