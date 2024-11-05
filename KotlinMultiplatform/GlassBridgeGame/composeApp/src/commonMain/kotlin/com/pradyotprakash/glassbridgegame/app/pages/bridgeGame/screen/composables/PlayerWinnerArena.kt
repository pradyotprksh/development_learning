package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.screen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.PlayerState

@Composable
fun PlayerWinnerArena(
    players: List<PlayerState>,
) {
    val size = players.size
    val middle = size / 2 + size % 2

    val firstHalf = players.subList(0, middle)
    val secondHalf = players.subList(middle, size)

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        HorizontalDivider(
            thickness = 5.dp,
        )
        Box {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().padding(10.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    firstHalf.map { player ->
                        AnimatedVisibility(
                            player.showInTheWinnerArena
                        ) {
                            Player(player = player)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    secondHalf.map { player ->
                        AnimatedVisibility(
                            player.showInTheWinnerArena
                        ) {
                            Player(player = player)
                        }
                    }
                }
            }
            Text(
                "WINNERS",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.fillMaxWidth().align(Alignment.Center).alpha(0.2f),
            )
        }
        HorizontalDivider(
            thickness = 5.dp,
        )
    }
}