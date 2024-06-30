package app.composables.tweet

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import app.composables.richText.RichTextComposable
import app.composables.richText.RichTextDetails
import app.composables.richText.TextDetails
import utils.TextType
import utils.UtilsMethod

@Composable
fun TweetWithTagComposable(
    modifier: Modifier = Modifier,
    tweet: String,
    onTagClick: (String) -> Unit,
    onOtherPartClick: () -> Unit,
) {
    val tags = UtilsMethod.Conversion.getTweetWithTags(tweet)
    if (tags.isEmpty()) {
        Text(
            tweet,
            modifier = modifier,
        )
    } else {
        RichTextComposable(
            modifier = modifier,
            richTextDetails = RichTextDetails(
                texts = tags.map {
                    if (it.second == TextType.Tag) {
                        TextDetails(
                            text = it.first,
                            isClickable = true,
                            spanStyle = SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                            ),
                            tag = it.first,
                            actions = {
                                onTagClick(it.first)
                            }
                        )
                    } else {
                        TextDetails(
                            text = it.first,
                            tag = it.first,
                            isClickable = true,
                            actions = onOtherPartClick
                        )
                    }
                },
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
            ),
        )
    }
}