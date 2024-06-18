package app.pages.home.search.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.CircularDotComposable
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
    ) { paddingValues ->
        val startPadding = paddingValues.calculateStartPadding(LocalLayoutDirection.current) + 10.dp
        val endPadding = paddingValues.calculateEndPadding(LocalLayoutDirection.current) + 10.dp

        Column(
            modifier = Modifier.fillMaxSize().padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
            ),
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
            ) { page ->
                when (page) {
                    1 -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            stickyHeader {
                                Box(
                                    modifier = Modifier.padding(
                                        bottom = 5.dp,
                                    ).fillMaxWidth().background(
                                        color = MaterialTheme.colorScheme.background,
                                    )
                                ) {
                                    Text(
                                        Localization.TRENDS,
                                        style = MaterialTheme.typography.headlineMedium,
                                        modifier = Modifier.padding(
                                            start = startPadding,
                                            end = endPadding,
                                        )
                                    )
                                }
                            }

                            items(homeSearchState.trendingTags.size) { index ->
                                val tag = homeSearchState.trendingTags[index]

                                Column(
                                    modifier = Modifier.fillMaxWidth().clickable { },
                                ) {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(
                                            start = startPadding,
                                            end = endPadding,
                                        )
                                    ) {
                                        Text(
                                            text = "${index + 1}",
                                            style = MaterialTheme.typography.bodySmall,
                                        )
                                        Spacer(modifier = Modifier.width(2.dp))
                                        CircularDotComposable(
                                            modifier = Modifier.size(2.dp)
                                        )
                                        Spacer(modifier = Modifier.width(2.dp))
                                        Text(
                                            text = Localization.TRENDING,
                                            style = MaterialTheme.typography.bodySmall,
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                        IconButton(
                                            modifier = Modifier.size(MaterialTheme.typography.bodySmall.fontSize.value.dp),
                                            onClick = {},
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Menu,
                                                contentDescription = Icons.Default.Menu.name,
                                            )
                                        }
                                    }
                                    Text(
                                        tag.tag,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.padding(
                                            start = startPadding,
                                            end = endPadding,
                                        )
                                    )
                                    Text(
                                        "${tag.count} ${Localization.POSTS.lowercase()}",
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.padding(
                                            start = startPadding,
                                            end = endPadding,
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    if (index == homeSearchState.trendingTags.lastIndex) {
                                        HorizontalDivider()
                                        Spacer(modifier = Modifier.height(15.dp))
                                    }
                                }
                            }
                        }
                    }

                    else -> {
                        Box { }
                    }
                }
            }
        }
    }
}