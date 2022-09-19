package com.project.pradyotprakash.rental.app.pages.home.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.pradyotprakash.rental.app.composables.PageStateComposable
import com.project.pradyotprakash.rental.app.pages.home.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val loading = homeViewModel.loading.observeAsState(false)
    val error = homeViewModel.error.observeAsState("")
    val userDetails = homeViewModel.userDetails.observeAsState()

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = homeViewModel::updateErrorState
    ) {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "Welcome, ${userDetails.value?.first_name ?: ""}",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(),
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = homeViewModel::goToAddPropertyScreen) {
                    Text(
                        text = "Add A Property",
                        modifier = Modifier.padding(
                            all = 15.dp
                        )
                    )
                }
            }
        ) {

        }
    }
}