package com.project.pradyotprakash.rental.app.pages.property.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.services.AppCheckService
import com.project.pradyotprakash.rental.domain.usecase.PropertyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyDetailsViewModel @Inject constructor(
    private val navigator: Navigator,
    private val appCheckService: AppCheckService,
    private val propertyUseCase: PropertyUseCase,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText

    fun getPropertyDetails(propertyId: String) {
        viewModelScope.launch {
            appCheckService.getAppCheckToken().collect { appCheckToken ->
                when (appCheckToken) {
                    is RenterResponse.Error -> updateErrorState(appCheckToken.exception.message)
                    is RenterResponse.Loading -> _loading.value = true
                    is RenterResponse.Success -> {
                        propertyUseCase.getProperties(
                            appCheckToken = appCheckToken.data,
                            propertyId = propertyId,
                        ).collect {
                            when (it) {
                                is RenterResponse.Error -> updateErrorState(it.exception.message)
                                is RenterResponse.Idle -> _loading.value = false
                                is RenterResponse.Loading -> _loading.value = true
                                is RenterResponse.Success -> _loading.value = false
                            }
                        }
                    }
                    else -> {}
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
}