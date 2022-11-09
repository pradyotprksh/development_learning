package com.project.pradyotprakash.rental.app.composables.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.modal.LocationEntity
import com.project.pradyotprakash.rental.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationPickerViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _locationResult = MutableLiveData<List<LocationEntity>>(emptyList())
    val locationResult: LiveData<List<LocationEntity>>
        get() = _locationResult

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    fun searchForLocation(
        searchText: String,
        zipCode: String,
        latitude: String,
        longitude: String
    ) {
        if (searchText.isNotBlank() || zipCode.isNotBlank() ||
            (latitude.isNotBlank() && longitude.isNotBlank())
        ) {
            viewModelScope.launch {
                searchUseCase.performLocationSearch(
                    searchedText = searchText,
                    zipCode = zipCode,
                    latitude = latitude,
                    longitude = longitude,
                    exactly_one = false,
                ).collect {
                    when (it) {
                        is RenterResponse.Error -> updateErrorState(it.exception.message)
                        is RenterResponse.Idle -> _loading.value = false
                        is RenterResponse.Loading -> _loading.value = true
                        is RenterResponse.Success -> {
                            _locationResult.value = it.data.data ?: emptyList()
                        }
                    }
                }
            }
        } else {
            updateErrorState(TR.locationSearchError)
        }
    }
}