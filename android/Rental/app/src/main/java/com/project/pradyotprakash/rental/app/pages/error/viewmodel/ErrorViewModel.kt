package com.project.pradyotprakash.rental.app.pages.error.viewmodel

import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.rental.core.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A view model class for the error screen
 */
@HiltViewModel
class ErrorViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {
    /**
     * Navigate back
     */
    fun navigateBack() {
        navigator.navigateBack()
        navigator.navigateBack()
    }
}