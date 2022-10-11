package com.project.pradyotprakash.demo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.pradyotprakash.demo.app.pages.splash.view.SplashView
import com.project.pradyotprakash.demo.app.theme.DemoTheme
import com.project.pradyotprakash.demo.core.navigation.Navigator
import com.project.pradyotprakash.demo.core.navigation.Routes
import com.project.pradyotprakash.demo.core.navigation.path
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

            DemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController = navController, startDestination = Routes.Splash.route) {
                        composable(Routes.Splash.path()) { SplashView(hiltViewModel()) }
                    }
                }
            }
        }
    }

    private fun setAppBasicRequirements() {
        navigationChangeListener()
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
