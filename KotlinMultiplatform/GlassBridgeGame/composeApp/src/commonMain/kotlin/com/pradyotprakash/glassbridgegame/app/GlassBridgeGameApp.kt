package com.pradyotprakash.glassbridgegame.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pradyotprakash.glassbridgegame.app.navigation.Splash

@Composable
fun GlassBridgeGameApp(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController,
        startDestination = Splash,
    ) {
        composable<Splash> {}
    }
}