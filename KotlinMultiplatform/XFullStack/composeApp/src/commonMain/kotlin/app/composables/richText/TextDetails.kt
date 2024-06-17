package app.composables.richText

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle

data class TextDetails(
    val text: String,
    val isClickable: Boolean = false,
    val style: TextStyle? = null,
    val spanStyle: SpanStyle? = null,
    val tag: String? = null,
    val actions: (() -> Unit)? = null,
) {
    fun haveClickProperty(): Boolean {
        return isClickable && tag != null && actions != null
    }
}