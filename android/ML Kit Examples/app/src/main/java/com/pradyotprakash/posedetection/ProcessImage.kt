package com.pradyotprakash.posedetection

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions

val whichToRun = WhichToRun.ImageLabelling

enum class WhichToRun {
    PoseDetection,
    ImageLabelling,
}

@OptIn(ExperimentalGetImage::class)
fun processImageProxy(
    whichToRun: WhichToRun,
    imageProxy: ImageProxy,
    poseLandmarks: SnapshotStateList<PoseLandmark>,
    imageLabel: SnapshotStateList<ImageLabel>,
    imageWidth: MutableState<Int>,
    imageHeight: MutableState<Int>,
) {
    val mediaImage = imageProxy.image ?: return
    val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

    when (whichToRun) {
        WhichToRun.PoseDetection -> {
            val poseDetectorOptions = PoseDetectorOptions.Builder()
                .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
                .setPreferredHardwareConfigs(PoseDetectorOptions.CPU_GPU)
                .build()

            val poseDetector = PoseDetection.getClient(poseDetectorOptions)

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

        WhichToRun.ImageLabelling -> {
            val imageLabelerOptions = ImageLabelerOptions.Builder()
                .build()

            val labeler = ImageLabeling.getClient(imageLabelerOptions)

            labeler.process(image)
                .addOnSuccessListener { label ->
                    imageLabel.addAll(label)
                }
                .addOnFailureListener { e ->
                    Log.e("ImageLabeler", "Detection failed", e)
                }
                .addOnSuccessListener {
                    imageProxy.close()
                }
        }
    }
}