package com.pradyotprakash.futuresugoroku.ui.pages.game.screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pradyotprakash.futuresugoroku.DiceToDoor
import com.pradyotprakash.futuresugoroku.R
import com.pradyotprakash.futuresugoroku.diceHumanReadable
import com.pradyotprakash.futuresugoroku.roomHumanReadable
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Player
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Room

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomGameComposable(
    modifier: Modifier = Modifier,
    room: Room,
    rollDiceValues: List<DiceToDoor>?,
    players: List<Player>,
    onRollDice: () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "${stringResource(R.string.room)} ${room.coordinates.roomHumanReadable}",
                        textAlign = TextAlign.Center,
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding(),
                start = innerPadding.calculateStartPadding(
                    LocalLayoutDirection.current
                ) + 15.dp,
                end = innerPadding.calculateEndPadding(
                    LocalLayoutDirection.current
                ) + 15.dp,
            ),
        ) {
            item {
                rollDiceValues?.let { rolls ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        rolls.forEach { roll ->
                            Box(
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.primaryContainer,
                                    )
                                    .size(
                                        80.dp,
                                    )
                                    .padding(end = 5.dp),
                            ) {
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = roll.diceHumanReadable,
                                )
                            }
                        }
                    }
                } ?: ElevatedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onRollDice,
                ) {
                    Text(
                        text = stringResource(R.string.roll_dice),
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            items(players) { player ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = player.name,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    rollDiceValues?.let { rolls ->
                        rolls.forEach { roll ->
                            RoomNumberComposable(
                                roomCoordinate = roll.second,
                                size = 70.dp,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}