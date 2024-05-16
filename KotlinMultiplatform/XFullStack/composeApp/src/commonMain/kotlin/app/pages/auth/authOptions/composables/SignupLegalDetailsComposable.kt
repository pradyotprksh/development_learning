package app.pages.auth.authOptions.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import app.composables.richText.RichTextComposable
import app.composables.richText.RichTextDetails
import app.composables.richText.TextDetails
import utils.Localization
import utils.Logger
import utils.LoggerLevel
import utils.Tags

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
                    tag = Tags.Terms,
                    actions = {
                        Logger.log(
                            loggerLevel = LoggerLevel.Info,
                            message = "${Tags.Terms.key} tapped"
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
                    tag = Tags.PrivacyPolicy,
                    actions = {
                        Logger.log(
                            loggerLevel = LoggerLevel.Info,
                            message = "${Tags.PrivacyPolicy.key} tapped"
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
                    tag = Tags.CookieUse,
                    actions = {
                        Logger.log(
                            loggerLevel = LoggerLevel.Info,
                            message = "${Tags.CookieUse.key} tapped"
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