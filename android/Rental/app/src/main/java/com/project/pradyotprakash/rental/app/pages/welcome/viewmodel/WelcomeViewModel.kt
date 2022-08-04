package com.project.pradyotprakash.rental.app.pages.welcome.viewmodel

import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.rental.core.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * The view model class which will handle the business logic.
 */
@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {
    /**
     * Navigate back
     */
    fun navigateBack() = navigator.navigateBack()

    /**
     * Go to the get information details screen
     */
    fun goToInformationScreen() {}
}