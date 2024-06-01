package app.pages.createTweet.screen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.composables.ProfileImageComposable
import app.pages.createTweet.state.TweetDetails

@Composable
fun TweetTextFieldComposable(
    modifier: Modifier = Modifier,
    profileImage: String?,
    tweet: TweetDetails,
    onValueChange: (String) -> Unit,
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
            OutlinedTextField(
                value = tweet.tweet,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}