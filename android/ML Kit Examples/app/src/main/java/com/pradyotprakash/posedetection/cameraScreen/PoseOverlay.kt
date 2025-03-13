package com.pradyotprakash.posedetection.cameraScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.google.mlkit.vision.pose.PoseLandmark

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
