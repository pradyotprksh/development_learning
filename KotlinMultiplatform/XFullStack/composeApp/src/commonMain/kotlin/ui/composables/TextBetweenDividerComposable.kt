package ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import xfullstack.composeapp.generated.resources.Res
import xfullstack.composeapp.generated.resources.or

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TextBetweenDivider() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(
            vertical = 4.dp
        )
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f)
        )
        Text(
            stringResource(Res.string.or),
            modifier = Modifier.padding(
                horizontal = 5.dp,
            )
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f)
        )
    }
}