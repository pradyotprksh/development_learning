package app.pages.tweetDetails.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Expand
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.NetworkImageComposable
import app.composables.UsernameClickableComposable
import app.composables.tweet.PollChoicesComposable
import app.composables.tweet.TweetActionsComposable
import app.composables.tweet.TweetComposable
import app.composables.tweet.TweetWithTagComposable
import app.pages.createTweet.screen.composables.CreateTweetOptionsComposable
import app.pages.home.home.state.TweetActions
import app.pages.tweetDetails.screen.composables.TweetCreatorDetailsComposable
import app.pages.tweetDetails.screen.composables.TweetPopularityDetailsComposable
import app.pages.tweetDetails.screen.composables.TweetTimeViewComposable
import app.pages.tweetDetails.viewModel.TweetDetailsViewModel
import kotlinx.coroutines.launch
import utils.Constants.ConstValues.TWEET_MAX_LENGTH
import utils.Localization
import utils.Logger
import utils.LoggerLevel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TweetDetailsScreen(
    tweetDetailsViewModel: TweetDetailsViewModel = viewModel { TweetDetailsViewModel() },
    tweetId: String,
    onNavigateBack: () -> Unit,
    openCreateTweetWithParentId: (String, Boolean, Boolean) -> Unit,
    openTweetDetails: (String) -> Unit,
    openProfileDetails: (String) -> Unit,
) {
    LaunchedEffect(Unit) {
        tweetDetailsViewModel.initialSetup(tweetId)
    }

    val scope = rememberCoroutineScope()
    val replyFocusRequester = remember { FocusRequester() }
    val snackbarHostState = remember { SnackbarHostState() }
    val tweetDetailsScreenState by tweetDetailsViewModel.tweetDetailsScreen.collectAsState()
    tweetDetailsScreenState.snackBarMessage?.let { message ->
        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = Localization.OKAY,
                duration = SnackbarDuration.Short
            )
            when (result) {
                SnackbarResult.ActionPerformed, SnackbarResult.Dismissed -> {
                    tweetDetailsViewModel.removeSnackBarMessage()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        Localization.POST,
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
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding() + 25.dp,
            ).imePadding()
        ) {
            HorizontalDivider()
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                tweetDetailsScreenState.tweet?.let { tweet ->
                    item {
                        tweet.createdBy?.let { createdBy ->
                            TweetCreatorDetailsComposable(
                                modifier = Modifier.fillMaxWidth().padding(
                                    all = 10.dp,
                                ),
                                createdBy = createdBy,
                                isSameUser = createdBy.isSameUser,
                                isFollowedByCurrentUser = createdBy.isFollowedByCurrentUser,
                                isFollowingCurrentUser = createdBy.isFollowingCurrentUser,
                                followUpdate = {
                                    tweetDetailsViewModel.followUpdate(createdBy.id)
                                },
                                openProfileDetails = {
                                    openProfileDetails(createdBy.id)
                                },
                            )
                        }
                    }

                    item {
                        TweetWithTagComposable(
                            modifier = Modifier.fillMaxWidth().padding(
                                start = 10.dp,
                                end = 10.dp,
                                bottom = 10.dp,
                            ),
                            tweet = tweet.tweet,
                            onTagClick = { tag ->
                                Logger.log(LoggerLevel.Info, "Tag clicked $tag")
                            },
                            onOtherPartClick = {}
                        )
                    }

                    item {
                        if (tweet.media.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(10.dp))
                            LazyRow(
                                modifier = Modifier.fillMaxWidth().height(120.dp)
                            ) {
                                items(tweet.media.size) {
                                    Row {
                                        if (it == 0) {
                                            Spacer(modifier = Modifier.width(10.dp))
                                        }
                                        NetworkImageComposable(
                                            url = tweet.media[it],
                                            modifier = Modifier.width(80.dp).fillMaxHeight(),
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                    }
                                }
                            }
                        }
                    }

                    if (tweet.isAPoll) {
                        item {
                            PollChoicesComposable(
                                modifier = Modifier.fillMaxWidth().padding(
                                    start = 10.dp,
                                    end = 10.dp,
                                    bottom = 10.dp,
                                ),
                                tweetId = tweet.id,
                                pollChoices = tweet.pollChoices,
                                isPollingAllowed = tweet.isPollingAllowed,
                                pollingEndTime = tweet.pollingEndTime,
                                totalVotesOnPoll = tweet.pollChoices.sumOf { choice -> choice.voteCount },
                                onPollSelection = { _, _ -> },
                            )
                        }
                    }

                    tweet.parentTweetDetails?.let { parentTweetDetails ->
                        if (tweet.isQuoteTweet) {
                            item {
                                Box(
                                    modifier = Modifier.padding(
                                        start = 10.dp,
                                        end = 10.dp,
                                        bottom = 10.dp,
                                    )
                                ) {
                                    TweetComposable(
                                        modifier = Modifier.border(
                                            width = Dp.Hairline,
                                            color = MaterialTheme.colorScheme.outlineVariant,
                                            shape = RoundedCornerShape(10.dp)
                                        ).padding(
                                            horizontal = 15.dp, vertical = 8.dp
                                        ),
                                        tweet = parentTweetDetails,
                                        showTweetActions = false,
                                        tweetActions = TweetActions(
                                            profileImageClick = {},
                                            onTweetClick = { tweetId ->
                                                openTweetDetails(tweetId)
                                            },
                                            onBookmark = { tweetId ->
                                                tweetDetailsViewModel.bookmarkUpdate(
                                                    tweetId
                                                )
                                            },
                                            onShare = {},
                                            onPollSelection = { _, _ -> },
                                            onComment = { },
                                            onViews = {},
                                            onLike = { id -> tweetDetailsViewModel.onLikeTweet(id) },
                                            onRepost = { id ->
                                                openCreateTweetWithParentId(
                                                    id, true, false
                                                )
                                            },
                                        ),
                                    )
                                }
                            }
                        }
                    }

                    item {
                        TweetTimeViewComposable(
                            modifier = Modifier.fillMaxWidth().padding(
                                start = 10.dp,
                                end = 10.dp,
                                bottom = 10.dp,
                            ),
                            tweetedOn = tweet.tweetedOn,
                            views = tweet.views.toString(),
                        )
                    }

                    item {
                        TweetPopularityDetailsComposable(
                            repostsCount = tweet.repostsCount.toString(),
                            quotesCount = tweet.quotesCount.toString(),
                            likesCount = tweet.likesCount.toString(),
                            bookmarksCount = tweet.bookmarksCount.toString(),
                        )
                    }

                    item {
                        Column {
                            HorizontalDivider()
                            TweetActionsComposable(
                                modifier = Modifier.padding(
                                    start = 10.dp,
                                    end = 10.dp,
                                ),
                                commentCount = "",
                                retweetCount = "",
                                likeCount = "",
                                views = "",
                                showViews = false,
                                isLikedByCurrentUser = tweet.isLikedByCurrentUser,
                                isBookmarkedByCurrentUser = tweet.isBookmarkedByCurrentUser,
                                onBookmark = {
                                    tweetDetailsViewModel.bookmarkUpdate(tweetId)
                                },
                                onShare = { },
                                onAddComment = {
                                    scope.launch {
                                        replyFocusRequester.requestFocus()
                                    }
                                },
                                onRepost = { openCreateTweetWithParentId(tweetId, true, false) },
                                onLike = { tweetDetailsViewModel.onLikeTweet(tweetId) },
                                onViews = { },
                            )
                            HorizontalDivider()
                        }
                    }

                    items(tweetDetailsScreenState.replies) { replyTweet ->
                        Column(
                            modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                                val itemBounds = layoutCoordinates.boundsInParent()
                                val parentBounds =
                                    layoutCoordinates.parentCoordinates?.boundsInParent()
                                        ?: Rect.Zero

                                if (parentBounds.left <= itemBounds.left && parentBounds.top <= itemBounds.top && parentBounds.right >= itemBounds.right && parentBounds.bottom >= itemBounds.bottom) {
                                    tweetDetailsViewModel.updateViewForTweet(replyTweet.id)
                                }
                            },
                        ) {
                            TweetComposable(
                                modifier = Modifier.padding(
                                    horizontal = 15.dp, vertical = 8.dp
                                ),
                                tweet = replyTweet,
                                showTweetActions = true,
                                tweetActions = TweetActions(
                                    profileImageClick = {
                                        openProfileDetails(replyTweet.createdBy?.id ?: "")
                                    },
                                    onTweetClick = { tweetId ->
                                        openTweetDetails(tweetId)
                                    },
                                    onBookmark = { id ->
                                        tweetDetailsViewModel.bookmarkUpdate(id)
                                    },
                                    onShare = {},
                                    onPollSelection = { tweetId, optionId ->
                                        tweetDetailsViewModel.updatePollOption(tweetId, optionId)
                                    },
                                    onComment = {},
                                    onViews = {},
                                    onLike = { id -> tweetDetailsViewModel.onLikeTweet(id) },
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
            HorizontalDivider()
            tweetDetailsScreenState.tweet?.createdBy?.let { createdBy ->
                AnimatedVisibility(
                    visible = tweetDetailsScreenState.isReplyTweetFocused
                ) {
                    UsernameClickableComposable(
                        modifier = Modifier.padding(
                            all = 10.dp,
                        ),
                        text = Localization.REPLYING_TO,
                        username = createdBy.username,
                        onClick = {
                            openProfileDetails(createdBy.id)
                        },
                    )
                }
            }
            TextField(value = tweetDetailsScreenState.replyTweet, onValueChange = { value ->
                tweetDetailsViewModel.updateTweetReply(value)
            }, modifier = Modifier.fillMaxWidth().padding(5.dp).onFocusChanged { focus ->
                tweetDetailsViewModel.replyTextFieldFocusChanged(focus.isFocused)
            }.focusRequester(replyFocusRequester), placeholder = {
                Text(
                    Localization.POST_YOUR_REPLY,
                )
            }, trailingIcon = {
                IconButton(
                    onClick = {
                        openCreateTweetWithParentId(
                            tweetId, false, true
                        )
                    },
                ) {
                    val icon =
                        if (tweetDetailsScreenState.isReplyTweetFocused) Icons.Default.Expand else Icons.Default.Camera
                    Icon(
                        imageVector = icon,
                        contentDescription = icon.name,
                    )
                }
            }, keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Send,
            ), keyboardActions = KeyboardActions(
                onSend = {
                    scope.launch {
                        replyFocusRequester.freeFocus()
                    }
                    tweetDetailsViewModel.replyToTweet(tweetId)
                },
            )
            )
            AnimatedVisibility(
                visible = tweetDetailsScreenState.isReplyTweetFocused
            ) {
                CreateTweetOptionsComposable(
                    progress = tweetDetailsScreenState.replyTweet.length.toFloat() / TWEET_MAX_LENGTH,
                    enableAddNewTweetButton = true,
                    showAddNewTweetButton = false,
                    tweetAction = {
                        Button(
                            onClick = {
                                tweetDetailsScreenState.tweet?.id?.let { tweetId ->
                                    scope.launch {
                                        replyFocusRequester.freeFocus()
                                    }
                                    tweetDetailsViewModel.replyToTweet(tweetId)
                                }
                            },
                            enabled = tweetDetailsScreenState.replyTweet.isNotBlank(),
                            modifier = Modifier.padding(
                                start = 10.dp,
                                end = 10.dp,
                            )
                        ) {
                            Text(
                                Localization.REPLY,
                            )
                        }
                    },
                    onImageClick = {},
                    onGifClick = {},
                    onPollClick = {},
                    onLocationClick = {},
                    onAddTweetClick = {},
                )
            }
        }
    }
}