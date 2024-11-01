package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.screen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.PlayerState

@Composable
fun PlayerSitArena(
    players: List<PlayerState>,
) {
    HorizontalDivider(
        thickness = 5.dp,
    )
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            players.subList(0, 7).map { player ->
                AnimatedVisibility(
                    player.showInTheArena
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
            players.subList(8, 15).map { player ->
                AnimatedVisibility(
                    player.showInTheArena
                ) {
                    Player(player = player)
                }
            }
        }
    }
    HorizontalDivider(
        thickness = 5.dp,
    )
}