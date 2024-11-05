package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pradyotprakash.glassbridgegame.app.navigation.BridgeGame
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.screen.composables.Glasses
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.screen.composables.PlayerSitArena
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.screen.composables.PlayerWinnerArena
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.BridgeGameViewModel

@Composable
fun BridgeGameScreen(
    bridgeGameViewModel: BridgeGameViewModel = viewModel(),
    bridgeGameDetails: BridgeGame,
) {
    LaunchedEffect(BridgeGame) {
        bridgeGameViewModel.initGame(bridgeGameDetails)
    }

    val bridgeGameState by bridgeGameViewModel.bridgeGameState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(
                start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
                end = paddingValues.calculateEndPadding(LocalLayoutDirection.current),
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
            ),
        ) {
            Text(
                bridgeGameState.gameTimeString,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                color = if (bridgeGameState.isGameFinished) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth().padding(10.dp),
            )
            AnimatedVisibility(
                visible = bridgeGameState.winnerPlayer.isEmpty(),
            ) {
                HorizontalDivider(
                    thickness = 5.dp, modifier = Modifier.padding(0.dp)
                )
            }
            AnimatedVisibility(
                visible = bridgeGameState.winnerPlayer.isNotEmpty(),
            ) {
                PlayerWinnerArena(
                    players = bridgeGameState.winnerPlayer,
                )
            }
            Glasses(
                modifier = Modifier.fillMaxWidth().weight(1f),
                glassesState = bridgeGameState.glasses,
                players = bridgeGameState.players,
            ) {
                bridgeGameViewModel.onBridgeGlassTap(it)
            }
            AnimatedVisibility(
                visible = bridgeGameState.inArenaPlayer.isNotEmpty(),
            ) {
                PlayerSitArena(
                    players = bridgeGameState.players,
                )
            }
        }
    }
}