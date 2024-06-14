package app.pages.profileDetails.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.FollowButtonComposable
import app.composables.FollowingFollowerComposable
import app.composables.LoadingDialogComposable
import app.composables.ProfileImageComposable
import app.composables.UsernameClickableComposable
import app.pages.profileDetails.viewModel.ProfileDetailsViewModel
import kotlinx.coroutines.launch
import utils.Localization

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProfileDetailsScreen(
    profileDetailsViewModel: ProfileDetailsViewModel = viewModel { ProfileDetailsViewModel() },
    userId: String?,
    onNavigateBack: () -> Unit,
) {
    LaunchedEffect(userId) {
        profileDetailsViewModel.initialSetup(userId)
    }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val profileDetailsState by profileDetailsViewModel.profileDetailsState.collectAsState()

    profileDetailsState.userInfoResponse?.let { details ->
        LaunchedEffect(details.isSameUser) {
            profileDetailsViewModel.listenToLikes(details.id)
        }
    }

    val tabPagerState = rememberPagerState(pageCount = { profileDetailsState.tabsDetails.size })
    if (profileDetailsState.showLoading) {
        LoadingDialogComposable()
    }
    profileDetailsState.snackBarMessage?.let { message ->
        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = Localization.OKAY,
                duration = SnackbarDuration.Short
            )
            when (result) {
                SnackbarResult.ActionPerformed, SnackbarResult.Dismissed -> {
                    profileDetailsViewModel.removeSnackBarMessage()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack, colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.background.copy(
                                alpha = 0.3f
                            )
                        )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name,
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {}, colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.background.copy(
                                alpha = 0.3f
                            )
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = Icons.Default.Search.name,
                        )
                    }
                    IconButton(
                        onClick = {}, colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.background.copy(
                                alpha = 0.3f
                            )
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = Icons.Default.Menu.name,
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
                    containerColor = Color.Transparent,
                ),
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        val startPadding = paddingValues.calculateStartPadding(LocalLayoutDirection.current) + 10.dp
        val endPadding = paddingValues.calculateEndPadding(LocalLayoutDirection.current) + 10.dp

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(
                bottom = paddingValues.calculateBottomPadding() + 25.dp,
            )
        ) {
            profileDetailsState.userInfoResponse?.let { userDetails ->
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(180.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(160.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth().height(120.dp).background(
                                    color = MaterialTheme.colorScheme.primary,
                                ).align(Alignment.TopCenter)
                            )

                            Box(
                                modifier = Modifier.align(Alignment.BottomStart).padding(
                                    start = startPadding,
                                ).size(80.dp)
                            ) {
                                ProfileImageComposable(
                                    profileImage = userDetails.profilePicture,
                                    modifier = Modifier.size(80.dp).align(
                                        Alignment.BottomStart,
                                    ).border(
                                        width = 2.dp,
                                        color = MaterialTheme.colorScheme.background,
                                        shape = CircleShape,
                                    )
                                )
                            }
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier.padding(
                            start = startPadding,
                            end = endPadding,
                        ).fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                userDetails.name,
                                style = MaterialTheme.typography.headlineSmall,
                            )
                            FollowButtonComposable(
                                isFollowingCurrentUser = userDetails.isFollowingCurrentUser,
                                isFollowedByCurrentUser = userDetails.isFollowedByCurrentUser,
                                isCurrentUser = userDetails.isSameUser,
                                onClick = {},
                            )
                        }
                        UsernameClickableComposable(
                            username = userDetails.username,
                            text = "",
                            onClick = {},
                        )
                        userDetails.bio?.let { bio ->
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                bio,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        FollowingFollowerComposable(
                            userDetails.following.toString(),
                            userDetails.followers.toString(),
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }

            stickyHeader {
                Column {
                    ScrollableTabRow(
                        selectedTabIndex = tabPagerState.currentPage,
                        edgePadding = 0.dp,
                        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                        divider = {},
                    ) {
                        profileDetailsState.tabsDetails.forEachIndexed { index, tab ->
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
                }
            }

            item {
                HorizontalPager(
                    state = tabPagerState,
                ) {}
            }
        }
    }
}