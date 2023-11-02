package com.pradyotprakash.libraryowner.app.pages.imagePicker.view

import android.Manifest
import androidx.compose.runtime.Composable
import com.huhx.picker.constant.AssetPickerConfig
import com.huhx.picker.constant.RequestType
import com.huhx.picker.data.AssetInfo
import com.huhx.picker.data.PickerPermissions
import com.huhx.picker.view.AssetPicker

@Composable
fun ImagePickerView(
    maxAssets: Int = 1,
    onPicked: (List<AssetInfo>) -> Unit,
    onClose: (List<AssetInfo>) -> Unit,
) {
    PickerPermissions(
        permissions = listOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
    ) {
        AssetPicker(
            assetPickerConfig = AssetPickerConfig(
                gridCount = 3,
                requestType = RequestType.COMMON,
                maxAssets = maxAssets,
            ),
            onPicked = onPicked,
            onClose = onClose
        )
    }
}