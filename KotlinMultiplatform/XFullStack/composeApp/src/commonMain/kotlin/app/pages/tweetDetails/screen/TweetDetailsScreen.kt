package app.pages.tweetDetails.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import app.pages.tweetDetails.viewModel.TweetDetailsViewModel

@Composable
fun TweetDetailsScreen(
    tweetDetailsViewModel: TweetDetailsViewModel = viewModel { TweetDetailsViewModel() },
    tweetId: String,
    onNavigateBack: () -> Unit,
) {

}