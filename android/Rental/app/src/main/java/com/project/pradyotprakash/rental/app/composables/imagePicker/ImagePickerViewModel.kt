package com.project.pradyotprakash.rental.app.composables.imagePicker

import androidx.lifecycle.ViewModel
import com.project.pradyotprakash.rental.core.models.FieldStates
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImagePickerViewModel @Inject constructor() : ViewModel() {
    fun startImagePicker(imagePickerType: ImagePickerType, fieldStates: FieldStates) {

    }
}