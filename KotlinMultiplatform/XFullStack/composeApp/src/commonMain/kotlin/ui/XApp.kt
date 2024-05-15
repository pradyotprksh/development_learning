package ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.navigation.Routes
import ui.pages.authOptions.screen.AuthOptionsScreen
import ui.pages.login.screen.LoginScreen
import ui.pages.splash.screen.SplashScreen

/**
 * XApp
 */
@Composable
fun XApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            startDestination = Routes.Splash.route,
        ) {
            composable(Routes.Splash.route) {
                SplashScreen(
                    navigateToAuthOption = {
                        navController.navigate(Routes.AuthenticationOption.route) {
                            popUpTo(Routes.Splash.route) {
                                inclusive = true
                            }
                        }
                    },
                    navigateToHome = {}
                )
            }
            composable(Routes.AuthenticationOption.route) {
                AuthOptionsScreen {
                    navController.navigate(Routes.Login.route)
                }
            }
            composable(Routes.Login.route) {
                LoginScreen {
                    navController.popBackStack()
                }
            }
        }
    }
}