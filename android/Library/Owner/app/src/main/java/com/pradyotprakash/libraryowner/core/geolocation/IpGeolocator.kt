package com.pradyotprakash.libraryowner.core.geolocation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pradyotprakash.libraryowner.core.models.IpGeolocationDetails
import javax.inject.Inject

class IpGeolocator @Inject constructor() {
    private val _ipGeolocation = MutableLiveData<IpGeolocationDetails>()
    val ipGeolocation: LiveData<IpGeolocationDetails>
        get() = _ipGeolocation

    fun update(ipGeolocationDetails: IpGeolocationDetails) {
        _ipGeolocation.postValue(ipGeolocationDetails)
    }
}