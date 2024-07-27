package app.pages.slides.screen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun KeyMapComposable(
    modifier: Modifier = Modifier,
    showKeyMap: Boolean,
    keyEvent: Pair<String?, String?>?,
    showKeyEvent: Boolean,
) {
    AnimatedVisibility(
        visible = showKeyMap && keyEvent != null && showKeyEvent,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = modifier,
    ) {
        Column {
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Box(
                    modifier = Modifier.background(
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(10)
                    )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(10.dp),
                    ) {
                        keyEvent?.first?.let { symbol ->
                            Text(
                                symbol,
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    color = MaterialTheme.colorScheme.background,
                                ),
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                        keyEvent?.second?.let { name ->
                            Text(
                                name,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.background,
                                ),
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}