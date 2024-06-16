package app.pages.home.home.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import app.pages.home.home.screen.composables.ForYouFollowingTweetsComposable
import app.pages.home.home.state.TweetActions
import app.pages.home.home.viewModel.HomeViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import utils.Localization

@OptIn(ExperimentalFoundationApi::class, FlowPreview::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel { HomeViewModel() },
    openCreateTweetWithParentId: (String, Boolean, Boolean) -> Unit,
    openTweetDetails: (String) -> Unit,
    openProfileDetails: (String) -> Unit,
) {
    LaunchedEffect(homeViewModel) {
        homeViewModel.initialSetup()
    }

    val homeScreenState by homeViewModel.homeScreenState.collectAsState()
    val scope = rememberCoroutineScope()
    val forYouLazyListState = rememberLazyListState(
        initialFirstVisibleItemIndex = homeViewModel.getForYouScrollPosition()
    )
    val followingLazyListState = rememberLazyListState(
        initialFirstVisibleItemIndex = homeViewModel.getFollowingScrollPosition()
    )
    val snackbarHostState = remember { SnackbarHostState() }
    val shouldStartPaginate = remember {
        derivedStateOf {
            homeScreenState.canPaginate && ((forYouLazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -9) >= (forYouLazyListState.layoutInfo.totalItemsCount - 6) || (followingLazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -9) >= (followingLazyListState.layoutInfo.totalItemsCount - 6))
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value) homeViewModel.updateTweetsPage()
    }

    LaunchedEffect(forYouLazyListState) {
        snapshotFlow {
            forYouLazyListState.firstVisibleItemIndex
        }.debounce(500).collectLatest { index ->
            homeViewModel.updateForYouScrollPosition(index)
        }
    }

    LaunchedEffect(followingLazyListState) {
        snapshotFlow {
            followingLazyListState.firstVisibleItemIndex
        }.debounce(500).collectLatest { index ->
            homeViewModel.updateFollowingScrollPosition(index)
        }
    }

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
                        ForYouFollowingTweetsComposable(
                            tweets = homeScreenState.forYouTweets,
                            showLoading = homeScreenState.showLoading,
                            tweetsLazyColumnState = forYouLazyListState,
                            tweetActions = TweetActions(
                                profileImageClick = { id ->
                                    openProfileDetails(id)
                                },
                                onTweetClick = {
                                    openTweetDetails(it)
                                },
                                onBookmark = { tweetId ->
                                    homeViewModel.bookmarkUpdate(tweetId)
                                },
                                onShare = {},
                                onPollSelection = { tweetId, optionId ->
                                    homeViewModel.updatePollOption(tweetId, optionId)
                                },
                                onComment = {},
                                onViews = {},
                                onLike = { homeViewModel.onLikeTweet(it) },
                                onRepost = { openCreateTweetWithParentId(it, true, false) },
                            ),
                            tweetVisibility = {
                                homeViewModel.updateViewForTweet(it)
                            },
                        )
                    }

                    1 -> {
                        ForYouFollowingTweetsComposable(
                            tweets = homeScreenState.followingTweets,
                            showLoading = homeScreenState.showLoading,
                            tweetsLazyColumnState = followingLazyListState,
                            tweetActions = TweetActions(
                                profileImageClick = { id ->
                                    openProfileDetails(id)
                                },
                                onTweetClick = {
                                    openTweetDetails(it)
                                },
                                onBookmark = { tweetId ->
                                    homeViewModel.bookmarkUpdate(tweetId)
                                },
                                onShare = {},
                                onPollSelection = { tweetId, optionId ->
                                    homeViewModel.updatePollOption(tweetId, optionId)
                                },
                                onComment = {},
                                onViews = {},
                                onLike = { homeViewModel.onLikeTweet(it) },
                                onRepost = { openCreateTweetWithParentId(it, true, false) },
                            ),
                            tweetVisibility = {
                                homeViewModel.updateViewForTweet(it)
                            },
                        )
                    }
                }
            }
        }
    }
}