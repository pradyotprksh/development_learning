package app.ui.splash.view

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import domain.modal.NameUrl
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun PokemonImageCell(image: NameUrl) {
    KamelImage(
        asyncPainterResource(
            image.url ?: "",
        ),
        contentDescription = image.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth().aspectRatio(1.0f)
    )
}