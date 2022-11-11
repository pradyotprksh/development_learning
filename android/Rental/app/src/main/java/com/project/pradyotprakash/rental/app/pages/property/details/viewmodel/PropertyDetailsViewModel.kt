package com.project.pradyotprakash.rental.app.pages.property.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity
import com.project.pradyotprakash.rental.domain.usecase.AuthenticationUseCase
import com.project.pradyotprakash.rental.domain.usecase.PropertyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyDetailsViewModel @Inject constructor(
    private val navigator: Navigator,
    private val propertyUseCase: PropertyUseCase,
    private val authenticationUseCase: AuthenticationUseCase,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _propertyDetails = MutableLiveData<PropertyEntity?>(null)
    val propertyDetails: LiveData<PropertyEntity?>
        get() = _propertyDetails
    private val _noProperties = MutableLiveData(false)
    val noProperties: LiveData<Boolean>
        get() = _noProperties

    fun getPropertyDetails(propertyId: String) {
        viewModelScope.launch {
            propertyUseCase.getProperties(
                propertyId = propertyId,
            ).collect {
                when (it) {
                    is RenterResponse.Error -> {
                        updateErrorState(it.exception.message)
                        _noProperties.value = true
                    }
                    is RenterResponse.Idle -> _loading.value = false
                    is RenterResponse.Loading -> _loading.value = true
                    is RenterResponse.Success -> {
                        val data = it.data.data
                        data?.firstOrNull()?.let { propertyDetails ->
                            _noProperties.value = false
                            _propertyDetails.value = propertyDetails
                        } ?: kotlin.run {
                            _noProperties.value = true
                        }
                    }
                }
            }
        }
    }

    /**
     * Navigate back
     */
    fun navigateBack() = navigator.navigateBack()

    fun updateErrorState(errorText: String? = null) {
        _loading.value = false
        _errorText.value = errorText ?: ""
    }

    fun isPropertyOwner(propertyCreatedBy: String?): Boolean {
        if (propertyCreatedBy != null && authenticationUseCase.getCurrentUserId() != null) {
            return authenticationUseCase.getCurrentUserId() == propertyCreatedBy
        }
        return false
    }

    fun searchForRenter() {
        navigator.navigate {
            it.navigate("${Routes.Search.route}${false}/${UserType.Renter.name}")
        }
    }
}