package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.glassbridgegame.app.navigation.BridgeGame
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.BridgeGameState
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.GlassState
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.PlayerState
import com.pradyotprakash.glassbridgegame.utils.NUMBER_OF_GLASSES
import com.pradyotprakash.glassbridgegame.utils.NUMBER_OF_PLAYERS
import kotlinx.coroutines.Dispatchers
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
        val glasses = getGlasses()

        val players = getOfflinePlayers()

        _bridgeGameState.update {
            it.copy(
                glasses = glasses,
                players = players,
                gameTimeValue = 3 * 60,
            )
        }

        startTimer()
    }

    private fun getGlasses(): List<GlassState> {
        val glasses = mutableListOf<GlassState>()
        for (i in 0..<NUMBER_OF_GLASSES step 2) {
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
        return glasses
    }

    private fun getOfflinePlayers(): List<PlayerState> {
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

        return players
    }

    private fun startTimer() {
        viewModelScope.launch(Dispatchers.Default) {
            delay(5000)
            takeTheStep(null)
            while (_bridgeGameState.value.gameTimeValue > 0 && !_bridgeGameState.value.isGameFinished) {
                _bridgeGameState.update {
                    val remainingTime = it.gameTimeValue - 1
                    val isGameFinished = remainingTime == 0 || hasGameCompleted()

                    it.copy(
                        gameTimeValue = remainingTime,
                        isGameStarted = remainingTime != 0 && !isGameFinished,
                        isGameFinished = isGameFinished,
                    )
                }
                delay(1000)
            }
        }
    }

    private fun takeTheStep(glassNumber: Int?) {
        val players = _bridgeGameState.value.players
        val currentPlayerIndex = _bridgeGameState.value.currentPlayer

        if (currentPlayerIndex < NUMBER_OF_PLAYERS) {
            val currentPlayer = players[currentPlayerIndex]

            if (!currentPlayer.isThePlayer) {
                viewModelScope.launch {
                    delay(800)
                    botPlay()
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
    }

    private fun humanPlay(selectedGlass: Int, currentPlayerIndex: Int) {

    }

    private fun botPlay() {
        val botNumber = _bridgeGameState.value.currentPlayer
        val botDetails = _bridgeGameState.value.players.first { it.playerNumber == botNumber }
        val oldGlassNumberForBot = botDetails.glassNumber
        val newGlassNumberForBot = getNextGlassNumber(oldGlassNumberForBot)

        if (newGlassNumberForBot < NUMBER_OF_GLASSES) {
            jumpToNextGlass(
                botNumber = botNumber,
                botDetails = botDetails,
                oldGlassNumberForBot = oldGlassNumberForBot,
                newGlassNumberForBot = newGlassNumberForBot,
            )
        } else {
            checkForBotStatus(
                botNumber = botNumber,
                oldGlassNumberForBot = oldGlassNumberForBot,
            )
        }

        if (!_bridgeGameState.value.isGameFinished) {
            takeTheStep(null)
        }
    }

    private fun checkForBotStatus(
        botNumber: Int,
        oldGlassNumberForBot: Int,
    ) {

        val hasAllPlayerPlayed = hasGameCompleted()
        val currentPlayedPlayer = _bridgeGameState.value.currentPlayer

        _bridgeGameState.update { state ->
            state.copy(
                glasses = state.glasses.map { glass ->
                    when (glass.number) {
                        oldGlassNumberForBot -> {
                            glass.copy(
                                playerNumber = -1,
                            )
                        }

                        else -> {
                            glass
                        }
                    }
                },
                players = state.players.map { player ->
                    if (player.playerNumber == botNumber) {
                        player.copy(
                            glassNumber = NUMBER_OF_GLASSES,
                        )
                    } else {
                        player
                    }
                },
                isGameFinished = hasAllPlayerPlayed,
                isGameStarted = !hasAllPlayerPlayed,
                currentPlayer = if (hasAllPlayerPlayed) -1 else currentPlayedPlayer + 1,
                gameTimeValue = if (hasAllPlayerPlayed) 0 else state.gameTimeValue,
            )
        }
    }

    private fun jumpToNextGlass(
        botNumber: Int,
        botDetails: PlayerState,
        oldGlassNumberForBot: Int,
        newGlassNumberForBot: Int,
    ) {
        val isPlayerDead =
            _bridgeGameState.value.glasses.first { it.number == newGlassNumberForBot }.isBreakable

        _bridgeGameState.update { state ->
            val updatedState = state.copy(
                players = state.players.map { player ->
                    if (player.playerNumber == botNumber) {
                        player.copy(
                            glassNumber = newGlassNumberForBot,
                            isDead = isPlayerDead,
                        )
                    } else {
                        player
                    }
                },
                glasses = state.glasses.map { glass ->
                    when (glass.number) {
                        newGlassNumberForBot -> {
                            glass.copy(
                                playerNumber = botDetails.playerNumber,
                                isBroken = isPlayerDead,
                            )
                        }

                        oldGlassNumberForBot -> {
                            glass.copy(
                                playerNumber = -1,
                            )
                        }

                        else -> {
                            glass
                        }
                    }
                },
            )

            val currentPlayerIndex = getNextPlayerNumber(updatedState, isPlayerDead)
            val hasAllPlayerPlayed = hasGameCompleted(updatedState)

            updatedState.copy(
                currentPlayer = currentPlayerIndex,
                isGameFinished = hasAllPlayerPlayed,
            )
        }
    }

    private fun hasGameCompleted(bridgeState: BridgeGameState? = null) =
        (bridgeState ?: _bridgeGameState.value).players.none {
            (it.showInTheArena || !it.showInTheWinnerArena) && !it.isDead
        }


    private fun getNextGlassNumber(oldGlassNumber: Int): Int {
        val randomGlassNumber = if (oldGlassNumber % 2 == 0) {
            Random.nextInt(oldGlassNumber + 2, oldGlassNumber + 4)
        } else {
            Random.nextInt(oldGlassNumber + 1, oldGlassNumber + 3)
        }

        if (randomGlassNumber % 2 == 0) {
            val isFirstGlassBroken =
                _bridgeGameState.value.glasses.firstOrNull { it.number == randomGlassNumber }?.isBroken == true

            return if (isFirstGlassBroken) {
                randomGlassNumber + 1
            } else {
                randomGlassNumber
            }
        } else {
            val isSecondGlassBroken =
                _bridgeGameState.value.glasses.firstOrNull { it.number == randomGlassNumber }?.isBroken == true

            return if (isSecondGlassBroken) {
                randomGlassNumber - 1
            } else {
                randomGlassNumber
            }
        }
    }

    private fun getNextPlayerNumber(updatedState: BridgeGameState, isPlayerDead: Boolean): Int {
        val nextPlayerNumber = updatedState.currentPlayer
        updatedState.players.filter { it.showInTheArena }.forEach { player ->
            val firstGlass = updatedState.glasses.first { it.number == 0 }
            val secondGlass = updatedState.glasses.first { it.number == 1 }

            if (firstGlass.playerNumber == -1 && secondGlass.playerNumber == -1) {
                return player.playerNumber
            } else if (firstGlass.isBroken && secondGlass.playerNumber == -1) {
                return player.playerNumber
            } else if (secondGlass.isBroken && firstGlass.playerNumber == -1) {
                return player.playerNumber
            }
        }

        updatedState.players.filter { !it.isDead && !it.showInTheArena && !it.showInTheWinnerArena }
            .sortedByDescending { it.playerNumber }.forEach { player ->
                val currentGlass = updatedState.glasses.first { it.number == player.glassNumber }

                val nextFirstGlass = if (currentGlass.number % 2 == 0) {
                    updatedState.glasses.firstOrNull { it.number == currentGlass.number + 2 }
                } else {
                    updatedState.glasses.firstOrNull { it.number == currentGlass.number + 1 }
                }

                if (nextFirstGlass == null) {
                    return player.playerNumber
                }

                val nextSecondGlass =
                    updatedState.glasses.first { it.number == nextFirstGlass.number + 1 }

                if (nextFirstGlass.playerNumber == -1 && nextSecondGlass.playerNumber == -1) {
                    return player.playerNumber
                } else if (nextFirstGlass.isBroken && nextSecondGlass.playerNumber == -1) {
                    return player.playerNumber
                } else if (nextSecondGlass.isBroken && nextFirstGlass.playerNumber == -1) {
                    return player.playerNumber
                }
            }

        updatedState.players.filter { !it.isDead && !it.showInTheArena && !it.showInTheWinnerArena }
            .sortedBy { it.playerNumber }.forEach { player ->
                return player.playerNumber
            }

        return if (isPlayerDead) nextPlayerNumber + 1 else nextPlayerNumber
    }

    fun onBridgeGlassTap(glassNumber: Int) {
        val isGameStarted = _bridgeGameState.value.isGameStarted
        if (isGameStarted) {
            takeTheStep(glassNumber)
        }
    }
}