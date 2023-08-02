package app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import core.navigator.Routes
import core.navigator.path
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import di.DiFactory
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition

@Composable
fun App(
    appViewModel: AppViewModel = getViewModel(Unit, viewModelFactory { AppViewModel() })
) {
    val customNavigator = DiFactory.navigator
    val navigator = rememberNavigator()
    if (!appViewModel.navigatorSet) {
        customNavigator.navigate { navigator }
        appViewModel.navigatorSet = true
    }

    MaterialTheme {
        NavHost(
            navigator = navigator,
            navTransition = NavTransition(),
            initialRoute = Routes.Splash.path(),
        ) {
            scene(Routes.Splash.path()) {}
        }
    }
}