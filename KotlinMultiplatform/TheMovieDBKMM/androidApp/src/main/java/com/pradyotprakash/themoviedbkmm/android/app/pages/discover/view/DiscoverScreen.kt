package com.pradyotprakash.themoviedbkmm.android.app.pages.discover.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pradyotprakash.themoviedbkmm.android.app.composables.HeaderComposable
import com.pradyotprakash.themoviedbkmm.android.app.pages.discover.composables.DiscoverDetails
import com.pradyotprakash.themoviedbkmm.android.app.pages.discover.viewmodel.DiscoverViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiscoverScreen(
    discoverViewModel: DiscoverViewModel = viewModel()
) {
    LaunchedEffect(key1 = true) {
        discoverViewModel.getDiscoverDetails()
    }

    val loading by discoverViewModel.loading.observeAsState(false)
    val movies by discoverViewModel.movies.observeAsState()
    val tvs by discoverViewModel.tvs.observeAsState()
    val movieError by discoverViewModel.movieError.observeAsState()
    val tvError by discoverViewModel.tvError.observeAsState()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier.fillMaxSize()
            ) {
                if (movieError != null) {
                    item {
                        Text(text = movieError ?: "")
                    }
                }

                if (movies != null) {
                    stickyHeader {
                        HeaderComposable(title = "Movies")
                    }

                    items(movies?.results ?: emptyList()) { movie ->
                        DiscoverDetails(title = movie.title, image = movie.imageUrl)
                    }
                }

                if (tvError != null) {
                    item {
                        Text(text = tvError ?: "")
                    }
                }

                if (tvs != null) {
                    stickyHeader {
                        HeaderComposable(title = "TVs")
                    }

                    items(tvs?.results ?: emptyList()) { tv ->
                        DiscoverDetails(title = tv.name, image = tv.imageUrl)
                    }
                }
            }

            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}