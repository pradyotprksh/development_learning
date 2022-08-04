package com.project.pradyotprakash.rental.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.pradyotprakash.rental.app.localization.Translation
import com.project.pradyotprakash.rental.app.pages.options.view.OptionsView
import com.project.pradyotprakash.rental.app.pages.splash.view.SplashView
import com.project.pradyotprakash.rental.app.pages.welcome.view.WelcomeScreen
import com.project.pradyotprakash.rental.app.theme.RentalTheme
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.app.utils.WelcomeScreenArguments
import com.project.pradyotprakash.rental.core.auth.AuthState
import com.project.pradyotprakash.rental.core.auth.AuthStateListener
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.core.navigation.path
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The main view of the application, the first view which gets created and
 * handle all the main level work related to the ui.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var authStateListener: AuthStateListener

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Fetch and save the translation values at the starting of the application
        // TODO: Handle the language change, currently only fetching the default one
        Translation.updateLocalizationMap(context = this)

        setContent {
            navController = rememberNavController()

            RentalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = Routes.Splash.route) {
                        composable(Routes.Splash.path()) { SplashView() }
                        composable(Routes.Option.path()) { OptionsView(hiltViewModel()) }
                        composable(
                            Routes.Welcome.path(),
                            arguments = Routes.Welcome.arguments.map {
                                navArgument(it) { type = NavType.StringType }
                            }
                        ) {
                            WelcomeScreen(
                                hiltViewModel(),
                                UserType.valueOf(
                                    it.arguments?.getString(
                                        WelcomeScreenArguments.userType
                                    ) ?: ""
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        navigationChangeListener()
        startAuthStateListener()
    }

    /**
     * Start the authentication state listener. This will initiate the process for
     * listening to the auth state changed.
     *
     * This will also help in navigation, which will navigate to the required screen.
     */
    private fun startAuthStateListener() {
        lifecycleScope.launch {
            authStateListener.authState.collect { authState ->
                when (authState) {
                    AuthState.Authenticated -> {}
                    AuthState.Unauthenticated -> {
                        navigator.navigate { navController ->
                            navController.navigate(Routes.Splash.path())
                        }
                        delay(2000)
                        navigator.navigate { navController ->
                            navController.navigate(Routes.Option.path()) {
                                popUpTo(Routes.Splash.path()) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }
        }
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
