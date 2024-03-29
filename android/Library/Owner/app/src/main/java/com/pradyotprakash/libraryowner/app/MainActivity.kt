package com.pradyotprakash.libraryowner.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pradyotprakash.libraryowner.app.localization.TR
import com.pradyotprakash.libraryowner.app.localization.Translation
import com.pradyotprakash.libraryowner.app.pages.details.view.DetailsView
import com.pradyotprakash.libraryowner.app.pages.splash.view.SplashView
import com.pradyotprakash.libraryowner.app.pages.welcome.view.WelcomeView
import com.pradyotprakash.libraryowner.app.routes.Routes
import com.pradyotprakash.libraryowner.app.routes.path
import com.pradyotprakash.libraryowner.app.theme.LibraryOwnerTheme
import com.pradyotprakash.libraryowner.core.navigation.Navigator
import com.pradyotprakash.libraryowner.core.toast.Toaster
import com.pradyotprakash.libraryowner.device.network.InternetConnectionCallback
import com.pradyotprakash.libraryowner.device.network.InternetConnectionObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), InternetConnectionCallback {
    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var toaster: Toaster

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setAppBasicRequirements()

        setContent {
            navController = rememberNavController()

            LibraryOwnerTheme(
                dynamicColor = false
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Splash.path()
                    ) {
                        composable(Routes.Splash.path()) { SplashView() }
                        composable(Routes.Welcome.path()) { WelcomeView() }
                        composable(Routes.Details.path()) { DetailsView() }
                    }
                }
            }
        }
    }

    private fun setAppBasicRequirements() {
        lifecycleScope.launch {
            mainViewModel.currentLanguage.collect {
                Translation.updateLocalizationMap(lan_key = it, context = this@MainActivity)
            }
        }

        setupNetworkListener()
        navigationChangeListener()
        toastMessageListener()
    }

    private fun toastMessageListener() {
        toaster.toastActions.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupNetworkListener() {
        InternetConnectionObserver
            .instance(this)
            .setCallback(this)
            .register()
    }

    override fun onDestroy() {
        super.onDestroy()
        InternetConnectionObserver.unRegister()
    }

    private fun navigationChangeListener() {
        navigator.navigateAction.observe(this) {
            it(navController)
        }
    }

    override fun onConnected() {
        toaster.toast(TR.internetAvailable)
    }

    override fun onDisconnected() {
        toaster.toast(TR.noInternet)
    }
}
