package app.pages.home.search.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.LoadingDialogComposable
import app.pages.home.search.viewModel.HomeSearchViewModel
import kotlinx.coroutines.launch
import utils.Localization

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeSearchScreen(
    homeSearchViewModel: HomeSearchViewModel = viewModel { HomeSearchViewModel() },
) {
    val homeSearchState by homeSearchViewModel.homeSearchState.collectAsState()

    val scope = rememberCoroutineScope()
    val tabPagerState = rememberPagerState(pageCount = { homeSearchState.tabsDetails.size })
    val snackbarHostState = remember { SnackbarHostState() }

    if (homeSearchState.showLoading) {
        LoadingDialogComposable()
    }
    homeSearchState.snackBarMessage?.let { message ->
        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = Localization.OKAY,
                duration = SnackbarDuration.Short
            )
            when (result) {
                SnackbarResult.ActionPerformed, SnackbarResult.Dismissed -> {
                    homeSearchViewModel.removeSnackBarMessage()
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            ScrollableTabRow(
                selectedTabIndex = tabPagerState.currentPage,
                edgePadding = 0.dp,
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                divider = {},
            ) {
                homeSearchState.tabsDetails.forEachIndexed { index, tab ->
                    Tab(
                        selected = tabPagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                tabPagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(text = tab)
                        },
                        unselectedContentColor = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
            HorizontalDivider()
            HorizontalPager(
                state = tabPagerState,
            ) {}
        }
    }
}