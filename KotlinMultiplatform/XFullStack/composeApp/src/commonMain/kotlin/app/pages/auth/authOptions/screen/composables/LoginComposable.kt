package app.pages.auth.authOptions.screen.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import app.composables.richText.RichTextComposable
import app.composables.richText.RichTextDetails
import app.composables.richText.TextDetails
import utils.Constants.ConstValues.LOG_IN
import utils.Localization

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
                    tag = LOG_IN,
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