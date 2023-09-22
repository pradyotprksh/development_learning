package app.ui.splash.view

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import domain.modal.NameUrl
import io.kamel.image.asyncPainterResource

class PokemonImagesContent(private val images: List<NameUrl>) : Screen {
    @Composable
    override fun Content() {
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 3)
        ) {
            items(images) {
                it.url?.let { url -> asyncPainterResource(data = url) }
            }
        }
    }
}