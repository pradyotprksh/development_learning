package app

import core.navigator.Navigator
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import di.DiFactory

class AppViewModel(
    private val navigator: Navigator = DiFactory.navigator,
): ViewModel() {
    var navigatorSet: Boolean = false
}