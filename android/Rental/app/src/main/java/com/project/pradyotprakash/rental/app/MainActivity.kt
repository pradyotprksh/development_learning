package com.project.pradyotprakash.rental.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.pradyotprakash.rental.app.localization.Translation
import com.project.pradyotprakash.rental.app.pages.error.view.ErrorScreen
import com.project.pradyotprakash.rental.app.pages.error.viewmodel.ErrorViewModel
import com.project.pradyotprakash.rental.app.pages.home.view.HomeScreen
import com.project.pradyotprakash.rental.app.pages.information.view.InformationScreen
import com.project.pradyotprakash.rental.app.pages.information.viewmodel.InformationViewModel
import com.project.pradyotprakash.rental.app.pages.options.view.OptionsView
import com.project.pradyotprakash.rental.app.pages.splash.view.SplashView
import com.project.pradyotprakash.rental.app.pages.welcome.view.WelcomeScreen
import com.project.pradyotprakash.rental.app.pages.welcome.viewmodel.WelcomeViewModel
import com.project.pradyotprakash.rental.app.theme.RentalTheme
import com.project.pradyotprakash.rental.app.utils.ErrorScreenArguments
import com.project.pradyotprakash.rental.app.utils.InformationScreenArguments
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.app.utils.WelcomeScreenArguments
import com.project.pradyotprakash.rental.core.auth.AuthState
import com.project.pradyotprakash.rental.core.auth.AuthStateListener
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.core.navigation.path
import dagger.hilt.android.AndroidEntryPoint
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
                        composable(Routes.Splash.path()) { SplashView(hiltViewModel()) }
                        composable(Routes.Home.path()) { HomeScreen(hiltViewModel()) }
                        composable(Routes.Option.path()) { OptionsView(hiltViewModel()) }
                        composable(
                            Routes.Welcome.path(),
                            arguments = Routes.Welcome.arguments.map {
                                navArgument(it) { type = NavType.StringType }
                            }
                        ) {
                            val userType = it.arguments?.getString(
                                WelcomeScreenArguments.userType
                            )

                            userType?.let { type ->
                                if (type.isEmpty()) {
                                    goToErrorScreen()
                                } else {
                                    WelcomeScreen(
                                        hiltViewModel<WelcomeViewModel>().also { viewModel ->
                                            viewModel.start(
                                                UserType.valueOf(type)
                                            )
                                        }
                                    )
                                }
                            }
                        }
                        composable(
                            Routes.Error.path(),
                            arguments = Routes.Error.arguments.map {
                                navArgument(it) { type = NavType.StringType }
                            }
                        ) {
                            goToErrorScreen(
                                title = it.arguments?.getString(
                                    ErrorScreenArguments.title
                                ) ?: "",
                                subtitle = it.arguments?.getString(
                                    ErrorScreenArguments.subtitle
                                ) ?: "",
                                description = it.arguments?.getString(
                                    ErrorScreenArguments.description
                                ) ?: ""
                            )
                        }
                        composable(
                            Routes.Information.path(),
                            arguments = Routes.Information.arguments.map {
                                navArgument(it) { type = NavType.StringType }
                            }
                        ) {
                            val userType = it.arguments?.getString(
                                WelcomeScreenArguments.userType
                            )
                            val onlyPreview = it.arguments?.getString(
                                InformationScreenArguments.onlyPreview
                            )?.toBoolean()
                            val allowBackOption = it.arguments?.getString(
                                InformationScreenArguments.allowBackOption
                            )?.toBoolean()

                            userType?.let { type ->
                                if (type.isEmpty()) {
                                    goToErrorScreen()
                                } else {
                                    onlyPreview?.let {
                                        allowBackOption?.let {
                                            InformationScreen(
                                                hiltViewModel<InformationViewModel>().also { viewModel ->
                                                    viewModel.start(
                                                        UserType.valueOf(userType),
                                                        onlyPreview,
                                                        allowBackOption,
                                                    )
                                                }
                                            )
                                        } ?: kotlin.run {
                                            goToErrorScreen()
                                        }
                                    } ?: kotlin.run {
                                        goToErrorScreen()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun goToErrorScreen(
        title: String = "",
        subtitle: String = "",
        description: String = ""
    ) {
        ErrorScreen(
            hiltViewModel<ErrorViewModel>().also { viewModel ->
                viewModel.start(
                    title = title,
                    subtitle = subtitle,
                    description = description,
                )
            }
        )
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
        authStateListener.authState.observe(this) {
            when (it) {
                AuthState.Authenticated -> {
                    navigator.navigate { navController ->
                        navController.navigate(Routes.Home.path()) {
                            popUpTo(Routes.Splash.path()) {
                                inclusive = true
                            }
                        }
                    }
                }
                AuthState.Unauthenticated -> {
                    navigator.navigate { navController ->
                        navController.navigate(Routes.Option.path()) {
                            popUpTo(Routes.Splash.path()) {
                                inclusive = true
                            }
                        }
                    }
                }
                else -> {}
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
