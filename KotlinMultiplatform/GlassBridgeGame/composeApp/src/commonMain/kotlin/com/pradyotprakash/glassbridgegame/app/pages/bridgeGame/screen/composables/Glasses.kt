package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.screen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.GlassState
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.PlayerState

@Composable
fun Glasses(
    modifier: Modifier = Modifier,
    glassesState: List<GlassState>,
    players: List<PlayerState>,
    onBridgeGlassTap: (Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(10.dp),
        reverseLayout = true,
    ) {
        item {
            for (g in glassesState.indices step 2) {
                val firstGlass = glassesState[g]
                val secondGlass = glassesState[g + 1]

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (g < glassesState.count() - 2) {
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Glass(
                            glass = firstGlass,
                            modifier = Modifier.weight(1f),
                            player = players.firstOrNull { it.playerNumber == firstGlass.playerNumber },
                        ) {
                            onBridgeGlassTap(firstGlass.glassNumber)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Glass(
                            glass = secondGlass,
                            modifier = Modifier.weight(1f),
                            player = players.firstOrNull { it.playerNumber == secondGlass.playerNumber },
                        ) {
                            onBridgeGlassTap(secondGlass.glassNumber)
                        }
                    }
                }
            }
        }
    }
}