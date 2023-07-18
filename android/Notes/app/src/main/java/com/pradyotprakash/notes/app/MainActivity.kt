package com.pradyotprakash.notes.app

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.pradyotprakash.notes.app.localization.Translation
import com.pradyotprakash.notes.app.pages.login.view.LoginView
import com.pradyotprakash.notes.app.pages.signup.view.SignUpView
import com.pradyotprakash.notes.app.pages.splash.view.SplashView
import com.pradyotprakash.notes.app.theme.NotesTheme
import com.pradyotprakash.notes.core.navigation.Navigator
import com.pradyotprakash.notes.core.navigation.Routes
import com.pradyotprakash.notes.core.navigation.path
import com.pradyotprakash.notes.core.services.AppStartService
import com.pradyotprakash.notes.core.services.ToastService
import com.pradyotprakash.notes.core.worker.ToastWorker
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    private lateinit var appStartService: AppStartService
    private var isAppStartServiceBound: Boolean = false

    private val toastWorker: WorkRequest =
        OneTimeWorkRequestBuilder<ToastWorker>()
            .build()

    private lateinit var navController: NavHostController

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = p1 as AppStartService.LocalBinder
            appStartService = binder.getService()
            isAppStartServiceBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isAppStartServiceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            setupNavigationChangeListener()

            NotesTheme {
                NavHost(navController = navController, startDestination = Routes.Splash.route) {
                    composable(Routes.Splash.path()) { SplashView() }
                    composable(Routes.SignUpUser.path()) { SignUpView() }
                    composable(Routes.LoginUser.path()) { LoginView() }
                }
            }
        }

        setAppBasicRequirements()
    }

    private fun setupNavigationChangeListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.route != Routes.Splash.path()) {
                unbindService(connection)
            }
        }
    }

    override fun onStart() {
        startAllServices()
        startWorkers()

        super.onStart()
    }

    private fun startWorkers() {
        WorkManager
            .getInstance(this)
            .enqueue(toastWorker)
    }

    private fun setAppBasicRequirements() {
        Translation.updateLocalizationMap(context = this)

        navigationChangeListener()
    }

    private fun startAllServices() {
        startService(Intent(this, ToastService::class.java))
        Intent(this, AppStartService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    private fun navigationChangeListener() {
        navigator.navigateAction.observe(this) {
            it(navController)
        }
    }
}
