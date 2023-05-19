package com.pradyotprakash.themoviedbkmm.android.app.pages.discover.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pradyotprakash.themoviedbkmm.android.app.pages.discover.viewmodel.DiscoverViewModel

@Composable
fun DiscoverScreen(
    discoverViewModel: DiscoverViewModel = viewModel()
) {
    LaunchedEffect(key1 = true) {
        discoverViewModel.getDiscoverDetails()
    }

    val loading = discoverViewModel.loading.observeAsState(false)
    val movies = discoverViewModel.movies.observeAsState()
    val tvs = discoverViewModel.tvs.observeAsState()
    val movieError = discoverViewModel.movieError.observeAsState()
    val tvError = discoverViewModel.tvError.observeAsState()
}