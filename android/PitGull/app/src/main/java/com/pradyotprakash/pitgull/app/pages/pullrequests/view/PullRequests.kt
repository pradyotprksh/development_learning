package com.pradyotprakash.pitgull.app.pages.pullrequests.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradyotprakash.pitgull.app.composables.PageStateComposable
import com.pradyotprakash.pitgull.app.localizations.TR
import com.pradyotprakash.pitgull.app.pages.pullrequests.view.composables.PrDetails
import com.pradyotprakash.pitgull.app.pages.pullrequests.viewmodel.PullRequestsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullRequests(
    pullRequestsViewModel: PullRequestsViewModel = hiltViewModel(),
) {
    val loading = pullRequestsViewModel.loading.observeAsState(false)
    val error = pullRequestsViewModel.error.observeAsState("")
    val closedPr = pullRequestsViewModel.closedPr.observeAsState()

    PageStateComposable(
        isLoading = loading.value,
        errorMessage = error.value,
        dismissErrorAlert = pullRequestsViewModel::updateErrorState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = TR.appName)
                    }
                )
            },
            floatingActionButton = {
                if (!loading.value) {
                    FloatingActionButton(
                        onClick = pullRequestsViewModel::getClosedPullRequests
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = Icons.Default.Refresh.name
                        )
                    }
                }
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(
                    bottom = paddingValues.calculateBottomPadding(),
                    top = paddingValues.calculateTopPadding(),
                    start = 15.dp,
                    end = 15.dp
                )
            ) {
                closedPr.value?.let { prs ->
                    items(prs.size) {
                        val prDetails = prs[it]
                        Card(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                            ) {
                                PrDetails(prDetails = prDetails)
                            }
                        }
                    }
                }
            }
        }
    }
}