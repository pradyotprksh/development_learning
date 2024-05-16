package app.composables.richText

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import utils.Tags

data class TextDetails(
    val text: String,
    val isClickable: Boolean = false,
    val style: TextStyle? = null,
    val spanStyle: SpanStyle? = null,
    val tag: Tags? = null,
    val actions: (() -> Unit)? = null,
) {
    fun haveClickProperty(): Boolean {
        return isClickable && tag != null && actions != null
    }
}

data class RichTextDetails(
    val texts: List<TextDetails>,
    val textStyle: TextStyle?,
)
