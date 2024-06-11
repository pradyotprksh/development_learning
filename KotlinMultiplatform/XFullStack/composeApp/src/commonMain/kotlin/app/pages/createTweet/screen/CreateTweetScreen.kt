package app.pages.createTweet.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.pages.createTweet.screen.composables.CreateTweetOptionsComposable
import app.pages.createTweet.screen.composables.TweetTextFieldComposable
import app.pages.createTweet.screen.composables.TweetVisibilityComposable
import app.pages.createTweet.viewModel.CreateTweetViewModel
import utils.Constants.ConstValues.TWEET_MAX_LENGTH
import utils.Localization

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTweetScreen(
    createTweetViewModel: CreateTweetViewModel = viewModel { CreateTweetViewModel() },
    parentTweetId: String?,
    onNavigateBack: () -> Unit,
) {
    LaunchedEffect(Unit) {
        createTweetViewModel.initialSetup(parentTweetId)
    }

    val createTweetState by createTweetViewModel.createTweetState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = Icons.Default.Close.name,
                        )
                    }
                },
                actions = {
                    Button(
                        onClick = {
                            createTweetViewModel.createTweet(
                                navigateBack = onNavigateBack,
                            )
                        },
                    ) {
                        Text(
                            if (createTweetState.multipleTweets) Localization.POST_ALL else Localization.POST
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                },
            )
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(
                top = it.calculateTopPadding() + 10.dp,
                bottom = it.calculateBottomPadding() + 25.dp,
                start = it.calculateStartPadding(LocalLayoutDirection.current),
                end = it.calculateEndPadding(LocalLayoutDirection.current),
            ),
        ) {
            LazyColumn(
                modifier = Modifier.weight(0.8f).padding(
                    start = 15.dp,
                    end = 15.dp,
                )
            ) {
                items(createTweetState.tweets) { tweet ->
                    AnimatedVisibility(
                        visible = tweet.isVisible,
                    ) {
                        TweetTextFieldComposable(
                            modifier = Modifier.alpha(
                                if (createTweetState.currentFocusedTweetIndex == tweet.index) 1f else 0.4f
                            ),
                            profileImage = createTweetState.profileImage,
                            tweet = tweet,
                            showCloseButton = tweet.index > 0,
                            showPoll = createTweetState.currentFocusedTweetIndex == tweet.index,
                            onTweetValueChange = { value ->
                                createTweetViewModel.updateTweet(value, tweet.index)
                            },
                            onFocusChange = {
                                createTweetViewModel.focusChange(tweet.index)
                            },
                            deleteTweet = {
                                createTweetViewModel.deleteTweet(tweet.index)
                            },
                            onPollCloseClick = {
                                createTweetViewModel.updateCurrentTweetPollStatus(isAPoll = false)
                            },
                            onAddNewPollClick = {
                                createTweetViewModel.addNewPollChoice(tweet.index)
                            },
                            onPollChoiceChange = { index, value ->
                                createTweetViewModel.updatePollChoiceValue(
                                    tweet.index, index, value,
                                )
                            },
                            onPollTimeChange = { hour, minute, seconds ->
                                createTweetViewModel.updatePollTime(
                                    tweet.index, hour, minute, seconds,
                                )
                            }
                        )
                    }
                }
            }
            HorizontalDivider()
            TweetVisibilityComposable(
                onClick = {},
            )
            HorizontalDivider()
            CreateTweetOptionsComposable(
                progress = createTweetState.currentSelectedTweetLength.toFloat() / TWEET_MAX_LENGTH,
                enableAddNewTweetButton = createTweetState.enableAddNewTweetButton,
                onImageClick = {},
                onGifClick = {},
                onPollClick = {
                    createTweetViewModel.updateCurrentTweetPollStatus(isAPoll = true)
                },
                onLocationClick = {},
                onAddTweetClick = {
                    createTweetViewModel.addNewTweetField()
                },
            )
        }
    }
}
