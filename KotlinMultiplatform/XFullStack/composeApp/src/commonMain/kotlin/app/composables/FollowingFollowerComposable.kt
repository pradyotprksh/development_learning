package app.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import utils.Localization

@Composable
fun FollowingFollowerComposable(
    following: String,
    followers: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            following,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            Localization.FOLLOWING,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            followers,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            Localization.FOLLOWERS,
            style = MaterialTheme.typography.bodySmall
        )
    }
}