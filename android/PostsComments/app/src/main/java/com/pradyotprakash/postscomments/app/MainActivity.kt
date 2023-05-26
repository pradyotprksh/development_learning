package com.pradyotprakash.postscomments.app

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
import com.pradyotprakash.postscomments.app.localization.Translation
import com.pradyotprakash.postscomments.app.pages.login.view.LoginView
import com.pradyotprakash.postscomments.app.pages.post.view.PostView
import com.pradyotprakash.postscomments.app.pages.posts.view.PostsView
import com.pradyotprakash.postscomments.app.pages.signUp.view.SignUpView
import com.pradyotprakash.postscomments.app.pages.splash.view.SplashView
import com.pradyotprakash.postscomments.app.theme.PostsCommentsTheme
import com.pradyotprakash.postscomments.core.auth.AuthState
import com.pradyotprakash.postscomments.core.auth.AuthStateListener
import com.pradyotprakash.postscomments.core.navigator.Navigator
import com.pradyotprakash.postscomments.core.navigator.Routes
import com.pradyotprakash.postscomments.core.navigator.path
import com.pradyotprakash.postscomments.core.utils.PostArguments
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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

            PostsCommentsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Splash.path()
                    ) {
                        composable(Routes.Splash.path()) { SplashView() }
                        composable(Routes.Login.path()) { LoginView() }
                        composable(Routes.SignUp.path()) { SignUpView() }
                        composable(Routes.Posts.path()) { PostsView() }
                        composable(
                            Routes.Post.path(),
                            arguments = Routes.Post.arguments.map {
                                navArgument(it) { type = NavType.StringType }
                            }
                        ) {
                            val postType = it.arguments?.getString(
                                PostArguments.postType
                            ) ?: PostArguments.createPost
                            val postId = it.arguments?.getString(
                                PostArguments.postId
                            ) ?: PostArguments.defaultPostId
                            PostView(
                                postType = postType,
                                postId = postId,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun setAppBasicRequirements() {
        Translation.updateLocalizationMap(context = this)

        startAuthStateListener()
        navigationChangeListener()
    }

    private fun startAuthStateListener() {
        authStateListener.authState.observe(this) {
            when (it) {
                AuthState.Authenticated -> {
                    navigator.navigate { navController ->
                        navController.popBackStack()
                        navController.navigate(Routes.Posts.path())
                    }
                }

                AuthState.Unauthenticated -> {
                    navigator.navigate { navController ->
                        navController.popBackStack()
                        navController.navigate(Routes.Login.path())
                    }
                }

                else -> {}
            }
        }
    }

    private fun navigationChangeListener() {
        navigator.navigateAction.observe(this) {
            it(navController)
        }
    }
}