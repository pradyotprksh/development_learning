package app.composables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XAppBarComposable(
    modifier: Modifier = Modifier,
    title: @Composable (() -> Unit)? = null,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable (RowScope.() -> Unit) = {},
) {
    CenterAlignedTopAppBar(
        title = title ?: {
            AppIconComposable(
                imageModifier = Modifier
                    .size(25.dp)
            )
        },
        navigationIcon = navigationIcon,
        actions = actions,
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}