package app.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import utils.Resources

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AppIcon(
    boxModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    showCircularProgressIndicator: Boolean = false
) {
    Box(
        modifier = boxModifier
    ) {
        Image(
            painter = painterResource(Resources.Logo.resource),
            Resources.Logo.contentDescription,
            modifier = imageModifier
        )
        if (showCircularProgressIndicator)
            CircularProgressIndicator(
                strokeWidth = 2.dp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
            )
    }
}