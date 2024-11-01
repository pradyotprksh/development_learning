package com.pradyotprakash.glassbridgegame.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pradyotprakash.glassbridgegame.app.navigation.BridgeGame
import com.pradyotprakash.glassbridgegame.app.navigation.Home
import com.pradyotprakash.glassbridgegame.app.navigation.Splash
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.screen.BridgeGameScreen
import com.pradyotprakash.glassbridgegame.app.pages.home.screen.HomeScreen
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

    val navigateToBridgeGame = { isOffline: Boolean ->
        navController.navigate(
            BridgeGame(
                isOffline = isOffline,
            )
        )
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
        composable<Home> {
            HomeScreen(
                navigateToBridgeGame = navigateToBridgeGame,
            )
        }
        composable<BridgeGame> { backStackEntry ->
            val bridgeGameDetails = backStackEntry.toRoute<BridgeGame>()

            BridgeGameScreen()
        }
    }
}