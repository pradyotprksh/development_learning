package app.pages.tweetDetails.screen.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import utils.Localization

@Composable
fun TweetPopularityDetailsComposable(
    modifier: Modifier = Modifier,
    repostsCount: String,
    quotesCount: String,
    likesCount: String,
    bookmarksCount: String,
) {
    Column(
        modifier = modifier,
    ) {
        HorizontalDivider()
        Row(
            modifier = Modifier.fillMaxWidth().padding(
                all = 10.dp,
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                repostsCount, style = LocalTextStyle.current.copy(
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
                quotesCount, style = LocalTextStyle.current.copy(
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
                likesCount, style = LocalTextStyle.current.copy(
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
                bookmarksCount, style = LocalTextStyle.current.copy(
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