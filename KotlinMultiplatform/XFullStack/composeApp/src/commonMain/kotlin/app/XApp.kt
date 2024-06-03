package app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.composables.userAppBar.composable.UserAppBarComposable
import app.composables.userDrawer.composable.UserNavigationDrawerComposable
import app.navigation.Routes
import app.navigation.path
import app.navigation.showAppBar
import app.navigation.showBottomNavBar
import app.navigation.showDrawer
import app.navigation.showFloatingActionButton
import app.pages.auth.authOptions.screen.AuthOptionsScreen
import app.pages.auth.login.screen.LoginScreen
import app.pages.auth.register.screen.RegisterScreen
import app.pages.createTweet.screen.CreateTweetScreen
import app.pages.home.bottomBar.HomeBottomNavItems
import app.pages.home.home.screen.HomeScreen
import app.pages.splash.screen.SplashScreen
import kotlinx.coroutines.launch
import utils.Constants.ConstValues.NO_USERNAME
import utils.Constants.ConstValues.USERNAME_EMAIL_PHONE
import utils.extensions.popUpToTop

/**
 * XApp
 */
@Composable
fun XApp(
    navController: NavHostController = rememberNavController(),
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    ModalNavigationDrawer(
        drawerState = drawerState, drawerContent = {
            if (showDrawer(currentDestination?.route ?: "")) {
                UserNavigationDrawerComposable()
            }
        }, gesturesEnabled = showDrawer(currentDestination?.route ?: "")
    ) {
        Scaffold(
            topBar = {
                if (showAppBar(currentDestination?.route ?: "")) {
                    UserAppBarComposable(
                        toggleNavDrawer = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        },
                    )
                }
            },
            floatingActionButton = {
                if (showFloatingActionButton(currentDestination?.route ?: "")) {
                    FloatingActionButton(
                        onClick = {
                            if (currentDestination?.route == Routes.HomeMessages.path()) {
                                TODO()
                            } else {
                                navController.navigate(Routes.CreateTweet.path())
                            }
                        },
                    ) {
                        Icon(
                            imageVector = if (currentDestination?.route == Routes.HomeMessages.path()) Icons.AutoMirrored.Filled.Message else Icons.Default.Add,
                            contentDescription = (if (currentDestination?.route == Routes.HomeMessages.path()) Icons.AutoMirrored.Filled.Message else Icons.Default.Add).name,
                        )
                    }
                }
            },
            bottomBar = {
                if (showBottomNavBar(currentDestination?.route ?: "")) {
                    NavigationBar {
                        listOf(
                            HomeBottomNavItems.Home,
                            HomeBottomNavItems.Search,
                            HomeBottomNavItems.Grok,
                            HomeBottomNavItems.Communities,
                            HomeBottomNavItems.Notification,
                            HomeBottomNavItems.Messages,
                        ).forEach { screen ->
                            NavigationBarItem(
                                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                label = {},
                                icon = {
                                    Icon(
                                        imageVector = screen.icon,
                                        contentDescription = screen.icon.name
                                    )
                                },
                                onClick = {
                                    navController.navigate(screen.route) {
                                        popUpTo(
                                            navController.graph.findStartDestination().route ?: ""
                                        ) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                            )
                        }
                    }
                }
            },
        ) { innerPadding ->
            NavHost(
                navController = navController,
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                startDestination = Routes.Splash.path(),
            ) {
                val navigateToAuthOption = {
                    navController.navigate(Routes.AuthenticationOption.path()) {
                        popUpTo(Routes.Splash.path()) {
                            inclusive = true
                        }
                    }
                }
                val navigateToHome = {
                    navController.navigate(Routes.Home.path()) {
                        popUpToTop(navController)
                    }
                }
                val navigateToLogin = { value: String ->
                    navController.navigate(
                        "${Routes.Login.route}$value",
                    )
                }
                val navigateToRegister = {
                    navController.navigate(Routes.Register.path())
                }

                composable(Routes.Splash.path()) {
                    SplashScreen(
                        navigateToAuthOption = navigateToAuthOption, navigateToHome = navigateToHome
                    )
                }
                composable(Routes.AuthenticationOption.path()) {
                    AuthOptionsScreen(
                        navigateToLogin = navigateToLogin,
                        navigateToRegister = navigateToRegister,
                    )
                }
                composable(
                    Routes.Login.path(),
                    arguments = Routes.Login.arguments.map {
                        navArgument(it) {
                            type = NavType.StringType
                            defaultValue = NO_USERNAME
                        }
                    },
                ) {
                    val usernameEmailPhoneArgument = it.arguments?.getString(USERNAME_EMAIL_PHONE)
                    val usernameEmailPhone =
                        if (usernameEmailPhoneArgument == NO_USERNAME) null else usernameEmailPhoneArgument

                    LoginScreen(
                        usernameEmailPhoneValue = usernameEmailPhone,
                        navigateToHome = navigateToHome,
                    ) {
                        navController.popBackStack()
                    }
                }
                composable(Routes.Register.path()) {
                    RegisterScreen(
                        navigateToLogin = {
                            navController.popBackStack()
                            navigateToLogin(it)
                        },
                    ) {
                        navController.popBackStack()
                    }
                }
                composable(Routes.Home.path()) {
                    HomeScreen()
                }
                composable(Routes.CreateTweet.path()) {
                    CreateTweetScreen {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}