package app.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun BoxedCircularProgressBarIndicator(
    modifier: Modifier = Modifier,
    visible: Boolean,
) {
    AnimatedVisibility(
        visible = visible, modifier = modifier,
    ) {
        Box(
            modifier = Modifier.padding(top = 15.dp)
        ) {
            Box(
                modifier = Modifier.size(40.dp).clip(CircleShape).background(
                    color = MaterialTheme.colorScheme.background
                )
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center).size(25.dp),
                    strokeWidth = 2.dp,
                )
            }
        }
    }
}