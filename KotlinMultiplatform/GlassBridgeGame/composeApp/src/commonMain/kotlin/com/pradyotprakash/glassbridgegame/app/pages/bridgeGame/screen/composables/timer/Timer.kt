package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.screen.composables.timer

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Timer(
    modifier: Modifier = Modifier,
    timerDetails: List<List<Int>>,
    isTimeStopped: Boolean,
    updateBlinker: Boolean,
) {
    val blinkerColor by animateColorAsState(
        if (isTimeStopped) MaterialTheme.colorScheme.error else if (updateBlinker) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
        label = "color"
    )

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Digit(
            digit = timerDetails[0],
            isTimeStopped = isTimeStopped,
        )
        Spacer(modifier = Modifier.width(3.dp))
        Digit(
            digit = timerDetails[1],
            isTimeStopped = isTimeStopped,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HorizontalDivider(
                modifier = Modifier.width(4.dp),
                thickness = 4.dp,
                color = blinkerColor,
            )
            Spacer(modifier = Modifier.height(5.dp))
            HorizontalDivider(
                modifier = Modifier.width(4.dp),
                thickness = 4.dp,
                color = blinkerColor,
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Digit(
            digit = timerDetails[2],
            isTimeStopped = isTimeStopped,
        )
        Spacer(modifier = Modifier.width(3.dp))
        Digit(
            digit = timerDetails[3],
            isTimeStopped = isTimeStopped,
        )
    }
}