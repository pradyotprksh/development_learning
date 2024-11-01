package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.glassbridgegame.app.navigation.BridgeGame
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.BridgeGameState
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.GlassState
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.PlayerState
import com.pradyotprakash.glassbridgegame.utils.NUMBER_OF_PLAYERS
import com.pradyotprakash.glassbridgegame.utils.NUMBER_OF_TILES
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class BridgeGameViewModel : ViewModel() {
    private val _bridgeGameState = MutableStateFlow(BridgeGameState())
    val bridgeGameState = _bridgeGameState.asStateFlow()

    fun initGame(bridgeGameDetails: BridgeGame) {
        if (bridgeGameDetails.isOffline) {
            createOfflineGame()
        }
    }

    private fun createOfflineGame() {
        val glasses = mutableListOf<GlassState>()
        for (i in 0..<NUMBER_OF_TILES step 2) {
            val isBreakable = Random.nextBoolean()

            glasses.add(
                GlassState(
                    number = i,
                    isBreakable = isBreakable,
                    isBroken = false,
                    playerNumber = -1,
                )
            )

            glasses.add(
                GlassState(
                    number = i + 1,
                    isBreakable = !isBreakable,
                    isBroken = false,
                    playerNumber = -1,
                )
            )
        }

        val players = mutableListOf<PlayerState>()
        val thePlayerNumber = Random.nextInt(0, NUMBER_OF_PLAYERS)
        players.add(
            PlayerState(
                isBot = false,
                glassNumber = -1,
                isDead = false,
                name = "Pradyot",
                isThePlayer = true,
                playerNumber = thePlayerNumber,
            )
        )
        for (i in 0..<NUMBER_OF_PLAYERS) {
            if (i == thePlayerNumber) {
                continue
            }

            players.add(
                PlayerState(
                    isBot = true,
                    glassNumber = -1,
                    isDead = false,
                    name = "Bot $i",
                    isThePlayer = false,
                    playerNumber = i,
                )
            )
        }
        players.sortBy { it.playerNumber }

        _bridgeGameState.update {
            it.copy(
                glasses = glasses,
                players = players,
                gameTimeValue = 3 * 60,
            )
        }

        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            delay(5000)
            takeTheStep(null)
            while (_bridgeGameState.value.gameTimeValue > 0) {
                _bridgeGameState.update {
                    val remainingTime = it.gameTimeValue - 1

                    it.copy(
                        gameTimeValue = remainingTime,
                        isGameStarted = remainingTime != 0,
                        isGameFinished = remainingTime == 0,
                    )
                }
                delay(1000)
            }
        }
    }

    private fun takeTheStep(glassNumber: Int?) {
        val players = _bridgeGameState.value.players
        val currentPlayerIndex = _bridgeGameState.value.currentPlayer
        val currentPlayer = players[currentPlayerIndex]

        if (!currentPlayer.isThePlayer) {
            viewModelScope.launch {
                delay(800)
                botPlay(
                    currentPlayerIndex = currentPlayerIndex,
                    currentPlayer = currentPlayer,
                )
                takeTheStep(null)
            }
        } else {
            glassNumber?.let {
                humanPlay(
                    selectedGlass = it,
                    currentPlayerIndex = currentPlayerIndex,
                )
            }
        }
    }

    private fun humanPlay(selectedGlass: Int, currentPlayerIndex: Int) {
        takeTheStep(null)
    }

    private fun botPlay(currentPlayerIndex: Int, currentPlayer: PlayerState) {

    }

    fun onBridgeGlassTap(glassNumber: Int) {
        val isGameStarted = _bridgeGameState.value.isGameStarted
        if (isGameStarted) {
            takeTheStep(glassNumber)
        }
    }
}