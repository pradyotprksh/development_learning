package com.pradyotprakash.findingfalcone.app.pages.selector.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import com.pradyotprakash.findingfalcone.app.composables.ConfirmationDialog
import com.pradyotprakash.findingfalcone.app.localization.TR
import com.pradyotprakash.findingfalcone.core.response.FindingFalconeResponse
import com.pradyotprakash.findingfalcone.core.utils.Constants.PLANETS_COUNT
import com.pradyotprakash.findingfalcone.domain.entity.Planets
import com.pradyotprakash.findingfalcone.domain.entity.Vehicles
import com.pradyotprakash.findingfalcone.domain.usecase.PlanetsUseCase
import com.pradyotprakash.findingfalcone.domain.usecase.VehiclesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectorViewModel @Inject constructor(
    private val planetsUseCase: PlanetsUseCase,
    private val vehiclesUseCase: VehiclesUseCase,
) : ViewModel() {
    val randomList = (1..6).toList().shuffled()
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _confirmationDialog = MutableLiveData(ConfirmationDialog())
    val confirmationDialog: LiveData<ConfirmationDialog>
        get() = _confirmationDialog
    private val _planets = MutableLiveData<Planets>(emptyList())
    val planets: LiveData<Planets>
        get() = _planets
    private val _vehicles = MutableLiveData<Vehicles>(emptyList())
    val vehicles: LiveData<Vehicles>
        get() = _vehicles
    private val _planetsSelected = MutableLiveData(false)
    val planetsSelected: LiveData<Boolean>
        get() = _planetsSelected

    fun retry() {
        getDetails()
    }

    fun getDetails() {
        viewModelScope.launch {
            coroutineScope {
                _loading.value = true
                try {
                    getPlanets()
                    getVehicles()
                } catch (e: Exception) {
                    Logger.e(e.localizedMessage ?: "")
                    updateErrorState(TR.noDataFoundError)
                }
                _loading.value = false
            }
        }
    }

    private suspend fun getVehicles() {
        when (val vehiclesResponse = vehiclesUseCase.getVehicles()) {
            is FindingFalconeResponse.Error -> {
                updateErrorState(vehiclesResponse.exception.message)
                _vehicles.value = emptyList()
            }

            is FindingFalconeResponse.Idle -> _loading.value = false
            is FindingFalconeResponse.Loading -> _loading.value = true
            is FindingFalconeResponse.Success -> _vehicles.value = vehiclesResponse.data!!
        }
    }

    private suspend fun getPlanets() {
        when (val planetsResponse = planetsUseCase.getPlanets()) {
            is FindingFalconeResponse.Error -> {
                updateErrorState(planetsResponse.exception.message)
                _planets.value = emptyList()
            }

            is FindingFalconeResponse.Idle -> _loading.value = false
            is FindingFalconeResponse.Loading -> _loading.value = true
            is FindingFalconeResponse.Success -> _planets.value = planetsResponse.data!!
        }
    }

    fun updateErrorState(message: String = "") {
        _errorText.value = message
        _loading.value = false
    }

    fun selectPlanet(index: Int) {
        val oldList = _planets.value

        var totalSelected = 0
        oldList?.forEach { planet ->
            if (planet.selected) {
                ++totalSelected
            }
        }

        _planets.value = emptyList()
        _planets.value = oldList?.run {
            if (totalSelected != PLANETS_COUNT) {
                this[index].selected = !this[index].selected
            } else if (this[index].selected) {
                this[index].selected = false
            }
            this
        }
        isPlanetsSelected()
    }

    private fun isPlanetsSelected() {
        _planetsSelected.value = numberOfPlanetsSelected() == PLANETS_COUNT
    }

    private fun numberOfPlanetsSelected(): Int {
        var totalSelected = 0
        _planets.value?.forEach { planet ->
            if (planet.selected) {
                ++totalSelected
            }
        }
        Logger.e(totalSelected.toString())
        return totalSelected
    }
}