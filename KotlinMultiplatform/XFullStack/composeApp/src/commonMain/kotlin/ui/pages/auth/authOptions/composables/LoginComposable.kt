package ui.pages.auth.authOptions.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import core.utils.Localization
import ui.composables.richText.RichTextComposable
import ui.composables.richText.RichTextDetails
import ui.composables.richText.TextDetails
import utils.Tags

@Composable
fun LoginComposable(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    RichTextComposable(
        richTextDetails = RichTextDetails(
            texts = listOf(
                TextDetails(
                    text = Localization.HAVE_AN_ACCOUNT,
                ),
                TextDetails(
                    text = Localization.WHITE_SPACE,
                ),
                TextDetails(
                    text = Localization.LOG_IN,
                    isClickable = true,
                    spanStyle = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                    ),
                    tag = Tags.LogIn,
                    actions = onClick
                ),
            ),
            textStyle = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
        ),
        modifier = modifier,
    )
}