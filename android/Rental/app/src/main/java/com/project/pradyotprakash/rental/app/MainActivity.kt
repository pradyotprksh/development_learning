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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.pradyotprakash.rental.app.localization.Translation
import com.project.pradyotprakash.rental.app.pages.error.view.ErrorScreen
import com.project.pradyotprakash.rental.app.pages.home.view.HomeScreen
import com.project.pradyotprakash.rental.app.pages.information.view.InformationScreen
import com.project.pradyotprakash.rental.app.pages.options.view.OptionsView
import com.project.pradyotprakash.rental.app.pages.property.add.view.PropertyScreen
import com.project.pradyotprakash.rental.app.pages.property.details.view.PropertyDetailsScreen
import com.project.pradyotprakash.rental.app.pages.search.view.SearchView
import com.project.pradyotprakash.rental.app.pages.splash.view.SplashView
import com.project.pradyotprakash.rental.app.pages.user.view.UserView
import com.project.pradyotprakash.rental.app.pages.welcome.view.WelcomeScreen
import com.project.pradyotprakash.rental.app.theme.RentalTheme
import com.project.pradyotprakash.rental.app.utils.ErrorScreenArguments
import com.project.pradyotprakash.rental.app.utils.InformationScreenArguments
import com.project.pradyotprakash.rental.app.utils.PropertyDetailsArguments
import com.project.pradyotprakash.rental.app.utils.UserDetailsArguments
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

        setAppBasicRequirements()

        setContent {
            navController = rememberNavController()

            RentalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = Routes.Splash.route) {
                        composable(Routes.Splash.path()) { SplashView() }
                        composable(Routes.Home.path()) { HomeScreen() }
                        composable(Routes.Option.path()) { OptionsView() }
                        composable(Routes.Property.path()) { PropertyScreen() }
                        composable(Routes.Welcome.path()) { WelcomeScreen() }
                        composable(Routes.Search.path()) { SearchView() }
                        composable(
                            Routes.PropertyDetails.path(),
                            arguments = Routes.PropertyDetails.arguments.map {
                                navArgument(it) { type = NavType.StringType }
                            }
                        ) {
                            val propertyId = it.arguments?.getString(
                                PropertyDetailsArguments.propertyId
                            )

                            propertyId?.let {
                                if (propertyId.isEmpty()) {
                                    GoToErrorScreen()
                                } else {
                                    PropertyDetailsScreen(
                                        propertyId,
                                    )
                                }
                            } ?: kotlin.run {
                                GoToErrorScreen()
                            }
                        }
                        composable(
                            Routes.UserDetails.path(),
                            arguments = Routes.UserDetails.arguments.map {
                                navArgument(it) { type = NavType.StringType }
                            }
                        ) {
                            val userId = it.arguments?.getString(
                                UserDetailsArguments.userId
                            )

                            userId?.let {
                                if (userId.isEmpty()) {
                                    GoToErrorScreen()
                                } else {
                                    UserView(
                                        userId = userId,
                                    )
                                }
                            } ?: kotlin.run {
                                GoToErrorScreen()
                            }
                        }
                        composable(
                            Routes.Error.path(),
                            arguments = Routes.Error.arguments.map {
                                navArgument(it) { type = NavType.StringType }
                            }
                        ) {
                            GoToErrorScreen(
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
                            val onlyPreview = it.arguments?.getString(
                                InformationScreenArguments.onlyPreview
                            )?.toBoolean()
                            val allowBackOption = it.arguments?.getString(
                                InformationScreenArguments.allowBackOption
                            )?.toBoolean()
                            val firstTimeAddingDetails = it.arguments?.getString(
                                InformationScreenArguments.firstTimeAddingDetails
                            )?.toBoolean()

                            onlyPreview?.let {
                                allowBackOption?.let {
                                    firstTimeAddingDetails?.let {
                                        InformationScreen(
                                            onlyPreview = onlyPreview,
                                            allowBackOption = allowBackOption,
                                            firstTimeAddingDetails = firstTimeAddingDetails,
                                        )
                                    } ?: kotlin.run {
                                        GoToErrorScreen()
                                    }
                                } ?: kotlin.run {
                                    GoToErrorScreen()
                                }
                            } ?: kotlin.run {
                                GoToErrorScreen()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setAppBasicRequirements() {
        // Fetch and save the translation values at the starting of the application
        // TODO: Handle the language change, currently only fetching the default one
        Translation.updateLocalizationMap(context = this)

        navigationChangeListener()
        startAuthStateListener()
    }

    @Composable
    private fun GoToErrorScreen(
        title: String = "",
        subtitle: String = "",
        description: String = ""
    ) {
        ErrorScreen(
            title = title,
            subtitle = subtitle,
            description = description,
        )
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
                    mainViewModel.logoutUser()
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
