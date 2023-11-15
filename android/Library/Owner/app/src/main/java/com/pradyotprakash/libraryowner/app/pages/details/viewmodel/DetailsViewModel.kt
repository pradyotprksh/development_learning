package com.pradyotprakash.libraryowner.app.pages.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils.CustomerDetails
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils.DetailsSwitchField
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils.DetailsTextField
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils.LibraryDetails
import com.pradyotprakash.libraryowner.core.geolocation.IpGeolocator
import com.pradyotprakash.libraryowner.core.models.AuthUser
import com.pradyotprakash.libraryowner.core.navigation.Navigator
import com.pradyotprakash.libraryowner.domain.usecases.AuthenticationUseCase
import com.pradyotprakash.libraryowner.domain.usecases.UserFirestoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
    private val navigator: Navigator,
    private val ipGeolocator: IpGeolocator,
    private val userFirestoreUseCase: UserFirestoreUseCase,
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
    private val _authUser = MutableLiveData<AuthUser>()
    val authUser: LiveData<AuthUser>
        get() = _authUser
    private val _libraryDetails = MutableLiveData(listOf<LibraryDetails>())
    val libraryDetails: LiveData<List<LibraryDetails>>
        get() = _libraryDetails
    private val region: String
        get() = ipGeolocator.ipGeolocation.value?.countryCode2 ?: ""

    init {
        getAuthenticationUserDetails()
        addNewLibraryInformation()
    }

    fun updateErrorState(message: String? = "") {
        _loading.value = false
        _errorText.value = message
    }

    private fun getAuthenticationUserDetails() {
        authenticationUseCase.getCurrentUserDetails()?.let { details -> _authUser.value = details }
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
        _authUser.value?.let { authUser ->
            _customerDetails.value?.let { userDetails ->
                _libraryDetails.value?.let { libraryDetails ->
                    if (authUser.userId.isNotBlank() && libraryDetails.isNotEmpty()) {
                        if (checkUserDetails(userDetails) && checkForLibraryDetails(libraryDetails)) {
                            viewModelScope.launch {
                                updateUserDetails(
                                    userId = authUser.userId,
                                    userDetails = userDetails,
                                    createdOn = authUser.createdOnTimestamp
                                )

                                updateLibraryDetails(
                                    userId = authUser.userId,
                                    libraryDetails = libraryDetails,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updateLibraryDetails(userId: String, libraryDetails: List<LibraryDetails>) {
        TODO("Not yet implemented")
    }

    private suspend fun updateUserDetails(
        userId: String,
        userDetails: CustomerDetails,
        createdOn: Long?,
    ) {
        userFirestoreUseCase.setUserDetails(
            userId = userId,
            customerDetails = userDetails,
            createdOn = createdOn,
        )
    }

    private fun checkForLibraryDetails(libraryDetails: List<LibraryDetails>): Boolean {
        val mutableDetails = libraryDetails.toMutableList()

        for (index in mutableDetails.indices) {
            mutableDetails[index] = mutableDetails[index].checkValidity(region)
        }

        _libraryDetails.value = mutableDetails.toList()

        return _libraryDetails.value?.none { !it.isValid(region) } ?: false
    }

    private fun checkUserDetails(userDetails: CustomerDetails): Boolean {
        _customerDetails.value = userDetails.checkValidity(region)

        return _customerDetails.value?.isValid(region) ?: false
    }
}