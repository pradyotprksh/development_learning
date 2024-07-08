package app.pages.home.grok.screen.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import utils.Localization

@Composable
fun GrokChatTabComposable(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    size: Int,
    selectIndex: (Int) -> Unit,
    getChatTitle: (Int) -> String,
) {
    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        modifier = modifier.fillMaxWidth().wrapContentHeight(),
        edgePadding = 0.dp,
        divider = {
            HorizontalDivider()
        },
        tabs = {
            for (i in 0..size) {
                Tab(
                    selected = selectedIndex == i,
                    onClick = {
                        selectIndex(i)
                    },
                    text = {
                        Text(text = if (i == 0) Localization.NEW else getChatTitle(i))
                    },
                    unselectedContentColor = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
    )
}