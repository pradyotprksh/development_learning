package app.pages.slides.screen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun SlideChangeTapAreaComposable(
    modifier: Modifier = Modifier,
    onLeftTap: () -> Unit,
    onRightTap: () -> Unit,
) {
    Row(
        modifier = modifier,
    ) {
        Spacer(
            modifier = Modifier.fillMaxHeight().weight(0.4f).clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) {
                onLeftTap()
            },
        )
        Spacer(modifier = Modifier.fillMaxHeight().weight(0.2f))
        Spacer(
            modifier = Modifier.fillMaxHeight().weight(0.4f).clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) {
                onRightTap()
            },
        )
    }
}