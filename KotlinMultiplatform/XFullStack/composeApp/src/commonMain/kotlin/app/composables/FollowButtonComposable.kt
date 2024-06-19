package app.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import utils.Localization

@Composable
fun FollowButtonComposable(
    modifier: Modifier = Modifier,
    isFollowingCurrentUser: Boolean,
    isFollowedByCurrentUser: Boolean,
    isCurrentUser: Boolean,
    onClick: () -> Unit,
) {
    if (!isCurrentUser) {
        OutlinedButton(
            onClick = onClick,
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (isFollowingCurrentUser) Color.Transparent else MaterialTheme.colorScheme.primary,
                contentColor = if (isFollowingCurrentUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary,
            ),
            border = if (isFollowingCurrentUser) BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
            ) else null,
            modifier = modifier,
        ) {
            Text(
                if (isFollowingCurrentUser) Localization.FOLLOWING else if (isFollowedByCurrentUser) Localization.FOLLOW_BACK else Localization.FOLLOW,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}