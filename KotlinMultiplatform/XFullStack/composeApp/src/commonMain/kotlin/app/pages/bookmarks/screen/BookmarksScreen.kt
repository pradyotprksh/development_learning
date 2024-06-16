package app.pages.bookmarks.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.tweet.TweetComposable
import app.pages.bookmarks.viewModel.BookmarksViewModel
import app.pages.home.home.state.TweetActions
import utils.Localization

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen(
    bookmarksViewModel: BookmarksViewModel = viewModel { BookmarksViewModel() },
    onNavigateBack: () -> Unit,
    openCreateTweetWithParentId: (String, Boolean, Boolean) -> Unit,
    openTweetDetails: (String) -> Unit,
    openProfileDetails: (String) -> Unit,
) {
    val bookmarksState by bookmarksViewModel.bookmarksState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        Localization.BOOKMARKS,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name,
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                actions = {
                    if (bookmarksState.bookmarks.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                bookmarksViewModel.updateDropDown()
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = Icons.Default.Menu.name,
                            )
                        }

                        DropdownMenu(
                            expanded = bookmarksState.openDropDown,
                            onDismissRequest = {
                                bookmarksViewModel.updateDropDown()
                            },
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(Localization.CLEAR_ALL_BOOKMARKS)
                                },
                                onClick = {
                                    bookmarksViewModel.clearAllBookmarks()
                                },
                            )
                        }
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
            ),
        ) {
            HorizontalDivider()
            if (bookmarksState.bookmarks.isEmpty()) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    Localization.SAVE_POSTS_FOR_LATER,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(
                        horizontal = 15.dp,
                    ),
                )
                Text(
                    Localization.BOOKMARKS_POSTS_TO_EASILY_FIND,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(
                        horizontal = 15.dp,
                    ),
                )
                Spacer(modifier = Modifier.weight(1f))
            } else {
                LazyColumn(
                    state = rememberLazyListState(),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(bookmarksState.bookmarks) { post ->
                        Column {
                            TweetComposable(
                                modifier = Modifier.padding(
                                    horizontal = 15.dp, vertical = 8.dp
                                ).onGloballyPositioned { layoutCoordinates ->
                                    val itemBounds = layoutCoordinates.boundsInParent()
                                    val parentBounds =
                                        layoutCoordinates.parentCoordinates?.boundsInParent()
                                            ?: Rect.Zero

                                    if (parentBounds.left <= itemBounds.left && parentBounds.top <= itemBounds.top && parentBounds.right >= itemBounds.right && parentBounds.bottom >= itemBounds.bottom) {
                                        bookmarksViewModel.updateViewForTweet(post.id)
                                    }
                                },
                                tweet = post,
                                showTweetActions = true,
                                tweetActions = TweetActions(
                                    profileImageClick = { id ->
                                        openProfileDetails(id)
                                    },
                                    onTweetClick = { id ->
                                        openTweetDetails(id)
                                    },
                                    onBookmark = { tweetId ->
                                        bookmarksViewModel.bookmarkUpdate(tweetId)
                                    },
                                    onShare = {},
                                    onPollSelection = { tweetId, optionId ->
                                        bookmarksViewModel.updatePollOption(tweetId, optionId)
                                    },
                                    onComment = {},
                                    onViews = {},
                                    onLike = { id -> bookmarksViewModel.onLikeTweet(id) },
                                    onRepost = { id ->
                                        openCreateTweetWithParentId(
                                            id, true, false
                                        )
                                    },
                                ),
                            )
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}