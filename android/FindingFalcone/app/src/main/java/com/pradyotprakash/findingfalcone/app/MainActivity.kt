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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pradyotprakash.findingfalcone.app.localization.Translation
import com.pradyotprakash.findingfalcone.app.pages.selector.view.SelectorView
import com.pradyotprakash.findingfalcone.app.pages.splash.view.SplashView
import com.pradyotprakash.findingfalcone.app.theme.FindingFalconeTheme
import com.pradyotprakash.findingfalcone.core.navigation.Navigator
import com.pradyotprakash.findingfalcone.core.navigation.Routes
import com.pradyotprakash.findingfalcone.core.navigation.path
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
                        composable(Routes.Splash.path()) { SplashView() }
                        composable(Routes.Selector.path()) { SelectorView() }
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
