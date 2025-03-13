package com.pradyotprakash.posedetection.cameraScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.mlkit.vision.pose.PoseLandmark

@Composable
fun rememberPoseState(): SnapshotStateList<PoseLandmark> {
    return remember { mutableStateListOf() }
}