package com.project.pradyotprakash.rental.app.composables.imagePicker

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.project.pradyotprakash.rental.app.composables.AnimatedProgressBar
import com.project.pradyotprakash.rental.app.composables.NetworkImageComposable
import com.project.pradyotprakash.rental.app.composables.PageStateComposable
import com.project.pradyotprakash.rental.core.models.FieldStates
import com.project.pradyotprakash.rental.core.permissions.PermissionHandler

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ImagePicker(
    imagePickerViewModel: ImagePickerViewModel,
    field: FieldStates,
    imagePickerType: ImagePickerType = ImagePickerType.SingleImagePicker,
) {
    val uploadProgress = imagePickerViewModel.imageUploading.observeAsState(0.0f)
    val error = imagePickerViewModel.error.observeAsState("")
    val uploadedImages = field.values.observeAsState(emptyList())

    val context = LocalContext.current

    val externalStorageState = PermissionHandler.checkForReadExternalStorage()
    val launcherMultiple = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
        imagePickerViewModel.startImagePicker(it, field, context)
    }
    val launcherSingle = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            imagePickerViewModel.startImagePicker(
                listOf(uri), field, context
            )
        } ?: run {
            imagePickerViewModel.startImagePicker(
                emptyList(), field, context
            )
        }
    }

    PageStateComposable(
        errorMessage = error.value,
        dismissErrorAlert = imagePickerViewModel::updateErrorState
    ) {
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
                items(uploadedImages.value) { images ->
                    NetworkImageComposable(imageUrl = images, size = 50.dp, cornerSize = 5.dp)
                    Spacer(modifier = Modifier.width(5.dp))
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
                                    when (imagePickerType) {
                                        ImagePickerType.MultipleImagePicker -> launcherMultiple.launch("image/*")
                                        ImagePickerType.SingleImagePicker -> launcherSingle.launch("image/*")
                                    }
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

            uploadProgress.value.run {
                Spacer(modifier = Modifier.height(10.dp))
                if (this > 0.0f) {
                    AnimatedProgressBar(indicatorProgress = this)
                }
            }
        }
    }
}