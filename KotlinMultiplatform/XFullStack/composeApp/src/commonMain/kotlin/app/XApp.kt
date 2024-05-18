package app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.navigation.Routes
import app.navigation.path
import app.pages.auth.authOptions.screen.AuthOptionsScreen
import app.pages.auth.login.screen.LoginScreen
import app.pages.auth.register.screen.RegisterScreen
import app.pages.splash.screen.SplashScreen
import utils.Constants.ConstValues.NO_USERNAME
import utils.Constants.ConstValues.USERNAME

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
            startDestination = Routes.Splash.path(),
        ) {
            val navigateToAuthOption = {
                navController.navigate(Routes.AuthenticationOption.path()) {
                    popUpTo(Routes.Splash.route) {
                        inclusive = true
                    }
                }
            }
            val navigateToHome = {}
            val navigateToLogin = { value: String ->
                navController.navigate(
                    "${Routes.Login.route}$value",
                )
            }
            val navigateToRegister = {
                navController.navigate(Routes.Register.path())
            }

            composable(Routes.Splash.path()) {
                SplashScreen(
                    navigateToAuthOption = navigateToAuthOption,
                    navigateToHome = navigateToHome
                )
            }
            composable(Routes.AuthenticationOption.path()) {
                AuthOptionsScreen(
                    navigateToLogin = navigateToLogin,
                    navigateToRegister = navigateToRegister,
                )
            }
            composable(
                Routes.Login.path(),
                arguments = Routes.Login.arguments.map {
                    navArgument(it) {
                        type = NavType.StringType
                        defaultValue = NO_USERNAME
                    }
                }
            ) {
                val usernameArgument = it.arguments?.getString(USERNAME)
                val username = if (usernameArgument == NO_USERNAME) null else usernameArgument

                LoginScreen(
                    username = username,
                ) {
                    navController.popBackStack()
                }
            }
            composable(Routes.Register.path()) {
                RegisterScreen(
                    navigateToLogin = navigateToLogin,
                ) {
                    navController.popBackStack()
                }
            }
        }
    }
}