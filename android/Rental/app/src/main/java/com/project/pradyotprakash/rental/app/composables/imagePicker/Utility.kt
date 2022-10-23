package com.project.pradyotprakash.rental.app.composables.imagePicker

/**
 * A sealed class for image picker type.
 */
sealed class ImagePickerType {
    /**
     * A type for multiple image selection
     */
    object MultipleImagePicker : ImagePickerType()

    /**
     * A type for single image selection
     */
    object SingleImagePicker : ImagePickerType()
}