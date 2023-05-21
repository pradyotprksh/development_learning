package com.pradyotprakash.findingfalcone.app.pages.result.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import com.pradyotprakash.findingfalcone.app.localization.TR
import com.pradyotprakash.findingfalcone.core.navigation.Navigator
import com.pradyotprakash.findingfalcone.core.response.FindingFalconeResponse
import com.pradyotprakash.findingfalcone.domain.entity.FindEntity
import com.pradyotprakash.findingfalcone.domain.usecase.FindUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val findUseCase: FindUseCase,
    private val navigator: Navigator,
): ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _findResult = MutableLiveData<FindEntity?>()
    val findResult: LiveData<FindEntity?>
        get() = _findResult

    fun findFalcone(planets: List<String>, vehicles: List<String>) {
        viewModelScope.launch {
            _loading.value = true
            try {
                when (val result = findUseCase
                    .findFalcone(
                        planetNames = planets,
                        vehicleNames = vehicles
                    )
                ) {
                    is FindingFalconeResponse.Error -> {
                        updateErrorState(result.exception.message)
                    }

                    is FindingFalconeResponse.Success -> {
                        _findResult.value = result.data
                    }
                }
            } catch (e: Exception) {
                Logger.e(e.localizedMessage ?: "")
                updateErrorState(TR.noDataFoundError)
            }
            _loading.value = false
        }
    }

    fun updateErrorState(message: String = "") {
        _errorText.value = message
        _loading.value = false
    }

    fun retry() {
        navigator.navigate {
            it.popBackStack()
        }
    }
}