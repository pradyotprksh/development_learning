package com.pradyotprakash.futuresugoroku.ui.pages.game.screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import com.pradyotprakash.futuresugoroku.R
import com.pradyotprakash.futuresugoroku.RoomCoordinate
import com.pradyotprakash.futuresugoroku.humanReadableName
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Room

@Composable
fun RoomComposable(
    modifier: Modifier = Modifier,
    room: Room,
    numberOfPlayerIn: (RoomCoordinate) -> Int,
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primaryContainer,
            )
            .height(
                80.dp,
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = room.coordinates.humanReadableName,
            )
            numberOfPlayerIn(room.coordinates).takeIf {
                it > 0
            }?.let {
                Text(
                    text = "$it ${pluralStringResource(R.plurals.players, it)}",
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}