package com.pradyotprakash.libraryowner.app.pages.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pradyotprakash.libraryowner.domain.usecases.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
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
}