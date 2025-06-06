package com.pradyotprakash.personalblog.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pradyotprakash.personalblog.app.navigation.AuthenticationOption
import com.pradyotprakash.personalblog.app.navigation.BlogDetails
import com.pradyotprakash.personalblog.app.navigation.BlogNew
import com.pradyotprakash.personalblog.app.navigation.BlogUpdate
import com.pradyotprakash.personalblog.app.navigation.Home
import com.pradyotprakash.personalblog.app.navigation.Splash
import com.pradyotprakash.personalblog.app.pages.authenticationOption.screen.AuthenticationOptionScreen
import com.pradyotprakash.personalblog.app.pages.home.screen.HomeScreen
import com.pradyotprakash.personalblog.app.pages.splash.screen.SplashScreen

@Composable
fun PersonalBlogApp(
    navController: NavHostController = rememberNavController(),
) {
    val navigateToAuthenticationOption = {
        navController.navigate(AuthenticationOption) {
            popUpTo(Splash) {
                inclusive = true
            }
        }
    }

    val navigateToHome = { admin: Boolean ->
        navController.navigate(
            Home(admin = admin),
        ) {
            popUpTo(AuthenticationOption) {
                inclusive = true
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Splash,
    ) {
        composable<Splash> {
            SplashScreen(
                navigateToAuthenticationOption = navigateToAuthenticationOption,
            )
        }
        composable<AuthenticationOption> {
            AuthenticationOptionScreen(
                navigateToHome = navigateToHome,
            )
        }
        composable<Home> {
            val home = it.toRoute<Home>()

            HomeScreen(
                isAdmin = home.admin,
            )
        }
        composable<BlogNew> { }
        composable<BlogDetails> { }
        composable<BlogUpdate> { }
    }
}