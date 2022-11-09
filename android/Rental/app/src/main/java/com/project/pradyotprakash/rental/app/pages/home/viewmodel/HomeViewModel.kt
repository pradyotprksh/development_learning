package com.project.pradyotprakash.rental.app.pages.home.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.core.auth.AuthState
import com.project.pradyotprakash.rental.core.auth.AuthStateListener
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.core.navigation.path
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.device.services.UserLocalServices
import com.project.pradyotprakash.rental.domain.modal.LocationEntity
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity
import com.project.pradyotprakash.rental.domain.modal.UserEntity
import com.project.pradyotprakash.rental.domain.usecase.AuthenticationUseCase
import com.project.pradyotprakash.rental.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator,
    private val authenticationUseCase: AuthenticationUseCase,
    private val authStateListener: AuthStateListener,
    private val searchUseCase: SearchUseCase,
    private val localServices: UserLocalServices,
    @ApplicationContext context: Context,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    val userDetails: LiveData<UserEntity?>
        get() = authStateListener.userDetails
    private val _properties = MutableLiveData<List<PropertyEntity>>()
    val properties: LiveData<List<PropertyEntity>>
        get() = _properties
    private val _nearByProperties = MutableLiveData<List<PropertyEntity>>()
    val nearByProperties: LiveData<List<PropertyEntity>>
        get() = _nearByProperties
    private val _otherProperties = MutableLiveData<List<PropertyEntity>>()
    val otherProperties: LiveData<List<PropertyEntity>>
        get() = _otherProperties
    private val _locationResult = MutableLiveData<List<LocationEntity>>(emptyList())
    val locationResult: LiveData<List<LocationEntity>>
        get() = _locationResult

    private var fusedLocationClient: FusedLocationProviderClient = LocationServices
        .getFusedLocationProviderClient(context)
    private val cancellationToken = object : CancellationToken() {
        override fun onCanceledRequested(listener: OnTokenCanceledListener) =
            CancellationTokenSource().token
        override fun isCancellationRequested() = false
    }

    private val userType: UserType
        get() = localServices.userType
    private val savedLatitude: String
        get() = localServices.currentLocation.first
    private val savedLongitude: String
        get() = localServices.currentLocation.second

    init {
        if (userType == UserType.Renter) {
            _loading.value = true
            setupLocationService()
        } else {
            checkForUserDetails()
        }
    }

    private fun setupLocationService() {
        tryForCurrentLocation()
    }

    @SuppressLint("MissingPermission")
    private fun tryForCurrentLocation() {
        fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            cancellationToken
        ).addOnSuccessListener { currentLocation ->
            localServices.saveCurrentLocation(currentLocation.latitude.toString(), currentLocation.longitude.toString())
            searchForLocationDetails(currentLocation.latitude, currentLocation.longitude)
        }.addOnFailureListener {
            tryForLastKnownLocation()
        }
    }

    private fun searchForLocationDetails(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            searchUseCase.performLocationSearch(
                latitude = latitude.toString(),
                longitude = longitude.toString(),
                searchedText = "",
                zipCode = "",
                exactly_one = true,
            ).collect {
                checkForUserDetails()
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
    }

    @SuppressLint("MissingPermission")
    private fun tryForLastKnownLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { lastKnownLocation ->
            localServices.saveCurrentLocation(lastKnownLocation.latitude.toString(), lastKnownLocation.longitude.toString())
            searchForLocationDetails(lastKnownLocation.latitude, lastKnownLocation.longitude)
        }.addOnFailureListener {
            checkForUserDetails()
        }
    }

    private fun checkForUserDetails() {
        viewModelScope.launch {
            authenticationUseCase.getCurrentUserId()?.let { userId ->
                authenticationUseCase.getCurrentUserDetails(
                    userId = userId,
                    latitude = savedLatitude,
                    longitude = savedLongitude,
                ).collect {
                    when (it) {
                        is RenterResponse.Error -> {
                            if (it.exception.isNotFound()) {
                                goToInformationScreen(
                                    true,
                                )
                            } else {
                                updateErrorState(it.exception.localizedMessage)
                            }
                        }
                        is RenterResponse.Loading -> _loading.value = true
                        is RenterResponse.Success -> {
                            it.data.data?.let { userDetails ->
                                authStateListener.updateUserDetails(userDetails)
                                authenticationUseCase.updateUserDetails(
                                    userDetails.fullName,
                                    userDetails.profile_pic_url,
                                )

                                if (userDetails.user_type == UserType.Owner.name) {
                                    _properties.value = userDetails.properties ?: emptyList()
                                } else {
                                    _nearByProperties.value = userDetails.nearby_properties ?: emptyList()
                                    _otherProperties.value = userDetails.other_properties ?: emptyList()
                                }

                                if (!userDetails.is_all_details_available) {
                                    goToInformationScreen(
                                        false,
                                    )
                                }
                            } ?: kotlin.run {
                                authStateListener.stateChange(AuthState.Unauthenticated)
                            }
                        }
                        is RenterResponse.Idle -> _loading.value = false
                    }
                }
            }
        }
    }

    /**
     * Go to the get information details screen
     */
    private fun goToInformationScreen(firstTimeAddingDetails: Boolean) {
        navigator.navigate {
            it.navigate("${Routes.Information.route}${false}/${false}/$firstTimeAddingDetails")
        }
    }

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    fun goToAddPropertyScreen() {
        navigator.navigate {
            it.navigate(Routes.Property.route)
        }
    }

    fun navigateToPropertyDetails(propertyId: String) {
        navigator.navigate {
            it.navigate("${Routes.PropertyDetails.route}${propertyId}")
        }
    }

    fun goToUserDetails() {
        authenticationUseCase.getCurrentUserId()?.let { userId ->
            navigator.navigate {
                it.navigate("${Routes.UserDetails.route}${userId}")
            }
        } ?: run {
            updateErrorState(TR.pleaseTryAgain)
        }
    }

    fun goToSearchPage() {
        navigator.navigate {
            it.navigate(Routes.Search.path())
        }
    }
}