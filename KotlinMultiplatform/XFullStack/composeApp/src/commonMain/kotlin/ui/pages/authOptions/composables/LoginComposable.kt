package ui.pages.authOptions.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ui.composables.richText.RichTextComposable
import ui.composables.richText.RichTextDetails
import ui.composables.richText.TextDetails
import utils.Tags
import xfullstack.composeapp.generated.resources.Res
import xfullstack.composeapp.generated.resources.have_an_account
import xfullstack.composeapp.generated.resources.log_in
import xfullstack.composeapp.generated.resources.white_space

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginComposable(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    RichTextComposable(
        richTextDetails = RichTextDetails(
            texts = listOf(
                TextDetails(
                    text = stringResource(Res.string.have_an_account),
                ),
                TextDetails(
                    text = stringResource(Res.string.white_space),
                ),
                TextDetails(
                    text = stringResource(Res.string.log_in),
                    isClickable = true,
                    spanStyle = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                    ),
                    tag = Tags.LogIn,
                    actions = onClick
                ),
            ),
            textStyle = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
        ),
        modifier = modifier,
    )
}