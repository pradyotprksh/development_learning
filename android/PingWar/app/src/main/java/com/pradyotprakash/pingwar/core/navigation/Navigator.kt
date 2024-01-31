package com.pradyotprakash.pingwar.core.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import javax.inject.Inject
import javax.inject.Singleton

typealias NavigationAction = (NavController) -> Unit

@Singleton
class Navigator @Inject constructor() {
    private val _navigateActions = MutableLiveData<NavigationAction>()
    val navigateAction: LiveData<NavigationAction>
        get() = _navigateActions

    fun navigate(navigationAction: NavigationAction) {
        _navigateActions.postValue(navigationAction)
    }
}