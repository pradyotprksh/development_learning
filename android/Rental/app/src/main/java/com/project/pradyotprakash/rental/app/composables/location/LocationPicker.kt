package com.project.pradyotprakash.rental.app.composables.location

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.pradyotprakash.rental.core.models.FieldStates

@Composable
fun LocationPicker(
    locationPickerViewModel: LocationPickerViewModel = hiltViewModel(),
    field: FieldStates,
    closeAction: () -> Unit = {},
) {

}