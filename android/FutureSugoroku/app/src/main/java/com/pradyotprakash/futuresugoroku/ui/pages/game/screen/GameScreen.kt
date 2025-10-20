package com.pradyotprakash.futuresugoroku.ui.pages.game.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pradyotprakash.futuresugoroku.R
import com.pradyotprakash.futuresugoroku.ui.pages.game.screen.components.PlayerComposable
import com.pradyotprakash.futuresugoroku.ui.pages.game.screen.components.RoomComposable
import com.pradyotprakash.futuresugoroku.ui.pages.game.screen.components.RoomGameDetailsSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel(),
) {
    val gameState = gameViewModel.gameState.collectAsState()

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    val hideAndDismiss = {
        scope.launch {
            sheetState.hide()
        }.invokeOnCompletion {
            gameViewModel.toggleRoomGameSheet(null)
        }
    }

    gameState.value.selectedRoomCoordinate?.let {
        ModalBottomSheet(
            onDismissRequest = {
                hideAndDismiss()
            },
            sheetState = sheetState,
        ) {
            RoomGameDetailsSheet(
                room = gameViewModel.getSelectedRoomDetails(),
                rollDiceValues = gameState.value.selectedRoomDices,
                players = gameViewModel.getSelectedRoomPlayers(),
                onRollDice = gameViewModel::getDiceRoll,
                onRoomSelection = { player, door ->
                    gameViewModel.onRoomSelection(player, door)
                }
            )
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name)
                    )
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                    start = innerPadding.calculateStartPadding(
                        LocalLayoutDirection.current
                    ) + 15.dp,
                    end = innerPadding.calculateEndPadding(
                        LocalLayoutDirection.current
                    ) + 15.dp,
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                items(gameState.value.players) { player ->
                    PlayerComposable(
                        player = player,
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            gameState.value.rooms.forEach { rowRoom ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    rowRoom.forEach { room ->
                        RoomComposable(
                            modifier = Modifier.weight(1f),
                            room = room,
                            numberOfPlayer = gameViewModel.numberOfPlayerIn(
                                room.coordinates
                            ),
                            onRoomTap = {
                                gameViewModel.toggleRoomGameSheet(
                                    room.coordinates,
                                )
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${gameState.value.remainingTurns} ${
                    pluralStringResource(
                        R.plurals.turns_remaining,
                        gameState.value.remainingTurns,
                    )
                }",
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}