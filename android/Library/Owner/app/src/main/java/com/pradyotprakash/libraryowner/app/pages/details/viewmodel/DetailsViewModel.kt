package com.pradyotprakash.libraryowner.app.pages.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils.CustomerDetails
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils.DetailsTextField
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils.LibraryDetails
import com.pradyotprakash.libraryowner.app.routes.Routes
import com.pradyotprakash.libraryowner.app.routes.path
import com.pradyotprakash.libraryowner.core.navigation.Navigator
import com.pradyotprakash.libraryowner.domain.usecases.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
    private val navigator: Navigator,
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

    fun updateTextFieldValue(value: String, type: DetailsTextField) {
        when (type) {
            DetailsTextField.CustomerName -> _customerDetails.value =
                _customerDetails.value?.copyWith(name = value)

            DetailsTextField.CustomerEmailId -> _customerDetails.value =
                _customerDetails.value?.copyWith(emailId = value)

            DetailsTextField.CustomerPhoneNumber -> _customerDetails.value =
                _customerDetails.value?.copyWith(phoneNumber = value)
        }
    }

    fun openImagePicker() {
        navigator.navigate { it.navigate(Routes.ImagePicker.path()) }
    }

    fun addNewLibraryInformation() {
        _libraryDetails.value = ((_libraryDetails.value ?: emptyList()) + listOf(LibraryDetails())).toList()
    }

    fun deleteLibraryInformation(index: Int) {
        _libraryDetails.value?.let { details ->
            val mutableDetails = details.toMutableList()
            mutableDetails.removeAt(index)
            _libraryDetails.value = mutableDetails.toList()
        }
    }
}