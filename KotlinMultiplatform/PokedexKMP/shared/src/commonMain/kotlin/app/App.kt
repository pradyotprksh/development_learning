package app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import app.ui.splash.view.SplashView
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun App() {
    MaterialTheme {
        Navigator(SplashView())
    }
}
