package com.project.pradyotprakash.rental.app.pages.property.details.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.project.pradyotprakash.rental.app.composables.PageStateComposable
import com.project.pradyotprakash.rental.app.pages.property.details.viewmodel.PropertyDetailsViewModel

@Composable
fun PropertyDetailsScreen(
    propertyDetailsViewModel: PropertyDetailsViewModel,
) {
    val loading = propertyDetailsViewModel.loading.observeAsState(false)
    val error = propertyDetailsViewModel.error.observeAsState("")

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = propertyDetailsViewModel::updateErrorState
    ) {}
}