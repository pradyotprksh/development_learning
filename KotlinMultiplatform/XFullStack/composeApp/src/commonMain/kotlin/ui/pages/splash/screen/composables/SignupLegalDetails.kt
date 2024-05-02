package ui.pages.splash.screen.composables

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import utils.Tags
import xfullstack.composeapp.generated.resources.Res
import xfullstack.composeapp.generated.resources.and
import xfullstack.composeapp.generated.resources.by_signing_up
import xfullstack.composeapp.generated.resources.comma
import xfullstack.composeapp.generated.resources.cookie_use
import xfullstack.composeapp.generated.resources.full_stop
import xfullstack.composeapp.generated.resources.privacy_policy
import xfullstack.composeapp.generated.resources.terms

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SignupLegalDetails() {
    val annotatedString = buildAnnotatedString {
        append(stringResource(Res.string.by_signing_up))
        append(" ")

        pushStringAnnotation(
            tag = Tags.Terms.key,
            annotation = Tags.Terms.value
        )
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
            ),
        ) {
            append(stringResource(Res.string.terms))
        }
        pop()

        append(stringResource(Res.string.comma))
        append(" ")

        pushStringAnnotation(
            tag = Tags.PrivacyPolicy.key,
            annotation = Tags.PrivacyPolicy.value
        )
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
            ),
        ) {
            append(stringResource(Res.string.privacy_policy))
        }
        pop()

        append(stringResource(Res.string.comma))
        append(" ")
        append(stringResource(Res.string.and))
        append(" ")

        pushStringAnnotation(
            tag = Tags.CookieUse.key,
            annotation = Tags.CookieUse.value
        )
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
            ),
        ) {
            append(stringResource(Res.string.cookie_use))
        }
        pop()

        append(stringResource(Res.string.full_stop))
    }
    ClickableText(
        onClick = { offset ->
            annotatedString.getStringAnnotations(
                tag = Tags.Terms.key,
                start = offset,
                end = offset
            ).firstOrNull()?.let { }
            annotatedString.getStringAnnotations(
                tag = Tags.PrivacyPolicy.key,
                start = offset,
                end = offset
            ).firstOrNull()?.let { }
            annotatedString.getStringAnnotations(
                tag = Tags.CookieUse.key,
                start = offset,
                end = offset
            ).firstOrNull()?.let { }
        },
        text = annotatedString,
        style = MaterialTheme.typography.labelMedium.copy(
            color = MaterialTheme.colorScheme.onBackground
        ),
    )
}