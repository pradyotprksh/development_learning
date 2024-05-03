package ui.pages.authOptions.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import core.utils.Logger
import core.utils.LoggerLevel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ui.composables.richText.RichTextComposable
import ui.composables.richText.RichTextDetails
import ui.composables.richText.TextDetails
import utils.Tags
import xfullstack.composeapp.generated.resources.Res
import xfullstack.composeapp.generated.resources.and
import xfullstack.composeapp.generated.resources.by_signing_up
import xfullstack.composeapp.generated.resources.comma
import xfullstack.composeapp.generated.resources.cookie_use
import xfullstack.composeapp.generated.resources.full_stop
import xfullstack.composeapp.generated.resources.privacy_policy
import xfullstack.composeapp.generated.resources.terms
import xfullstack.composeapp.generated.resources.white_space

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SignupLegalDetails(modifier: Modifier = Modifier) {
    RichTextComposable(
        richTextDetails = RichTextDetails(
            texts = listOf(
                TextDetails(
                    text = stringResource(Res.string.by_signing_up),
                ),
                TextDetails(
                    text = stringResource(Res.string.white_space),
                ),
                TextDetails(
                    text = stringResource(Res.string.terms),
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
                    text = stringResource(Res.string.comma),
                ),
                TextDetails(
                    text = stringResource(Res.string.white_space),
                ),
                TextDetails(
                    text = stringResource(Res.string.privacy_policy),
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
                    text = stringResource(Res.string.comma),
                ),
                TextDetails(
                    text = stringResource(Res.string.white_space),
                ),
                TextDetails(
                    text = stringResource(Res.string.and),
                ),
                TextDetails(
                    text = stringResource(Res.string.white_space),
                ),
                TextDetails(
                    text = stringResource(Res.string.cookie_use),
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
                    text = stringResource(Res.string.full_stop),
                ),
            ),
            textStyle = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onBackground
            )
        ),
        modifier = modifier,
    )
}