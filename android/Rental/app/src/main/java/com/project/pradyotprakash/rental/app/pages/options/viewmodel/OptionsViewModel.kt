package com.project.pradyotprakash.rental.app.pages.options.viewmodel

import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.device.services.UserLocalServices
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A view model class for the options view.
 */
@HiltViewModel
class OptionsViewModel @Inject constructor(
    private val navigator: Navigator,
    private val userLocalServices: UserLocalServices,
) : ViewModel() {
    /**
     * Start navigation to the owner flow
     */
    fun navigateToOwnerFlow() {
        userLocalServices.saveSelectedUserType(UserType.Owner.name)
        navigator.navigate {
            it.navigate(Routes.Welcome.route)
        }
    }

    /**
     * Start the navigation to the renter flow
     */
    fun navigateToRenterFlow() {
        userLocalServices.saveSelectedUserType(UserType.Renter.name)
        navigator.navigate {
            it.navigate(Routes.Welcome.route)
        }
    }
}