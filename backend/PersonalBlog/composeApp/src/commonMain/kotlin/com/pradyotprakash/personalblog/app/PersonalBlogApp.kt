package com.pradyotprakash.personalblog.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pradyotprakash.personalblog.app.navigation.AuthenticationOption
import com.pradyotprakash.personalblog.app.navigation.BlogDetails
import com.pradyotprakash.personalblog.app.navigation.BlogNew
import com.pradyotprakash.personalblog.app.navigation.BlogUpdate
import com.pradyotprakash.personalblog.app.navigation.Home
import com.pradyotprakash.personalblog.app.navigation.Splash

@Composable
fun PersonalBlogApp(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Splash,
    ) {
        composable<Splash> {  }
        composable<AuthenticationOption> {  }
        composable<Home> {  }
        composable<BlogNew> {  }
        composable<BlogDetails> {  }
        composable<BlogUpdate> {  }
    }
}