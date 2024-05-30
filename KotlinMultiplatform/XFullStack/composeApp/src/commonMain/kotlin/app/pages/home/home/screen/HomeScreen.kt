package app.pages.home.home.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import app.pages.home.home.screen.composables.ForYouTweetsComposable
import app.pages.home.home.state.TweetActions
import app.pages.home.home.viewModel.HomeViewModel
import kotlinx.coroutines.launch
import utils.Localization

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel { HomeViewModel() },
) {
    LaunchedEffect(homeViewModel) {
        homeViewModel.initialSetup()
    }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val homeScreenState by homeViewModel.homeScreenState.collectAsState()
    val tabPagerState = rememberPagerState(pageCount = { homeScreenState.tabsDetails.size })
    homeScreenState.snackBarMessage?.let { message ->
        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = Localization.OKAY,
                duration = SnackbarDuration.Short
            )
            when (result) {
                SnackbarResult.ActionPerformed, SnackbarResult.Dismissed -> {
                    homeViewModel.removeSnackBarMessage()
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        Column {
            TabRow(
                selectedTabIndex = tabPagerState.currentPage,
                divider = { HorizontalDivider() },
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
            ) {
                homeScreenState.tabsDetails.forEachIndexed { index, tab ->
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
            HorizontalPager(
                state = tabPagerState,
            ) { page ->
                when (page) {
                    0 -> {
                        ForYouTweetsComposable(
                            tweets = homeScreenState.tweets,
                            showLoading = homeScreenState.showLoading,
                            tweetActions = TweetActions(
                                profileImageClick = {},
                                onTweetClick = {},
                                onBookmark = {},
                                onShare = {},
                                onPollSelection = {},
                            )
                        )
                    }

                    1 -> {}
                }
            }
        }
    }
}