package app.pages.auth.authOptions.screen.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import app.composables.richText.RichTextComposable
import app.composables.richText.RichTextDetails
import app.composables.richText.TextDetails
import utils.Constants.ConstValues.COOKIE_USE
import utils.Constants.ConstValues.PRIVACY_POLICY
import utils.Constants.ConstValues.TERMS
import utils.Localization
import utils.Logger
import utils.LoggerLevel

@Composable
fun SignupLegalDetails(modifier: Modifier = Modifier) {
    RichTextComposable(
        richTextDetails = RichTextDetails(
            texts = listOf(
                TextDetails(
                    text = Localization.BY_SIGNING_UP,
                ),
                TextDetails(
                    text = Localization.WHITE_SPACE,
                ),
                TextDetails(
                    text = Localization.TERMS,
                    isClickable = true,
                    spanStyle = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                    ),
                    tag = TERMS,
                    actions = {
                        Logger.log(
                            loggerLevel = LoggerLevel.Info,
                            message = "$TERMS tapped"
                        )
                    }
                ),
                TextDetails(
                    text = Localization.COMMA,
                ),
                TextDetails(
                    text = Localization.WHITE_SPACE,
                ),
                TextDetails(
                    text = Localization.PRIVACY_POLICY,
                    isClickable = true,
                    spanStyle = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                    ),
                    tag = PRIVACY_POLICY,
                    actions = {
                        Logger.log(
                            loggerLevel = LoggerLevel.Info,
                            message = "$PRIVACY_POLICY tapped"
                        )
                    }
                ),
                TextDetails(
                    text = Localization.COMMA,
                ),
                TextDetails(
                    text = Localization.WHITE_SPACE,
                ),
                TextDetails(
                    text = Localization.AND,
                ),
                TextDetails(
                    text = Localization.WHITE_SPACE,
                ),
                TextDetails(
                    text = Localization.COOKIE_USE,
                    isClickable = true,
                    spanStyle = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                    ),
                    tag = COOKIE_USE,
                    actions = {
                        Logger.log(
                            loggerLevel = LoggerLevel.Info,
                            message = "$COOKIE_USE tapped"
                        )
                    }
                ),
                TextDetails(
                    text = Localization.FULL_STOP,
                ),
            ),
            textStyle = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onBackground
            )
        ),
        modifier = modifier,
    )
}