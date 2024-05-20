package app.composables

import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun ProfileImageComposable(
    profileImage: String?,
    modifier: Modifier = Modifier,
) {
    KamelImage(
        resource = asyncPainterResource(data = profileImage ?: ""),
        contentDescription = profileImage,
        onLoading = { progress ->
            CircularProgressIndicator(
                progress = { progress },
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