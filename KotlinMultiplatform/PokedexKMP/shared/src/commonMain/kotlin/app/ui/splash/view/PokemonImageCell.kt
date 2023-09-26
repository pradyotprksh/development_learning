package app.ui.splash.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import domain.modal.PokemonImage
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PokemonImageCell(image: PokemonImage) {
    KamelImage(
        asyncPainterResource(
            image.url ?: "",
        ),
        contentDescription = image.id.toString(),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.0f),
        onLoading = {
            Image(
                painterResource("default_pokemon.xml"),
                null
            )
        }
    )
}