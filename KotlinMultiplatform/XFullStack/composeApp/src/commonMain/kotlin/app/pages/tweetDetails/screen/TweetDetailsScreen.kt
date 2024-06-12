package app.pages.tweetDetails.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.composables.CircularDotComposable
import app.composables.ProfileImageComposable
import app.composables.tweet.PollChoicesComposable
import app.composables.tweet.TweetActionsComposable
import app.composables.tweet.TweetComposable
import app.pages.home.home.state.TweetActions
import app.pages.tweetDetails.viewModel.TweetDetailsViewModel
import kotlinx.coroutines.launch
import utils.Constants.ConstValues.USERNAME_PREFIX
import utils.Localization

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TweetDetailsScreen(
    tweetDetailsViewModel: TweetDetailsViewModel = viewModel { TweetDetailsViewModel() },
    tweetId: String,
    onNavigateBack: () -> Unit,
) {
    LaunchedEffect(Unit) {
        tweetDetailsViewModel.initialSetup(tweetId)
    }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val tweetDetailsScreen by tweetDetailsViewModel.tweetDetailsScreen.collectAsState()
    tweetDetailsScreen.snackBarMessage?.let { message ->
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
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(
                top = it.calculateTopPadding(),
                bottom = it.calculateBottomPadding() + 25.dp,
            ).imePadding()
        ) {
            HorizontalDivider()
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                tweetDetailsScreen.tweet?.let { tweet ->
                    item {
                        tweet.createdBy?.let { createdBy ->
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(
                                    all = 10.dp,
                                ),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                ProfileImageComposable(
                                    profileImage = createdBy.profilePicture,
                                    modifier = Modifier.size(40.dp).clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() },
                                        onClick = {},
                                    )
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Column {
                                    Text(
                                        createdBy.name,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        "${USERNAME_PREFIX}${createdBy.username}",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(
                                    onClick = {}
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = Icons.Default.Menu.name,
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Text(
                            tweet.tweet,
                            modifier = Modifier.fillMaxWidth().padding(
                                start = 10.dp,
                                end = 10.dp,
                                bottom = 10.dp,
                            )
                        )
                    }

                    if (tweet.isAPoll) {
                        item {
                            PollChoicesComposable(
                                modifier = Modifier.fillMaxWidth().padding(
                                    start = 10.dp,
                                    end = 10.dp,
                                    bottom = 10.dp,
                                ),
                                pollChoices = tweet.pollChoices,
                                isPollingAllowed = tweet.isPollingAllowed,
                                pollingEndTime = tweet.pollingEndTime,
                                totalVotesOnPoll = tweet.pollChoices.sumOf { choice -> choice.voteCount },
                                onPollSelection = { },
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
                                        createdByProfilePicture = parentTweetDetails.createdBy?.profilePicture,
                                        createdByName = parentTweetDetails.createdBy?.name ?: "",
                                        createdByUsername = parentTweetDetails.createdBy?.username
                                            ?: "",
                                        tweetedOn = parentTweetDetails.tweetedOn,
                                        tweet = parentTweetDetails.tweet,
                                        tweetId = parentTweetDetails.id,
                                        commentCount = "${parentTweetDetails.commentCount}",
                                        retweetCount = "${parentTweetDetails.retweetCount}",
                                        likeCount = "${parentTweetDetails.likesCount}",
                                        views = "${parentTweetDetails.views}",
                                        isAPoll = parentTweetDetails.isAPoll,
                                        isQuoteTweet = parentTweetDetails.isQuoteTweet,
                                        pollChoices = parentTweetDetails.pollChoices.toList(),
                                        isPollingAllowed = parentTweetDetails.isPollingAllowed,
                                        pollingEndTime = parentTweetDetails.pollingEndTime,
                                        showTweetActions = false,
                                        onPollSelection = { },
                                        tweetActions = TweetActions(),
                                        isLikedByCurrentUser = parentTweetDetails.isLikedByCurrentUser,
                                        parentTweetDetails = tweet.parentTweetDetails,
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(
                                start = 10.dp,
                                end = 10.dp,
                                bottom = 10.dp,
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                tweet.tweetedOn,
                                style = MaterialTheme.typography.bodySmall,
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            CircularDotComposable(
                                modifier = Modifier.size(3.dp),
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                tweet.views.toString(),
                                style = LocalTextStyle.current.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                Localization.VIEWS,
                                style = MaterialTheme.typography.bodySmall,
                            )
                        }
                    }

                    item {
                        Column {
                            HorizontalDivider()
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(
                                    all = 10.dp,
                                ),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    tweet.repostsCount.toString(),
                                    style = LocalTextStyle.current.copy(
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    Localization.REPOSTS,
                                    style = MaterialTheme.typography.bodySmall,
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    tweet.quotesCount.toString(),
                                    style = LocalTextStyle.current.copy(
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    Localization.QUOTES,
                                    style = MaterialTheme.typography.bodySmall,
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    tweet.likesCount.toString(),
                                    style = LocalTextStyle.current.copy(
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    Localization.LIKES,
                                    style = MaterialTheme.typography.bodySmall,
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    tweet.bookmarksCount.toString(),
                                    style = LocalTextStyle.current.copy(
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    Localization.BOOKMARKS,
                                    style = MaterialTheme.typography.bodySmall,
                                )
                            }
                        }
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
                                onBookmark = { },
                                onShare = { },
                                onAddComment = { },
                                onRepost = { },
                                onLike = { },
                                onViews = { },
                            )
                            HorizontalDivider()
                        }
                    }
                }
            }
            HorizontalDivider()
            TextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                placeholder = {
                    Text(
                        Localization.POST_YOUR_REPLY,
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Camera,
                            contentDescription = Icons.Default.Camera.name,
                        )
                    }
                },
            )
        }
    }
}