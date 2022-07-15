package com.project.pradyotprakash.twitter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.pradyotprakash.twitter.navigator.Navigator
import com.project.pradyotprakash.twitter.navigator.Routes
import com.project.pradyotprakash.twitter.splash.splash.SplashView
import com.project.pradyotprakash.twitter.twiteme.theme.Twiteme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
/**
 * The main view of the application, this is the first view which is loaded by the app
 * and all the common functionality related to UI will be handled here.
 */
class MainView : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    @Inject lateinit var navigator: Navigator

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()

            Twiteme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController = navController, startDestination = Routes.Splash.route) {
                        composable(Routes.Splash.route) { SplashView(hiltViewModel()) }
                        composable(Routes.AuthOption.route) { SplashView(hiltViewModel()) }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkForAuthChangeListener()
        checkForNetworkConnectivityListener()
        navigationChangeListener()
        showDialogListener()
        showToastMessageListener()
        checkForAppUpdate()
        initiateNativeAppReview()
    }

    /**
     * Start the app review check for the user.
     */
    private fun initiateNativeAppReview() {

    }

    /**
     * Check if a new update is available, if available show the option to update
     */
    private fun checkForAppUpdate() {

    }

    /**
     * If needed to show a toast message this listener will help in that. This way
     * all the toasts will be handled in one place.
     */
    private fun showToastMessageListener() {

    }

    /**
     * A dialog listener to see if a new dialog has to be shown or not.
     */
    private fun showDialogListener() {

    }

    /**
     * If needed to change the page or navigate to a new screen this listener will help in
     * to do so.
     */
    private fun navigationChangeListener() {
        navigator.navigateAction.observe(this) {
            it(navController)
        }
    }

    /**
     * A network connectivity check listener, if network is not available then will help
     * in let the user know that the app is offline.
     */
    private fun checkForNetworkConnectivityListener() {

    }

    /**
     * A authentication change listener which will help in know what the current auth state of
     * the application is, authenticated or not.
     */
    private fun checkForAuthChangeListener() {

    }
}
