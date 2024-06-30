package app.composables

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun NetworkImageComposable(
    modifier: Modifier = Modifier,
    url: String?,
) {
    KamelImage(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.secondaryContainer,
            shape = RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 10.dp,
                bottomStart = 10.dp,
                bottomEnd = 10.dp,
            ),
        ).clip(
            RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 10.dp,
                bottomStart = 10.dp,
                bottomEnd = 10.dp,
            )
        ),
        resource = asyncPainterResource(data = url ?: ""),
        contentDescription = url,
        onLoading = {
            Icon(
                imageVector = Icons.Default.Downloading,
                contentDescription = Icons.Default.Downloading.name,
                modifier = modifier,
            )
        },
        animationSpec = tween(),
        contentScale = ContentScale.Crop,
    )
}