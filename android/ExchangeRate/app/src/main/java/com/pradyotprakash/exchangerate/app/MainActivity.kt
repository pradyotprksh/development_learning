package com.pradyotprakash.exchangerate.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pradyotprakash.exchangerate.app.localization.Translation
import com.pradyotprakash.exchangerate.app.pages.home.view.HomeView
import com.pradyotprakash.exchangerate.app.pages.splash.view.SplashView
import com.pradyotprakash.exchangerate.app.routes.Routes
import com.pradyotprakash.exchangerate.app.theme.ExchangeRateTheme
import com.pradyotprakash.exchangerate.core.navigation.Navigator
import com.pradyotprakash.exchangerate.core.toast.Toaster
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var toaster: Toaster

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setAppBasicRequirements()

        setContent {
            navController = rememberNavController()

            ExchangeRateTheme(
                dynamicColor = false
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Splash.route
                    ) {
                        composable(Routes.Splash.route) { SplashView() }
                        composable(Routes.Home.route) { HomeView() }
                    }
                }
            }
        }
    }

    private fun setAppBasicRequirements() {
        Translation.updateLocalizationMap(context = this)

        navigationChangeListener()
        toastMessageListener()
    }

    private fun toastMessageListener() {
        toaster.toastActions.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigationChangeListener() {
        navigator.navigateAction.observe(this) {
            it(navController)
        }
    }
}