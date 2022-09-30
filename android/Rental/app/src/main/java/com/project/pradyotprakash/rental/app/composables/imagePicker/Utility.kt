package com.project.pradyotprakash.rental.app.composables.imagePicker

sealed class ImagePickerType {
    object MultipleImagePicker : ImagePickerType()
    object SingleImagePicker : ImagePickerType()
}