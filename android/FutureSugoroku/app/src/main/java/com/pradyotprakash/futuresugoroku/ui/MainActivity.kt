package com.pradyotprakash.futuresugoroku.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pradyotprakash.futuresugoroku.ui.navigation.Routes
import com.pradyotprakash.futuresugoroku.ui.pages.game.screen.GameScreen
import com.pradyotprakash.futuresugoroku.ui.pages.home.screen.HomeScreen
import com.pradyotprakash.futuresugoroku.ui.theme.FutureSugorokuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            val goToGameScreen = {
                navController.navigate(Routes.Game)
            }

            FutureSugorokuTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Home,
                        modifier = Modifier.padding(
                            paddingValues = innerPadding
                        )
                    ) {
                        composable<Routes.Home> {
                            HomeScreen(
                                goToGameScreen = goToGameScreen,
                            )
                        }
                        composable<Routes.Game> {
                            GameScreen(
                                goBack = {
                                    navController.popBackStack()
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}