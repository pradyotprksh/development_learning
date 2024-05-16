package app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.navigation.Routes
import app.pages.auth.authOptions.screen.AuthOptionsScreen
import app.pages.auth.login.screen.LoginScreen
import app.pages.auth.register.screen.RegisterScreen
import app.pages.splash.screen.SplashScreen

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
                AuthOptionsScreen(
                    navigateToLogin = {
                        navController.navigate(Routes.Login.route)
                    },
                    navigateToRegister = {
                        navController.navigate(Routes.Register.route)
                    },
                )
            }
            composable(Routes.Login.route) {
                LoginScreen {
                    navController.popBackStack()
                }
            }
            composable(Routes.Register.route) {
                RegisterScreen {
                    navController.popBackStack()
                }
            }
        }
    }
}