package com.pradyotprakash.posedetection.cameraScreen

import android.content.Context
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.mlkit.common.model.CustomRemoteModel
import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetector
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions
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

@OptIn(ExperimentalGetImage::class)
private fun processImageProxy(
    imageProxy: ImageProxy,
    poseLandmarks: SnapshotStateList<PoseLandmark>,
    imageWidth: MutableState<Int>,
    imageHeight: MutableState<Int>,
) {
    val mediaImage = imageProxy.image ?: return
    val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

    val options = PoseDetectorOptions.Builder()
        .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
        .build()

    val poseDetector = PoseDetection.getClient(options)

    poseDetector.process(image)
        .addOnSuccessListener { pose ->
            poseLandmarks.clear()
            poseLandmarks.addAll(pose.allPoseLandmarks)
            imageWidth.value = mediaImage.width
            imageHeight.value = mediaImage.height
        }
        .addOnFailureListener { e ->
            Log.e("PoseDetection", "Detection failed", e)
        }
        .addOnCompleteListener {
            imageProxy.close()
        }
}

@Composable
fun rememberPoseState(): SnapshotStateList<PoseLandmark> {
    return remember { mutableStateListOf() }
}

@Composable
fun PoseOverlay(
    poseLandmarks: List<PoseLandmark>,
    imageWidth: Int,
    imageHeight: Int,
    canvasWidth: Float,
    canvasHeight: Float,
) {
    val scaleX = canvasWidth / imageWidth
    val scaleY = canvasHeight / imageHeight

    Canvas(modifier = Modifier.fillMaxSize()) {
        for (landmark in poseLandmarks) {
            val adjustedX = landmark.position.x * scaleX
            val adjustedY = landmark.position.y * scaleY

            drawCircle(
                color = Color.Red,
                radius = 8f,
                center = Offset(adjustedX, adjustedY)
            )
        }

        val connections = listOf(
            PoseLandmark.LEFT_EYE to PoseLandmark.RIGHT_EYE,
            PoseLandmark.LEFT_EYE to PoseLandmark.LEFT_EAR,
            PoseLandmark.RIGHT_EYE to PoseLandmark.RIGHT_EAR,
            PoseLandmark.NOSE to PoseLandmark.LEFT_EYE,
            PoseLandmark.NOSE to PoseLandmark.RIGHT_EYE,
            PoseLandmark.NOSE to PoseLandmark.LEFT_MOUTH,
            PoseLandmark.NOSE to PoseLandmark.RIGHT_MOUTH,

            // **Torso**
            PoseLandmark.LEFT_SHOULDER to PoseLandmark.RIGHT_SHOULDER,
            PoseLandmark.LEFT_SHOULDER to PoseLandmark.LEFT_HIP,
            PoseLandmark.RIGHT_SHOULDER to PoseLandmark.RIGHT_HIP,
            PoseLandmark.LEFT_HIP to PoseLandmark.RIGHT_HIP,

            // **Arms**
            PoseLandmark.LEFT_SHOULDER to PoseLandmark.LEFT_ELBOW,
            PoseLandmark.LEFT_ELBOW to PoseLandmark.LEFT_WRIST,
            PoseLandmark.RIGHT_SHOULDER to PoseLandmark.RIGHT_ELBOW,
            PoseLandmark.RIGHT_ELBOW to PoseLandmark.RIGHT_WRIST,

            // **Hands & Fingers (Basic)**
            PoseLandmark.LEFT_WRIST to PoseLandmark.LEFT_INDEX,
            PoseLandmark.LEFT_WRIST to PoseLandmark.LEFT_PINKY,
            PoseLandmark.LEFT_WRIST to PoseLandmark.LEFT_THUMB,
            PoseLandmark.RIGHT_WRIST to PoseLandmark.RIGHT_INDEX,
            PoseLandmark.RIGHT_WRIST to PoseLandmark.RIGHT_PINKY,
            PoseLandmark.RIGHT_WRIST to PoseLandmark.RIGHT_THUMB,

            // **Legs**
            PoseLandmark.LEFT_HIP to PoseLandmark.LEFT_KNEE,
            PoseLandmark.LEFT_KNEE to PoseLandmark.LEFT_ANKLE,
            PoseLandmark.RIGHT_HIP to PoseLandmark.RIGHT_KNEE,
            PoseLandmark.RIGHT_KNEE to PoseLandmark.RIGHT_ANKLE,

            // **Feet**
            PoseLandmark.LEFT_ANKLE to PoseLandmark.LEFT_HEEL,
            PoseLandmark.LEFT_ANKLE to PoseLandmark.LEFT_FOOT_INDEX,
            PoseLandmark.RIGHT_ANKLE to PoseLandmark.RIGHT_HEEL,
            PoseLandmark.RIGHT_ANKLE to PoseLandmark.RIGHT_FOOT_INDEX
        )

        for ((start, end) in connections) {
            val startLandmark = poseLandmarks.find { it.landmarkType == start }
            val endLandmark = poseLandmarks.find { it.landmarkType == end }

            if (startLandmark != null && endLandmark != null) {
                val startX = startLandmark.position.x * scaleX
                val startY = startLandmark.position.y * scaleY
                val endX = endLandmark.position.x * scaleX
                val endY = endLandmark.position.y * scaleY

                drawLine(
                    color = Color.Green,
                    strokeWidth = 4f,
                    start = Offset(startX, startY),
                    end = Offset(endX, endY)
                )
            }
        }
    }
}
