package app.composables.tweet

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ParentTweetReactionComposable(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(
            start = 15.dp,
            top = 8.dp,
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = icon.name,
            modifier = Modifier.size(
                MaterialTheme.typography.bodySmall.fontSize.value.dp,
            ),
            tint = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}