package app.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XAppBarComposable(
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            AppIconComposable(
                imageModifier = Modifier
                    .size(40.dp)
            )
        },
        navigationIcon = navigationIcon,
        modifier = modifier,
    )
}