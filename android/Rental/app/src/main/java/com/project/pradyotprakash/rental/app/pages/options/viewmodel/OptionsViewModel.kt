package com.project.pradyotprakash.rental.app.pages.options.viewmodel

import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.rental.core.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A view model class for the options view.
 */
@HiltViewModel
class OptionsViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {
    /**
     * Start navigation to the owner flow
     */
    fun navigateToOwnerFlow() {
        navigator.navigate {}
    }

    /**
     * Start the navigation to the renter flow
     */
    fun navigateToRenterFlow() {
        navigator.navigate {}
    }

    /**
     * Show the more info page, for extra information needed by the user.
     */
    fun showMoreInfo() {}
}