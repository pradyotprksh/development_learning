package com.project.pradyotprakash.twitter.navigator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import javax.inject.Inject

typealias NavigationAction = (NavController) -> Unit

/**
 * A navigator class which will be used by feature modules to navigate from one screen
 * to another.
 *
 * This will create a single place to change the destination.
 */
class Navigator @Inject constructor() {
    private val _navigateActions = MutableLiveData<NavigationAction>()
    val navigateAction: LiveData<NavigationAction>
        get() = _navigateActions

    fun navigate(navigationAction: NavigationAction) {
        _navigateActions.postValue(navigationAction)
    }
}