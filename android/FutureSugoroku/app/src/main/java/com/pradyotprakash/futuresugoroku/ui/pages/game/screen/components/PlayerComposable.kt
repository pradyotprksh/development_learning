package com.pradyotprakash.futuresugoroku.ui.pages.game.screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Player
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.PlayerStatus

@Composable
fun PlayerComposable(
    modifier: Modifier = Modifier,
    player: Player,
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = when (player.status) {
                    PlayerStatus.Playing -> {
                        MaterialTheme.colorScheme.primaryContainer
                    }
                    PlayerStatus.Won -> {
                        MaterialTheme.colorScheme.primaryFixed
                    }
                    PlayerStatus.Dead -> {
                        MaterialTheme.colorScheme.error
                    }
                },
            )
            .size(120.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = player.name,
            )
            Text(
                text = player.roomPosition.humanReadableName,
            )
            Text(
                text = when (player.status) {
                    PlayerStatus.Playing -> "${player.score}"
                    else -> player.status.name
                }
            )
        }
    }
}