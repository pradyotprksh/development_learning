package com.project.pradyotprakash.rental.app

import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.rental.domain.usecase.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * View model class for the [MainActivity], this will handle all the business logic
 * and helps in keeping the view class clean.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
) : ViewModel() {
    fun logoutUser() = authenticationUseCase.logoutUser()
}