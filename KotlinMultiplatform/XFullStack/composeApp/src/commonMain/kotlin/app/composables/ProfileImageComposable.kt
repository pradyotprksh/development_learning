package app.composables

import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun ProfileImageComposable(
    profileImage: String?,
    modifier: Modifier = Modifier,
) {
    KamelImage(
        modifier = modifier.clip(CircleShape),
        resource = asyncPainterResource(data = profileImage ?: ""),
        contentDescription = profileImage,
        onLoading = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = Icons.Default.AccountCircle.name,
                modifier = modifier,
            )
        },
        onFailure = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = Icons.Default.AccountCircle.name,
                modifier = modifier,
            )
        },
        animationSpec = tween(),
    )
}