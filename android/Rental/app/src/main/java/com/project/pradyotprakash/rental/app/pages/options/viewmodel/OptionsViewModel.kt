package com.project.pradyotprakash.rental.app.pages.options.viewmodel

import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.core.utils.Constants
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
        Constants.currentUserType = UserType.Owner
        navigator.navigate {
            it.navigate("${Routes.Welcome.route}${UserType.Owner}")
        }
    }

    /**
     * Start the navigation to the renter flow
     */
    fun navigateToRenterFlow() {
        Constants.currentUserType = UserType.Renter
        navigator.navigate {
            it.navigate("${Routes.Welcome.route}${UserType.Renter}")
        }
    }
}