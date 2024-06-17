package app.composables.richText

import androidx.compose.ui.text.TextStyle

data class RichTextDetails(
    val texts: List<TextDetails>,
    val textStyle: TextStyle?,
)
