package com.pradyotprakash.glassbridgegame.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pradyotprakash.glassbridgegame.app.navigation.Home
import com.pradyotprakash.glassbridgegame.app.navigation.Splash
import com.pradyotprakash.glassbridgegame.app.pages.splash.screen.SplashScreen
import com.pradyotprakash.glassbridgegame.utils.extensions.popUpToTop

@Composable
fun GlassBridgeGameApp(
    navController: NavHostController = rememberNavController(),
) {
    val navigateToHome = {
        navController.navigate(Home) {
            popUpToTop(navController)
        }
    }

    NavHost(
        navController,
        startDestination = Splash,
    ) {
        composable<Splash> {
            SplashScreen(
                navigateToHome = navigateToHome,
            )
        }
        composable<Home> {}
    }
}