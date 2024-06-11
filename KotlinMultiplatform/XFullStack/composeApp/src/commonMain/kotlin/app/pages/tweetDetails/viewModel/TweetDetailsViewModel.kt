package app.pages.tweetDetails.viewModel

import androidx.lifecycle.ViewModel
import di.SharedModulesDi
import domain.repositories.tweet.TweetRepository

class TweetDetailsViewModel(
    private val tweetRepository: TweetRepository = SharedModulesDi.Instance.tweetRepository,
) : ViewModel()