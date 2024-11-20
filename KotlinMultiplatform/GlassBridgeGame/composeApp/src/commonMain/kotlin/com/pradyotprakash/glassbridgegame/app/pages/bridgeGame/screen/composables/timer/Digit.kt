package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.screen.composables.timer

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Digit(
    digit: List<Int>,
    isTimeStopped: Boolean,
) {
    val timeStoppedColor = MaterialTheme.colorScheme.error
    val lineEnabled = MaterialTheme.colorScheme.primary
    val lineDisabled = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f)

    val color1 by animateColorAsState(
        if (isTimeStopped) timeStoppedColor else if (digit[0] == 1) lineEnabled else lineDisabled,
        label = "color1"
    )
    val color2 by animateColorAsState(
        if (isTimeStopped) timeStoppedColor else if (digit[1] == 1) lineEnabled else lineDisabled,
        label = "color2"
    )
    val color3 by animateColorAsState(
        if (isTimeStopped) timeStoppedColor else if (digit[2] == 1) lineEnabled else lineDisabled,
        label = "color3"
    )
    val color4 by animateColorAsState(
        if (isTimeStopped) timeStoppedColor else if (digit[3] == 1) lineEnabled else lineDisabled,
        label = "color4"
    )
    val color5 by animateColorAsState(
        if (isTimeStopped) timeStoppedColor else if (digit[4] == 1) lineEnabled else lineDisabled,
        label = "color5"
    )
    val color6 by animateColorAsState(
        if (isTimeStopped) timeStoppedColor else if (digit[5] == 1) lineEnabled else lineDisabled,
        label = "color6"
    )
    val color7 by animateColorAsState(
        if (isTimeStopped) timeStoppedColor else if (digit[6] == 1) lineEnabled else lineDisabled,
        label = "color7"
    )

    val lineLength = 20.dp
    val lineThickness = 4.dp

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row {
            VerticalDivider(
                modifier = Modifier.height(lineLength),
                thickness = lineThickness,
                color = color6,
            )
            HorizontalDivider(
                modifier = Modifier.width(lineLength),
                thickness = lineThickness,
                color = color1,
            )
            VerticalDivider(
                modifier = Modifier.height(lineLength),
                thickness = lineThickness,
                color = color2,
            )
        }
        HorizontalDivider(
            thickness = lineThickness,
            modifier = Modifier.width(lineLength + (lineThickness * 2)).padding(
                start = lineThickness,
                end = lineThickness,
            ),
            color = color7,
        )
        Row {
            VerticalDivider(
                modifier = Modifier.height(lineLength),
                thickness = lineThickness,
                color = color5,
            )
            HorizontalDivider(
                thickness = lineThickness,
                modifier = Modifier.width(lineLength).align(Alignment.Bottom),
                color = color4,
            )
            VerticalDivider(
                modifier = Modifier.height(lineLength),
                thickness = lineThickness,
                color = color3,
            )
        }
    }
}