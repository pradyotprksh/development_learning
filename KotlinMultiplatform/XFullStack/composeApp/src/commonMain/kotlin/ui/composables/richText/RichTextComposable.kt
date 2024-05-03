package ui.composables.richText

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun RichTextComposable(
    richTextDetails: RichTextDetails,
    modifier: Modifier = Modifier,
) {
    if (richTextDetails.texts.isEmpty()) {
        Spacer(modifier = Modifier.size(0.dp))
    } else {
        val annotatedString = buildAnnotatedString {
            for (text in richTextDetails.texts) {
                if (text.haveClickProperty()) {
                    pushStringAnnotation(
                        tag = text.tag?.key ?: "",
                        annotation = text.tag?.value ?: "",
                    )
                    withStyle(
                        style = text.spanStyle ?: SpanStyle(),
                    ) {
                        append(text.text)
                    }
                    pop()
                } else {
                    append(text.text)
                }
            }
        }

        ClickableText(
            onClick = { offset ->
                for (text in richTextDetails.texts) {
                    if (text.haveClickProperty()) {
                        annotatedString.getStringAnnotations(
                            tag = text.tag?.key ?: "",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let {
                            text.actions?.invoke()
                        }
                    }
                }
            },
            text = annotatedString,
            style = richTextDetails.textStyle ?: MaterialTheme.typography.labelMedium,
            modifier = modifier,
        )
    }
}