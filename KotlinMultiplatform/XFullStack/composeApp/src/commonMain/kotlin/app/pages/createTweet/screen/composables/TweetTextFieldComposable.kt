package app.pages.createTweet.screen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.composables.ProfileImageComposable
import app.pages.createTweet.state.TweetDetails

@Composable
fun TweetTextFieldComposable(
    modifier: Modifier = Modifier,
    profileImage: String?,
    tweet: TweetDetails,
    showCloseButton: Boolean,
    showPoll: Boolean,
    onTweetValueChange: (String) -> Unit,
    onFocusChange: () -> Unit,
    deleteTweet: () -> Unit,
    onPollCloseClick: () -> Unit,
    onAddNewPollClick: () -> Unit,
    onPollChoiceChange: (Int, String) -> Unit,
    onPollTimeChange: (Long?, Long?, Long?) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProfileImageComposable(
                profileImage = profileImage,
                modifier = Modifier.size(35.dp).clickable {},
            )
            Spacer(modifier = Modifier.width(5.dp))
            Column(
                modifier = Modifier.fillMaxWidth().weight(1f),
            ) {
                OutlinedTextField(
                    value = tweet.tweet,
                    onValueChange = onTweetValueChange,
                    modifier = Modifier.fillMaxWidth().onFocusChanged {
                        if (it.hasFocus) {
                            onFocusChange()
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors().copy(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                    ),
                    label = {
                        Text(
                            tweet.label,
                        )
                    },
                )
                if (tweet.isAPoll) {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                AnimatedVisibility(
                    visible = tweet.isAPoll && showPoll,
                ) {
                    PollChoiceInputComposable(
                        modifier = Modifier.fillMaxWidth(),
                        pollChoices = tweet.pollChoices,
                        hour = tweet.pollHour,
                        minute = tweet.pollMinute,
                        seconds = tweet.pollSeconds,
                        onPollCloseClick = onPollCloseClick,
                        onAddNewPollClick = onAddNewPollClick,
                        onPollChoiceChange = onPollChoiceChange,
                        onPollTimeChange = onPollTimeChange,
                    )
                }
            }
            if (showCloseButton) {
                IconButton(
                    onClick = deleteTweet,
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = Icons.Default.Close.name,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}