package com.pradyotprakash.findingfalcone.core.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import javax.inject.Inject
import javax.inject.Singleton

typealias NavigationAction = (NavController) -> Unit

/**
 * A navigator class which acts like a listener and teller for any navigation required.
 * The listener for this navigation is in [MainActivity]
 */
@Singleton
class Navigator @Inject constructor() {
    private val _navigateActions = MutableLiveData<NavigationAction>()
    val navigateAction: LiveData<NavigationAction>
        get() = _navigateActions

    /**
     * Navigate to a certain page
     */
    fun navigate(navigationAction: NavigationAction) {
        _navigateActions.postValue(navigationAction)
    }

    /**
     * Pop the current page from the stack and go back
     */
    fun navigateBack() {
        navigate {
            it.popBackStack()
        }
    }
}