package com.pradyotprakash.libraryowner.app.pages.details.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.libraryowner.app.composables.PageStateComposable
import com.pradyotprakash.libraryowner.app.localization.TR
import com.pradyotprakash.libraryowner.app.pages.details.viewmodel.DetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsView(detailsViewModel: DetailsViewModel = hiltViewModel()) {
    val loading = detailsViewModel.loading.observeAsState(false)
    val error = detailsViewModel.error.observeAsState("")

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = detailsViewModel::updateErrorState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = TR.detailsTitle)
                    },
                    navigationIcon = {}
                )
            }
        ) { paddingValues ->
            LazyColumn(
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                    start = 15.dp,
                    end = 15.dp
                ),
            ) {
                item {
                    Text(
                        text = "Your Details",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }
    }
}