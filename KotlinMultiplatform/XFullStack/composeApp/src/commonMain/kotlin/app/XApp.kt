package app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
import app.navigation.isMessageRoute
import app.navigation.isSearchRoute
import app.navigation.path
import app.navigation.showAppBar
import app.navigation.showBottomNavBar
import app.navigation.showDrawer
import app.navigation.showFloatingActionButton
import app.navigation.showSearchBar
import app.navigation.showSettingOption
import app.pages.auth.authOptions.screen.AuthOptionsScreen
import app.pages.auth.login.screen.LoginScreen
import app.pages.auth.register.screen.RegisterScreen
import app.pages.bookmarks.screen.BookmarksScreen
import app.pages.createTweet.screen.CreateTweetScreen
import app.pages.directMessage.screen.DirectMessageScreen
import app.pages.home.bottomBar.HomeBottomNavItems
import app.pages.home.grok.screen.HomeGrokScreen
import app.pages.home.home.screen.HomeScreen
import app.pages.home.message.screen.HomeMessageScreen
import app.pages.home.search.screen.HomeSearchScreen
import app.pages.profileDetails.screen.ProfileDetailsScreen
import app.pages.splash.screen.SplashScreen
import app.pages.tweetDetails.screen.TweetDetailsScreen
import kotlinx.coroutines.launch
import utils.Constants.ConstValues.CHAT_ID
import utils.Constants.ConstValues.IS_REPLY
import utils.Constants.ConstValues.IS_RETWEET
import utils.Constants.ConstValues.NO_NAV_VALUE
import utils.Constants.ConstValues.PARENT_TWEET_ID
import utils.Constants.ConstValues.TWEET_ID
import utils.Constants.ConstValues.USERNAME_EMAIL_PHONE
import utils.Constants.ConstValues.USER_ID
import utils.Localization
import utils.extensions.popUpToTop

/**
 * XApp
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun XApp(
    navController: NavHostController = rememberNavController(),
    xAppViewModel: XAppViewModel = viewModel { XAppViewModel() },
) {
    LaunchedEffect(Unit) {
        xAppViewModel.initSetup()
    }

    val windowSizeClass = calculateWindowSizeClass()
    val isPhone =
        windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact || windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium

    val snackbarHostState = remember { SnackbarHostState() }
    val xAppState by xAppViewModel.xAppState.collectAsState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    xAppState.snackBarMessage?.let { message ->
        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = Localization.OKAY,
                duration = SnackbarDuration.Short
            )
            when (result) {
                SnackbarResult.ActionPerformed, SnackbarResult.Dismissed -> {
                    xAppViewModel.removeSnackBarMessage()
                }
            }
        }
    }

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
    val navigateToCreateTweet = { value: String, isRetweet: Boolean, isReply: Boolean ->
        navController.navigate(
            "${Routes.CreateTweet.route}${value}/${isRetweet}/${isReply}",
        )
    }
    val navigateToTweetDetails = { value: String ->
        navController.navigate(
            "${Routes.TweetDetails.route}$value",
        )
    }
    val navigateToProfileDetails = { value: String ->
        navController.navigate(
            "${Routes.ProfileDetails.route}$value",
        )
    }
    val navigateToBookmarks = {
        navController.navigate(Routes.Bookmarks.path())
    }
    val navigateToDirectMessage = { userId: String, chatId: String ->
        navController.navigate(
            "${Routes.DirectMessage.route}${userId}/${chatId}",
        )
    }
    val drawerOpenClose = {
        scope.launch {
            drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState, drawerContent = {
            AnimatedVisibility(
                showDrawer(currentDestination?.route ?: "")
            ) {
                UserNavigationDrawerComposable(
                    openProfileDetails = { id ->
                        drawerOpenClose()
                        navigateToProfileDetails(id)
                    },
                    openBookmarkPage = {
                        drawerOpenClose()
                        navigateToBookmarks()
                    },
                )
            }
        }, gesturesEnabled = showDrawer(currentDestination?.route ?: "")
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            topBar = {
                AnimatedVisibility(
                    showAppBar(currentDestination?.route ?: "")
                ) {
                    UserAppBarComposable(
                        toggleNavDrawer = {
                            drawerOpenClose()
                        },
                        showSettingButton = showSettingOption(currentDestination?.route ?: ""),
                        title = if (showSearchBar(currentDestination?.route ?: "")) {
                            {
                                FilledTonalButton(
                                    onClick = {},
                                    modifier = Modifier.fillMaxWidth().height(30.dp),
                                ) {
                                    if (isSearchRoute(currentDestination?.route ?: "")) {
                                        Text(
                                            Localization.format(
                                                Localization.SEARCH,
                                                Localization.APP_NAME,
                                            ),
                                            style = MaterialTheme.typography.bodySmall,
                                        )
                                    } else if (isMessageRoute(currentDestination?.route ?: "")) {
                                        Text(
                                            Localization.format(
                                                Localization.SEARCH,
                                                Localization.DIRECT_MESSAGES,
                                            ),
                                            style = MaterialTheme.typography.bodySmall,
                                        )
                                    }
                                }
                            }
                        } else {
                            null
                        },
                    )
                }
            },
            floatingActionButton = {
                AnimatedVisibility(
                    showFloatingActionButton(currentDestination?.route ?: "")
                ) {
                    FloatingActionButton(
                        onClick = {
                            if (currentDestination?.route == Routes.HomeMessages.path()) {
                                TODO()
                            } else {
                                navigateToCreateTweet(NO_NAV_VALUE, false, false)
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
                AnimatedVisibility(
                    showBottomNavBar(currentDestination?.route ?: "")
                ) {
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
                composable(Routes.Splash.path()) {
                    SplashScreen(
                        navigateToAuthOption = navigateToAuthOption, navigateToHome = navigateToHome
                    )
                }
                composable(Routes.AuthenticationOption.path()) {
                    AuthOptionsScreen(
                        isPhone = isPhone,
                        navigateToLogin = navigateToLogin,
                        navigateToRegister = navigateToRegister,
                    )
                }
                composable(
                    Routes.Login.path(),
                    arguments = Routes.Login.arguments.map {
                        navArgument(it) {
                            type = NavType.StringType
                            defaultValue = NO_NAV_VALUE
                        }
                    },
                ) {
                    val usernameEmailPhoneArgument = it.arguments?.getString(USERNAME_EMAIL_PHONE)
                    val usernameEmailPhone =
                        if (usernameEmailPhoneArgument == NO_NAV_VALUE) null else usernameEmailPhoneArgument

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
                    HomeScreen(
                        openCreateTweetWithParentId = { id, isRetweet, isReply ->
                            navigateToCreateTweet(id, isRetweet, isReply)
                        },
                        openTweetDetails = {
                            navigateToTweetDetails(it)
                        },
                        openProfileDetails = {
                            navigateToProfileDetails(it)
                        },
                    )
                }
                composable(Routes.HomeSearch.path()) {
                    HomeSearchScreen()
                }
                composable(Routes.HomeGrok.path()) {
                    HomeGrokScreen(
                        isPhone = isPhone,
                    )
                }
                composable(Routes.HomeCommunities.path()) { }
                composable(Routes.HomeNotifications.path()) { }
                composable(Routes.HomeMessages.path()) {
                    HomeMessageScreen(
                        openDirectMessage = { id, chatId ->
                            navigateToDirectMessage(id ?: NO_NAV_VALUE, chatId)
                        }
                    )
                }
                composable(Routes.Bookmarks.path()) {
                    BookmarksScreen(
                        onNavigateBack = {
                            navController.popBackStack()
                        },
                        openCreateTweetWithParentId = { id, isRetweet, isReply ->
                            navigateToCreateTweet(id, isRetweet, isReply)
                        },
                        openTweetDetails = { id ->
                            navigateToTweetDetails(id)
                        },
                        openProfileDetails = { id ->
                            navigateToProfileDetails(id)
                        },
                    )
                }
                composable(
                    Routes.CreateTweet.path(),
                    arguments = Routes.CreateTweet.arguments.map {
                        navArgument(it) {
                            type = NavType.StringType
                            defaultValue = NO_NAV_VALUE
                        }
                    },
                ) {
                    val parentTweetIdArgument = it.arguments?.getString(PARENT_TWEET_ID)
                    val isRetweet =
                        it.arguments?.getString(IS_RETWEET)?.toBooleanStrictOrNull() ?: false
                    val isReply =
                        it.arguments?.getString(IS_REPLY)?.toBooleanStrictOrNull() ?: false

                    val parentTweetId =
                        if (parentTweetIdArgument == NO_NAV_VALUE) null else parentTweetIdArgument

                    CreateTweetScreen(
                        parentTweetId = parentTweetId,
                        isRetweet = isRetweet,
                        isReply = isReply,
                    ) {
                        navController.popBackStack()
                    }
                }
                composable(
                    Routes.TweetDetails.path(),
                    arguments = Routes.TweetDetails.arguments.map {
                        navArgument(it) {
                            type = NavType.StringType
                            defaultValue = NO_NAV_VALUE
                        }
                    },
                ) {
                    val tweetIdArgument = it.arguments?.getString(TWEET_ID)
                    val tweetId = if (tweetIdArgument == NO_NAV_VALUE) null else tweetIdArgument

                    TweetDetailsScreen(
                        tweetId = tweetId ?: "",
                        onNavigateBack = {
                            navController.popBackStack()
                        },
                        openCreateTweetWithParentId = { id, isRetweet, isReply ->
                            navigateToCreateTweet(id, isRetweet, isReply)
                        },
                        openTweetDetails = { id ->
                            navigateToTweetDetails(id)
                        },
                        openProfileDetails = { id ->
                            navigateToProfileDetails(id)
                        },
                    )
                }
                composable(
                    Routes.ProfileDetails.path(),
                    arguments = Routes.ProfileDetails.arguments.map {
                        navArgument(it) {
                            type = NavType.StringType
                            defaultValue = NO_NAV_VALUE
                        }
                    },
                ) {
                    val userIdArgument = it.arguments?.getString(USER_ID)
                    val userId = if (userIdArgument == NO_NAV_VALUE) null else userIdArgument

                    ProfileDetailsScreen(
                        userId = userId,
                        onNavigateBack = {
                            navController.popBackStack()
                        },
                        openCreateTweetWithParentId = { id, isRetweet, isReply ->
                            navigateToCreateTweet(id, isRetweet, isReply)
                        },
                        openTweetDetails = { id ->
                            navigateToTweetDetails(id)
                        },
                        openProfileDetails = { id ->
                            navigateToProfileDetails(id)
                        },
                        openDirectMessage = { id, chatId ->
                            navigateToDirectMessage(id, chatId)
                        }
                    )
                }
                composable(
                    Routes.DirectMessage.path(),
                    arguments = Routes.DirectMessage.arguments.map {
                        navArgument(it) {
                            type = NavType.StringType
                            defaultValue = NO_NAV_VALUE
                        }
                    },
                ) {
                    val userIdArgument = it.arguments?.getString(USER_ID)
                    val userId =
                        if (userIdArgument == NO_NAV_VALUE) null else userIdArgument
                    val chatIdArgument = it.arguments?.getString(CHAT_ID)
                    val chatId =
                        if (chatIdArgument == NO_NAV_VALUE) null else chatIdArgument

                    DirectMessageScreen(
                        userId = userId,
                        chatId = chatId,
                        openProfileDetails = { id ->
                            navigateToProfileDetails(id)
                        },
                        onNavigateBack = {
                            navController.popBackStack()
                        },
                    )
                }
            }
        }
    }
}