package com.pradyotprakash.libraryowner.app.pages.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pradyotprakash.libraryowner.domain.usecases.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
) : ViewModel() {
    private val _detailsState = MutableStateFlow<DetailsState>(DetailsState.Idle)
    val detailsState = _detailsState.asStateFlow()

    fun updateErrorState(message: String? = "") {
        _detailsState.value = DetailsState.Error(message ?: "")
    }

    fun getAuthenticationUserDetails() {
        authenticationUseCase.getCurrentUserDetails()?.let { details -> }
    }
}