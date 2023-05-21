package com.pradyotprakash.findingfalcone.app

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pradyotprakash.findingfalcone.app.localization.Translation
import com.pradyotprakash.findingfalcone.app.pages.result.view.ResultView
import com.pradyotprakash.findingfalcone.app.pages.selector.view.SelectorView
import com.pradyotprakash.findingfalcone.app.pages.splash.view.SplashView
import com.pradyotprakash.findingfalcone.app.theme.FindingFalconeTheme
import com.pradyotprakash.findingfalcone.core.navigation.Navigator
import com.pradyotprakash.findingfalcone.core.navigation.Routes
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * The main view of the application, the first view which gets created and
 * handle all the main level work related to the ui.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setAppBasicRequirements()

        setContent {
            navController = rememberNavController()

            FindingFalconeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = Routes.Splash.route) {
                        composable(Routes.Splash.route) { SplashView() }
                        composable(Routes.Selector.route) { SelectorView() }
                        composable(
                            Routes.Result.route,
                            arguments = listOf(
                                navArgument("planets") { type = NavType.StringType },
                                navArgument("vehicles") { type = NavType.StringType },
                                navArgument("timeTaken") { type = NavType.IntType },
                            )
                        ) {
                            val arguments = requireNotNull(it.arguments)
                            val planets = arguments.getString("planets")?.split(",")?.toTypedArray() ?: emptyArray()
                            val vehicles = arguments.getString("vehicles")?.split(",")?.toTypedArray() ?: emptyArray()
                            val timeTaken = arguments.getInt("timeTaken") ?: 0

                            ResultView(
                                planets = planets.toList(),
                                vehicles = vehicles.toList(),
                                timeTaken = timeTaken,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun setAppBasicRequirements() {
        // Fetch and save the translation values at the starting of the application
        setApplicationOrientation()
        Translation.updateLocalizationMap(context = this)
        navigationChangeListener()
    }

    private fun setApplicationOrientation() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    /**
     * A navigation change listener which starts observing the navigation change.
     */
    private fun navigationChangeListener() {
        navigator.navigateAction.observe(this) {
            it(navController)
        }
    }
}
