package app.pages.createTweet.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.pages.createTweet.viewModel.CreateTweetViewModel
import utils.Localization

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTweetScreen(
    createTweetViewModel: CreateTweetViewModel = viewModel { CreateTweetViewModel() },
    onNavigateBack: () -> Unit,
) {
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
                        onClick = {},
                    ) {
                        Text(
                            if (createTweetState.tweets.size == 1) Localization.POST else Localization.POST_ALL
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                },
            )
        },
    ) { }
}