package com.project.pradyotprakash.rental.app.composables.location.view

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.pradyotprakash.rental.app.composables.location.viewmodel.LocationPickerViewModel
import com.project.pradyotprakash.rental.core.models.FieldStates

@Composable
fun LocationPicker(
    locationPickerViewModel: LocationPickerViewModel = hiltViewModel(),
    field: FieldStates,
    closeAction: () -> Unit = {},
) {

}