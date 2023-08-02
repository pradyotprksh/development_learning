package core.navigator

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.postValue
import moe.tlaster.precompose.navigation.Navigator

typealias NavigationAction = (Navigator) -> Unit

class Navigator {
    private val _navigateActions = MutableLiveData<NavigationAction> {}

    val navigateAction: LiveData<NavigationAction>
        get() = _navigateActions

    /**
     * Navigate to a certain page
     */
    fun navigate(navigationAction: NavigationAction) {
        _navigateActions.postValue { navigationAction }
    }
}