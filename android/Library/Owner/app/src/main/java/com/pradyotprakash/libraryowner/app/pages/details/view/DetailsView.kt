package com.pradyotprakash.libraryowner.app.pages.details.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.libraryowner.app.composables.PageStateComposable
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.DetailsViewModel

@Composable
fun DetailsView(detailsViewModel: DetailsViewModel = hiltViewModel()) {
    val loading = detailsViewModel.loading.observeAsState(false)
    val error = detailsViewModel.error.observeAsState("")

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = detailsViewModel::updateErrorState
    ) {

    }
}