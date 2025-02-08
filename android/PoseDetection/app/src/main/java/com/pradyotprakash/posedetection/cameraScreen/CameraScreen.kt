package com.pradyotprakash.posedetection.cameraScreen

import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pradyotprakash.posedetection.processImageProxy
import java.util.concurrent.Executors

@Composable
fun CameraScreen(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val previewView = remember { PreviewView(context) }
    val poseLandmarks = rememberPoseState()
    val imageWidth = remember { mutableIntStateOf(1) }
    val imageHeight = remember { mutableIntStateOf(1) }

    Box(
        modifier = modifier,
    ) {
        AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize()) { view ->
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener({
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                val androidPreview = Preview.Builder().build().also {
                    it.surfaceProvider = view.surfaceProvider
                }

                val imageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()

                imageAnalysis.setAnalyzer(Executors.newSingleThreadExecutor()) { imageProxy ->
                    processImageProxy(
                        imageProxy,
                        poseLandmarks,
                        imageWidth,
                        imageHeight,
                    )
                }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        androidPreview,
                        imageAnalysis
                    )
                } catch (exc: Exception) {
                    Log.e("CameraX", "Use case binding failed", exc)
                }
            }, ContextCompat.getMainExecutor(context))
        }

        with(density) {
            val screenWidth = configuration.screenWidthDp.dp.toPx()
            val screenHeight = configuration.screenHeightDp.dp.toPx()

            PoseOverlay(
                poseLandmarks,
                imageWidth.intValue,
                imageHeight.intValue,
                screenWidth,
                screenHeight
            )
        }
    }
}
