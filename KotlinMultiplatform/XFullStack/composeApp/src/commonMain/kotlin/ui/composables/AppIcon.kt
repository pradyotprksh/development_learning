package ui.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import utils.Resources

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AppIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(Resources.Logo.resource),
        Resources.Logo.contentDescription,
        modifier = modifier,
    )
}