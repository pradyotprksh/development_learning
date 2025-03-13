package com.pradyotprakash.posedetection

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions

@OptIn(ExperimentalGetImage::class)
fun processImageProxy(
    imageProxy: ImageProxy,
    poseLandmarks: SnapshotStateList<PoseLandmark>,
    imageWidth: MutableState<Int>,
    imageHeight: MutableState<Int>,
) {
    val mediaImage = imageProxy.image ?: return
    val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

    val options = PoseDetectorOptions.Builder()
        .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
        .setPreferredHardwareConfigs(PoseDetectorOptions.CPU_GPU)
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