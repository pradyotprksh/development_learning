package app.pages.tweetDetails.screen.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.composables.CircularDotComposable
import utils.Localization

@Composable
fun TweetTimeViewComposable(
    modifier: Modifier = Modifier,
    tweetedOn: String,
    views: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            tweetedOn,
            style = MaterialTheme.typography.bodySmall,
        )
        Spacer(modifier = Modifier.width(4.dp))
        CircularDotComposable(
            modifier = Modifier.size(3.dp),
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            views,
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