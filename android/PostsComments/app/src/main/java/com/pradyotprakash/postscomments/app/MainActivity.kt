package com.pradyotprakash.postscomments.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pradyotprakash.postscomments.app.localization.Translation
import com.pradyotprakash.postscomments.app.pages.splash.view.SplashView
import com.pradyotprakash.postscomments.app.theme.PostsCommentsTheme
import com.pradyotprakash.postscomments.core.navigator.Navigator
import com.pradyotprakash.postscomments.core.navigator.Routes
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

            PostsCommentsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = Routes.Splash.route) {
                        composable(Routes.Splash.route) { SplashView() }
                    }
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