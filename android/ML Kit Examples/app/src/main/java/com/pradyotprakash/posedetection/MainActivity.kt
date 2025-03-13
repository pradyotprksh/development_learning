package com.pradyotprakash.posedetection

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.pradyotprakash.posedetection.cameraScreen.CameraScreen
import com.pradyotprakash.posedetection.ui.theme.PoseDetectionTheme

class MainActivity : ComponentActivity() {
    private val cameraPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                setCameraPreview()
            } else {
                openPermissionSettings()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        checkCameraPermission()
        enableEdgeToEdge()
    }

    private fun checkCameraPermission() {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA,
            ),
                -> {
                setCameraPreview()
            }

            else -> {
                cameraPermissionRequest.launch(android.Manifest.permission.CAMERA)
            }
        }
    }

    private fun setCameraPreview() {
        setContent {
            PoseDetectionTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    CameraScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun openPermissionSettings() {
        Intent(ACTION_APPLICATION_DETAILS_SETTINGS).also {
            val uri = Uri.fromParts("package", packageName, null)
            it.data = uri
            startActivity(it)
        }
    }
}