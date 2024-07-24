package app.pages.slides.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import utils.Localization

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SlidesScreen(
    showBackButton: Boolean,
    navigateBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        Localization.SLIDES,
                    )
                },
                navigationIcon = {
                    if (showBackButton) {
                        IconButton(
                            onClick = navigateBack,
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name,
                            )
                        }
                    }
                },
            )
        },
    ) {}
}