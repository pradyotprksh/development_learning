package com.pradyotprakash.libraryowner.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.libraryowner.core.geolocation.IpGeolocator
import com.pradyotprakash.libraryowner.core.response.OwnerResponse
import com.pradyotprakash.libraryowner.domain.usecases.AppConfigUseCase
import com.pradyotprakash.libraryowner.domain.usecases.IpGeolocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appConfigUseCase: AppConfigUseCase,
    private val ipGeolocationUseCase: IpGeolocationUseCase,
    private val ipGeolocator: IpGeolocator,
) : ViewModel() {
    val currentLanguage = appConfigUseCase.getCurrentLanguage()

    init {
        getGeoLocationDetails()
    }

    private fun getGeoLocationDetails() {
        viewModelScope.launch {
            ipGeolocationUseCase.getGeolocationDetails().collect {
                when (it) {
                    is OwnerResponse.Success -> ipGeolocator.update(it.data)
                    else -> {}
                }
            }
        }
    }
}