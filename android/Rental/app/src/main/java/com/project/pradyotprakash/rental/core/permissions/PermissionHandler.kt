package com.project.pradyotprakash.rental.core.permissions

import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
object PermissionHandler {
    fun permissionInitiatorWithStateCheck(
        state: PermissionState,
        askForPermission: () -> Unit,
        onDenied: () -> Unit = {},
        onPermanentlyDenied: () -> Unit = {},
        onGranted: () -> Unit = {},
    ) {
        if (state.status != PermissionStatus.Granted) {
            askForPermission()
        }
        when (state.status) {
            is PermissionStatus.Denied -> {
                if (state.status.shouldShowRationale) {
                    onDenied()
                } else {
                    onPermanentlyDenied()
                }
            }
            is PermissionStatus.Granted -> onGranted()
        }
    }

    @Composable
    fun checkForReadExternalStorage() =
        rememberPermissionState(permission = android.Manifest.permission.READ_EXTERNAL_STORAGE)
    
    @Composable
    fun checkForAccurateLocation() =
        rememberPermissionState(permission = android.Manifest.permission.ACCESS_FINE_LOCATION)

    @Composable
    fun checkForApproximateLocation() =
        rememberPermissionState(permission = android.Manifest.permission.ACCESS_COARSE_LOCATION)
}