package com.pradyotprakash.futuresugoroku.ui.pages.game.screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pradyotprakash.futuresugoroku.RoomCoordinate
import com.pradyotprakash.futuresugoroku.roomHumanReadable

@Composable
fun RoomNumberComposable(
    modifier: Modifier = Modifier,
    roomCoordinate: RoomCoordinate,
    size: Dp,
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primaryContainer,
            )
            .size(size),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = roomCoordinate.roomHumanReadable,
        )
    }
}