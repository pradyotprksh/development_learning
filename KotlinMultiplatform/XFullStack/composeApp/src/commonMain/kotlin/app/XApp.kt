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
import app.pages.home.screen.HomeScreen
import app.pages.splash.screen.SplashScreen
import utils.Constants.ConstValues.NO_USERNAME
import utils.Constants.ConstValues.USERNAME_EMAIL_PHONE
import utils.extensions.popUpToTop

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
                    popUpTo(Routes.Splash.path()) {
                        inclusive = true
                    }
                }
            }
            val navigateToHome = {
                navController.navigate(Routes.Home.path()) {
                    popUpToTop(navController)
                }
            }
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
                val usernameEmailPhoneArgument = it.arguments?.getString(USERNAME_EMAIL_PHONE)
                val usernameEmailPhone =
                    if (usernameEmailPhoneArgument == NO_USERNAME) null else usernameEmailPhoneArgument

                LoginScreen(
                    usernameEmailPhoneValue = usernameEmailPhone,
                    navigateToHome = navigateToHome,
                ) {
                    navController.popBackStack()
                }
            }
            composable(Routes.Register.path()) {
                RegisterScreen(
                    navigateToLogin = {
                        navController.popBackStack()
                        navigateToLogin(it)
                    },
                ) {
                    navController.popBackStack()
                }
            }
            composable(Routes.Home.path()) {
                HomeScreen()
            }
        }
    }
}