package com.pradyotprakash.findingfalcone.app.pages.result.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.findingfalcone.app.composables.PageStateComposable
import com.pradyotprakash.findingfalcone.app.localization.TR
import com.pradyotprakash.findingfalcone.app.pages.result.viewmodel.ResultViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultView(
    resultViewModel: ResultViewModel = hiltViewModel(),
    planets: List<String>,
    vehicles: List<String>,
    timeTaken: Int,
) {
    LaunchedEffect(key1 = true) {
        resultViewModel.findFalcone(
            planets,
            vehicles,
        )
    }

    val loading by resultViewModel.loading.observeAsState(false)
    val error by resultViewModel.error.observeAsState("")
    val findResult by resultViewModel.findResult.observeAsState()

    PageStateComposable(
        isLoading = loading,
        errorMessage = error,
        retryOperation = resultViewModel::retry,
        dismissErrorAlert = resultViewModel::updateErrorState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = TR.appName,
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = Color.Black,
                            ),
                        )
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
            },
        ) { padding ->
            findResult?.let {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = padding.calculateTopPadding(),
                            bottom = padding.calculateBottomPadding()
                        ),
                ) {
                    if (it.isFound) {
                        Text(text = TR.successMessage)
                    } else {
                        Text(text = TR.errorMessage)
                    }
                    Box(modifier = Modifier.height(10.dp))
                    Text(
                        text = "${TR.timeTaken} $timeTaken",
                    )
                    if (it.isFound) {
                        Text(
                            text = "${TR.planetFound} ${it.planet_name}",
                        )
                    }
                    Box(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            resultViewModel.retry()
                        }
                    ) {
                        Text(text = TR.startAgain)
                    }
                }
            }
        }
    }
}