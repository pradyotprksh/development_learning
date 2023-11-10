package com.pradyotprakash.libraryowner.app.pages.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils.CustomerDetails
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils.DetailsSwitchField
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils.DetailsTextField
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils.LibraryDetails
import com.pradyotprakash.libraryowner.core.geolocation.IpGeolocator
import com.pradyotprakash.libraryowner.core.navigation.Navigator
import com.pradyotprakash.libraryowner.domain.usecases.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
    private val navigator: Navigator,
    private val ipGeolocator: IpGeolocator,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _customerDetails = MutableLiveData(CustomerDetails())
    val customerDetails: LiveData<CustomerDetails>
        get() = _customerDetails
    private val _libraryDetails = MutableLiveData(listOf<LibraryDetails>())
    val libraryDetails: LiveData<List<LibraryDetails>>
        get() = _libraryDetails
    private val region: String
        get() = ipGeolocator.ipGeolocation.value?.countryCode2 ?: ""

    init {
        addNewLibraryInformation()
    }

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    fun getAuthenticationUserDetails() {
        authenticationUseCase.getCurrentUserDetails()?.let { details -> }
    }

    fun updateTextFieldValue(value: String, type: DetailsTextField, index: Int = -1) {
        when (type) {
            DetailsTextField.CustomerName -> _customerDetails.value =
                _customerDetails.value?.copy(name = value)

            DetailsTextField.CustomerEmailId -> _customerDetails.value =
                _customerDetails.value?.copy(emailId = value)

            DetailsTextField.CustomerPhoneNumber -> _customerDetails.value =
                _customerDetails.value?.copy(phoneNumber = value)

            DetailsTextField.LibraryName -> {
                _libraryDetails.value?.let { details ->
                    val mutableDetails = details.toMutableList()
                    mutableDetails[index] = mutableDetails[index].copy(name = value)
                    _libraryDetails.value = mutableDetails.toList()
                }
            }

            DetailsTextField.LibraryEmailId -> {
                _libraryDetails.value?.let { details ->
                    val mutableDetails = details.toMutableList()
                    mutableDetails[index] = mutableDetails[index].copy(emailId = value)
                    _libraryDetails.value = mutableDetails.toList()
                }
            }

            DetailsTextField.LibraryPhoneNumber -> {
                _libraryDetails.value?.let { details ->
                    val mutableDetails = details.toMutableList()
                    mutableDetails[index] = mutableDetails[index].copy(phoneNumber = value)
                    _libraryDetails.value = mutableDetails.toList()
                }
            }

            DetailsTextField.LibraryAddress -> {
                _libraryDetails.value?.let { details ->
                    val mutableDetails = details.toMutableList()
                    mutableDetails[index] = mutableDetails[index].copy(address = value)
                    _libraryDetails.value = mutableDetails.toList()
                }
            }
        }
    }

    fun openImagePicker() {}

    fun addNewLibraryInformation() {
        _libraryDetails.value =
            ((_libraryDetails.value ?: emptyList()) + listOf(LibraryDetails())).toList()
    }

    fun deleteLibraryInformation(index: Int) {
        _libraryDetails.value?.let { details ->
            val mutableDetails = details.toMutableList()
            mutableDetails.removeAt(index)
            _libraryDetails.value = mutableDetails.toList()
        }
    }

    fun onCheckedChange(value: Boolean, detailsSwitchField: DetailsSwitchField, index: Int) {
        when (detailsSwitchField) {
            DetailsSwitchField.EmailIdSame -> {
                _libraryDetails.value?.let { details ->
                    val mutableDetails = details.toMutableList()
                    mutableDetails[index] =
                        mutableDetails[index].copy(emailIdSameAsCustomer = value)
                    _libraryDetails.value = mutableDetails.toList()
                }
            }

            DetailsSwitchField.PhoneNumberSame -> {
                _libraryDetails.value?.let { details ->
                    val mutableDetails = details.toMutableList()
                    mutableDetails[index] =
                        mutableDetails[index].copy(phoneNumberSameAsCustomer = value)
                    _libraryDetails.value = mutableDetails.toList()
                }
            }
        }
    }

    fun saveDetails() {
        val userDetails = _customerDetails.value
        val libraryDetails = _libraryDetails.value
        if (userDetails != null) {
            if (!libraryDetails.isNullOrEmpty()) {
                checkUserDetails(userDetails)
                checkForLibraryDetails(libraryDetails)
            }
        }
    }

    private fun checkForLibraryDetails(libraryDetails: List<LibraryDetails>) {
        _libraryDetails.value?.let { details ->
            val mutableDetails = details.toMutableList()

            for (index in mutableDetails.indices) {
                mutableDetails[index] = mutableDetails[index].checkValidity(region)
            }

            _libraryDetails.value = mutableDetails.toList()
        }
    }

    private fun checkUserDetails(userDetails: CustomerDetails) {
        _customerDetails.value = userDetails.checkValidity(region)
    }
}