package app.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconTextButtonComposable(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String,
    tint: Color? = null,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        if (text.isNotBlank()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = icon.name,
                    modifier = Modifier.size(15.dp),
                    tint = tint ?: LocalContentColor.current,
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        } else {
            Icon(
                imageVector = icon,
                contentDescription = icon.name,
            )
        }
    }
}