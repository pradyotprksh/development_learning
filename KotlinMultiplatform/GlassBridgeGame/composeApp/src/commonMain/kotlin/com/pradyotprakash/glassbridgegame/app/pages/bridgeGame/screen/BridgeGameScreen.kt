package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.screen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pradyotprakash.glassbridgegame.app.navigation.BridgeGame
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.BridgeGameViewModel
import com.pradyotprakash.glassbridgegame.utils.NUMBER_OF_TILES

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
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth().padding(10.dp),
            )
            HorizontalDivider(
                thickness = 5.dp, modifier = Modifier.padding(0.dp)
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(1f).padding(10.dp),
                reverseLayout = true,
            ) {
                item {
                    for (g in bridgeGameState.glasses.indices step 2) {
                        val firstGlass = bridgeGameState.glasses[g]
                        val secondGlass = bridgeGameState.glasses[g + 1]

                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val firstGlassBreakEffect by animateFloatAsState(
                                targetValue = if (firstGlass.isBroken) 0.5f else 1f,
                                animationSpec = tween(durationMillis = 300, easing = LinearEasing)
                            )
                            val secondGlassBreakEffect by animateFloatAsState(
                                targetValue = if (secondGlass.isBroken) 0.5f else 1f,
                                animationSpec = tween(durationMillis = 300, easing = LinearEasing)
                            )
                            if (g < NUMBER_OF_TILES - 2) {
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Box(
                                    modifier = Modifier.clip(RoundedCornerShape(16.dp))
                                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f * firstGlassBreakEffect))
                                        .blur(10.dp).border(
                                            width = 1.dp,
                                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f * firstGlassBreakEffect),
                                            shape = RoundedCornerShape(10.dp)
                                        ).graphicsLayer(
                                            scaleX = firstGlassBreakEffect,
                                            scaleY = firstGlassBreakEffect,
                                        ).padding(16.dp).height(100.dp).weight(1f)
                                ) { }
                                Spacer(modifier = Modifier.width(10.dp))
                                Box(
                                    modifier = Modifier.clip(RoundedCornerShape(16.dp))
                                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f * secondGlassBreakEffect))
                                        .blur(10.dp).border(
                                            width = 1.dp,
                                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f * secondGlassBreakEffect),
                                            shape = RoundedCornerShape(10.dp)
                                        ).graphicsLayer(
                                            scaleX = secondGlassBreakEffect,
                                            scaleY = secondGlassBreakEffect,
                                        ).padding(16.dp).height(100.dp).weight(1f)
                                ) { }
                            }
                        }
                    }
                }
            }
            if (bridgeGameState.isGameStarted) {
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
                        bridgeGameState.players.subList(0, 7).map {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Icon(
                                    Icons.Filled.Person,
                                    contentDescription = Icons.Filled.Person.name,
                                    tint = if (it.isThePlayer) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    it.name,
                                    style = MaterialTheme.typography.labelSmall,
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        bridgeGameState.players.subList(8, 15).map {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Icon(
                                    Icons.Filled.Person,
                                    contentDescription = Icons.Filled.Person.name,
                                    tint = if (it.isThePlayer) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    it.name,
                                    style = MaterialTheme.typography.labelSmall,
                                )
                            }
                        }
                    }
                }
                HorizontalDivider(
                    thickness = 5.dp,
                )
            }
        }
    }
}