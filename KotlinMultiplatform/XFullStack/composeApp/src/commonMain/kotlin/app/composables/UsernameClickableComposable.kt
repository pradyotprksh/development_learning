package app.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import app.composables.richText.RichTextComposable
import app.composables.richText.RichTextDetails
import app.composables.richText.TextDetails
import utils.Constants.ConstValues.CREATED_BY
import utils.Constants.ConstValues.USERNAME_PREFIX
import utils.Localization

@Composable
fun UsernameClickableComposable(
    modifier: Modifier = Modifier,
    text: String,
    username: String,
    onClick: () -> Unit,
) {
    val texts = mutableListOf<TextDetails>()
    if (text.isNotBlank()) {
        texts.add(
            TextDetails(
                text = text,
            ),
        )
        texts.add(
            TextDetails(
                text = Localization.WHITE_SPACE,
            ),
        )
    }
    texts.add(
        TextDetails(
            text = "${USERNAME_PREFIX}${username}",
            isClickable = true,
            spanStyle = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
            ),
            tag = CREATED_BY,
            actions = {
                onClick()
            },
        ),
    )

    RichTextComposable(
        modifier = modifier,
        richTextDetails = RichTextDetails(
            texts = texts,
            textStyle = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
        ),
    )
}