package com.pradyotprakash.findingfalcone.app.pages.selector.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import com.pradyotprakash.findingfalcone.app.localization.TR
import com.pradyotprakash.findingfalcone.core.response.FindingFalconeResponse
import com.pradyotprakash.findingfalcone.core.utils.Constants.PLANETS_COUNT
import com.pradyotprakash.findingfalcone.domain.entity.FindEntity
import com.pradyotprakash.findingfalcone.domain.entity.Planets
import com.pradyotprakash.findingfalcone.domain.entity.Vehicles
import com.pradyotprakash.findingfalcone.domain.usecase.FindUseCase
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
    private val findUseCase: FindUseCase,
) : ViewModel() {
    val randomList = (1..6).toList().shuffled()
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _planets = MutableLiveData<Planets>(emptyList())
    val planets: LiveData<Planets>
        get() = _planets
    private val _vehicles = MutableLiveData<Vehicles>(emptyList())
    val vehicles: LiveData<Vehicles>
        get() = _vehicles
    private val _isArmySelected = MutableLiveData(false)
    val isArmySelected: LiveData<Boolean>
        get() = _isArmySelected
    private val _totalTime = MutableLiveData<Long>(0)
    val totalTime: LiveData<Long>
        get() = _totalTime
    private val _findResult = MutableLiveData<FindEntity?>()
    val findResult: LiveData<FindEntity?>
        get() = _findResult

    fun retry() {
        _totalTime.value = 0
        _findResult.value = null
        _isArmySelected.value = false
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

            is FindingFalconeResponse.Success -> _vehicles.value = vehiclesResponse.data
        }
    }

    private suspend fun getPlanets() {
        when (val planetsResponse = planetsUseCase.getPlanets()) {
            is FindingFalconeResponse.Error -> {
                updateErrorState(planetsResponse.exception.message)
                _planets.value = emptyList()
            }

            is FindingFalconeResponse.Success -> _planets.value = planetsResponse.data
        }
    }

    fun updateErrorState(message: String = "") {
        _errorText.value = message
        _loading.value = false
    }

    fun selectPlanet(index: Int) {
        if (_loading.value == true) return
        val oldList = _planets.value

        val totalSelected = numberOfPlanetsSelected(oldList)

        _planets.value = emptyList()
        _planets.value = oldList?.run {
            if (totalSelected != PLANETS_COUNT) {
                this[index].selected = !this[index].selected
            } else if (this[index].selected) {
                this[index].selected = false
            }
            if (!this[index].selected) {
                updateVehiclePodCount(
                    this[index].vehicleDetails?.name ?: "",
                    false
                )
                this[index].vehicleDetails = null
            }
            this
        }
        isArmySelected()
    }

    private fun isArmySelected() {
        _isArmySelected.value = numberOfPlanetsSelected(_planets.value) == PLANETS_COUNT
    }

    private fun numberOfPlanetsSelected(planets: Planets?): Int {
        var totalSelected = 0
        var totalTime: Long = 0
        planets?.forEach { planet ->
            if (planet.selected && planet.vehicleDetails != null) {
                ++totalSelected

                planet.vehicleDetails?.let { vehicle ->
                    getVehicleDetails(vehicle.name)?.let { details ->
                        totalTime += planet.distance / details.speed
                    }
                }
            }
        }
        _totalTime.value = totalTime
        return totalSelected
    }

    private fun updateVehiclePodCount(vehicleName: String, isSelected: Boolean) {
        val oldList = _vehicles.value

        _vehicles.value = oldList?.run {
            this.forEach { vehicle ->
                if (vehicle.name == vehicleName) {
                    vehicle.total_no = if (isSelected) --vehicle.total_no else ++vehicle.total_no
                }
            }
            this
        }
    }

    fun selectVehicle(index: Int, vehicleName: String) {
        if (_loading.value == true) return
        val vehicleDetails = getVehicleDetails(vehicleName)

        val oldPlanetsList = _planets.value
        _planets.value = emptyList()
        _planets.value = oldPlanetsList?.run {
            if (this[index].vehicleDetails != vehicleDetails) {
                updateVehiclePodCount(
                    this[index].vehicleDetails?.name ?: "",
                    false
                )
                this[index].vehicleDetails = vehicleDetails
                updateVehiclePodCount(
                    this[index].vehicleDetails?.name ?: "",
                    true
                )
            }
            this
        }
        isArmySelected()
    }

    private fun getVehicleDetails(name: String) = _vehicles.value?.first { it.name == name }
    fun sendTheArmy() {
        val selectedPlanets = ArrayList<String>()
        val selectedVehicles = ArrayList<String>()
        _planets.value?.let { planets ->
            planets.forEach { planet ->
                if (planet.selected) {
                    selectedPlanets.add(planet.name)
                    planet.vehicleDetails?.let {
                        selectedVehicles.add(it.name)
                    }
                }
            }
        }
        viewModelScope.launch {
            _loading.value = true
            try {
                when (val result = findUseCase
                    .findFalcone(
                        planetNames = selectedPlanets,
                        vehicleNames = selectedVehicles
                    )
                ) {
                    is FindingFalconeResponse.Error -> {
                        updateErrorState(result.exception.message)
                    }

                    is FindingFalconeResponse.Success -> {
                        _findResult.value = result.data
                        _isArmySelected.value = false
                    }
                }
            } catch (e: Exception) {
                Logger.e(e.localizedMessage ?: "")
                updateErrorState(TR.noDataFoundError)
            }
            _loading.value = false
        }
    }
}