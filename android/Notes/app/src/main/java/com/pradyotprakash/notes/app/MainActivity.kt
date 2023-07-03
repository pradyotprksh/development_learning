package com.pradyotprakash.notes.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pradyotprakash.notes.app.localization.Translation
import com.pradyotprakash.notes.app.pages.splash.view.SplashView
import com.pradyotprakash.notes.app.theme.NotesTheme
import com.pradyotprakash.notes.core.navigation.Navigator
import com.pradyotprakash.notes.core.navigation.Routes
import com.pradyotprakash.notes.core.navigation.path
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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

            NotesTheme {
                NavHost(navController = navController, startDestination = Routes.Splash.route) {
                    composable(Routes.Splash.path()) { SplashView() }
                }
            }
        }
    }

    private fun setAppBasicRequirements() {
        Translation.updateLocalizationMap(context = this)

        navigationChangeListener()
    }

    private fun navigationChangeListener() {
        navigator.navigateAction.observe(this) {
            it(navController)
        }
    }
}
