package com.project.pradyotprakash.rental.app.composables.imagePicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.project.pradyotprakash.rental.core.models.FieldStates
import com.project.pradyotprakash.rental.core.permissions.PermissionHandler

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ImagePicker(
    imagePickerViewModel: ImagePickerViewModel,
    field: FieldStates,
    imagePickerType: ImagePickerType = ImagePickerType.SingleImagePicker,
) {
    val externalStorageState = PermissionHandler.checkForReadExternalStorage()

    Column(
        modifier = Modifier
            .padding(all = 10.dp)
    ) {
        Text(
            text = field.label,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow {
            field.values.value?.let { images ->
                items(images) {
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }

            item {
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(
                    onClick = {
                        PermissionHandler.permissionInitiatorWithStateCheck(
                            state = externalStorageState,
                            askForPermission = {
                                externalStorageState.launchPermissionRequest()
                            },
                            onGranted = {
                                imagePickerViewModel.startImagePicker(imagePickerType, field)
                            }
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = Icons.Default.Add.name,
                    )
                }
            }
        }
    }
}