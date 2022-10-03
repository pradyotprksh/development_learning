package com.project.pradyotprakash.rental.app.composables.imagePicker

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.rental.core.models.FieldStates
import com.project.pradyotprakash.rental.domain.services.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImagePickerViewModel @Inject constructor(
    private val storageService: StorageService,
) : ViewModel() {
    private val _imageUploading = MutableLiveData(0.0f)
    val imageUploading: LiveData<Float>
        get() = _imageUploading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText

    fun startImagePicker(uris: List<Uri>, fieldStates: FieldStates, context: Context) {
        if (uris.isEmpty()) return
        uris.forEach {
            val bitmap = it.let {
                if (Build.VERSION.SDK_INT < 28) {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                } else {
                    val source = ImageDecoder
                        .createSource(context.contentResolver, it)
                    ImageDecoder.decodeBitmap(source)
                }
            }

            fieldStates.storageReference?.let { reference ->
                storageService.uploadFile(
                    bitmap,
                    reference,
                    onSuccess = { downloadUrl ->
                        _imageUploading.value = 0.0f
                        fieldStates.values.value = (fieldStates.values.value ?: emptyList()) + listOf(downloadUrl)
                    },
                    onFailure = { exception ->
                        _imageUploading.value = 0.0f
                        _errorText.value = exception.localizedMessage ?: "Something went wrong while uploading the image. Please try again."
                    },
                    onProgress = { progress ->
                        _imageUploading.value = progress
                    }
                )
            }
        }
    }

    fun updateErrorState(errorText: String? = null) {
        _errorText.value = errorText ?: ""
    }
}