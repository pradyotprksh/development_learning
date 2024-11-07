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
                    glassNumber = i,
                    isBreakable = isBreakable,
                    isBroken = false,
                    playerNumber = -1,
                )
            )

            glasses.add(
                GlassState(
                    glassNumber = i + 1,
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
        val thePlayerNumber = Random.nextInt(1, NUMBER_OF_PLAYERS)
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
            takeTheStep()
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

    private fun takeTheStep(glassNumber: Int? = null) {
        val currentPlayerIndex = _bridgeGameState.value.currentPlayer

        if (currentPlayerIndex < NUMBER_OF_PLAYERS) {
            val currentPlayer = _bridgeGameState.value.currentPlayerDetails

            if (currentPlayer.isThePlayer) {
                glassNumber?.let {
                    humanPlay(
                        selectedGlass = glassNumber,
                        currentPlayerIndex = currentPlayerIndex,
                    )
                } ?: checkIfThePlayerWon()
            } else {
                viewModelScope.launch {
                    delay(800)
                    botPlay()
                }
            }
        }
    }

    private fun checkIfThePlayerWon() {
        val currentPlayerIndex = _bridgeGameState.value.currentPlayer
        val playerDetails = _bridgeGameState.value.players.firstOrNull {
            it.playerNumber == currentPlayerIndex && it.isThePlayer
        }
        if (playerDetails != null) {
            val currentGlassNumber = playerDetails.glassNumber
            val currentGlassDetails =
                _bridgeGameState.value.glasses.firstOrNull { it.glassNumber == currentGlassNumber }

            if (currentGlassDetails != null) {
                if (currentGlassDetails.glassNumber in NUMBER_OF_GLASSES - 2..<NUMBER_OF_GLASSES) {
                    _bridgeGameState.update { state ->
                        val updatedState = state.copy(
                            players = state.players.map { player ->
                                if (player.playerNumber == currentPlayerIndex && !player.isDead) {
                                    player.copy(
                                        glassNumber = NUMBER_OF_GLASSES,
                                    )
                                } else {
                                    player
                                }
                            },
                            glasses = state.glasses.map { glass ->
                                when (glass.glassNumber) {
                                    currentGlassNumber -> {
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

                        updatedState.copy(
                            currentPlayer = getNextPlayerNumber(
                                updatedState, false,
                            ),
                            isGameStarted = !hasGameCompleted(updatedState),
                            isGameFinished = hasGameCompleted(updatedState),
                        )
                    }
                    takeTheStep()
                }
            }
        }
    }

    private fun humanPlay(selectedGlass: Int, currentPlayerIndex: Int) {
        val playerDetails = _bridgeGameState.value.humanPlayerDetailsByIndex(currentPlayerIndex)
        val currentGlassNumber = playerDetails.glassNumber
        val currentGlassDetails =
            _bridgeGameState.value.glasses.firstOrNull { it.glassNumber == currentGlassNumber }
        val selectedGlassDetails = _bridgeGameState.value.glassDetailsByIndex(selectedGlass)

        if (canJumpToTheSelectedGlass(
                playerDetails = playerDetails,
                currentGlassDetails = currentGlassDetails,
                selectedGlassDetails = selectedGlassDetails
            )
        ) {
            _bridgeGameState.update { state ->
                val updatedState = state.copy(
                    players = state.players.map { player ->
                        if (player.playerNumber == currentPlayerIndex) {
                            player.copy(
                                glassNumber = selectedGlass,
                                isDead = selectedGlassDetails.isBreakable,
                            )
                        } else {
                            player
                        }
                    },
                    glasses = state.glasses.map { glass ->
                        when (glass.glassNumber) {
                            selectedGlass -> {
                                glass.copy(
                                    playerNumber = currentPlayerIndex,
                                    isBroken = selectedGlassDetails.isBreakable,
                                )
                            }

                            currentGlassNumber -> {
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

                updatedState.copy(
                    currentPlayer = getNextPlayerNumber(
                        updatedState, selectedGlassDetails.isBreakable
                    ),
                    isGameStarted = !hasGameCompleted(updatedState),
                    isGameFinished = hasGameCompleted(updatedState),
                )
            }
        }

        takeTheStep()
    }

    private fun canJumpToTheSelectedGlass(
        playerDetails: PlayerState,
        currentGlassDetails: GlassState?,
        selectedGlassDetails: GlassState,
    ): Boolean {
        if (!playerDetails.isThePlayer) {
            return false
        }

        if (currentGlassDetails == null) {
            return selectedGlassDetails.glassNumber in 0..1
        }

        if (selectedGlassDetails.glassNumber <= currentGlassDetails.glassNumber) {
            return false
        }

        if (currentGlassDetails.glassNumber % 2 == 0) {
            if (selectedGlassDetails.glassNumber !in currentGlassDetails.glassNumber + 2..currentGlassDetails.glassNumber + 3) {
                return false
            }
        } else {
            if (selectedGlassDetails.glassNumber !in currentGlassDetails.glassNumber + 1..currentGlassDetails.glassNumber + 2) {
                return false
            }
        }

        if (selectedGlassDetails.playerNumber != -1) {
            return false
        }

        return true
    }

    private fun botPlay() {
        val botNumber = _bridgeGameState.value.currentPlayer
        val botDetails = _bridgeGameState.value.playerDetailsByIndex(botNumber)
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
            takeTheStep()
        }
    }

    private fun checkForBotStatus(
        botNumber: Int,
        oldGlassNumberForBot: Int,
    ) {
        val hasAllPlayerPlayed = hasGameCompleted()

        _bridgeGameState.update { state ->
            val updatedState = state.copy(
                glasses = state.glasses.map { glass ->
                    when (glass.glassNumber) {
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
                    if (player.playerNumber == botNumber && !player.isDead) {
                        player.copy(
                            glassNumber = NUMBER_OF_GLASSES,
                        )
                    } else {
                        player
                    }
                },
                isGameFinished = hasAllPlayerPlayed,
                isGameStarted = !hasAllPlayerPlayed,
                gameTimeValue = if (hasAllPlayerPlayed) 0 else state.gameTimeValue,
            )

            updatedState.copy(
                currentPlayer = if (hasAllPlayerPlayed) -1 else getNextPlayerNumber(
                    updatedState, false
                )
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
            _bridgeGameState.value.glassDetailsByIndex(newGlassNumberForBot).isBreakable

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
                    when (glass.glassNumber) {
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
                _bridgeGameState.value.glasses.firstOrNull { it.glassNumber == randomGlassNumber }?.isBroken == true

            return if (isFirstGlassBroken) {
                randomGlassNumber + 1
            } else {
                randomGlassNumber
            }
        } else {
            val isSecondGlassBroken =
                _bridgeGameState.value.glasses.firstOrNull { it.glassNumber == randomGlassNumber }?.isBroken == true

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
            val firstGlass = updatedState.glassDetailsByIndex(0)
            val secondGlass = updatedState.glassDetailsByIndex(1)

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
                val currentGlass =
                    updatedState.glassDetailsByIndex(player.glassNumber)

                val nextFirstGlass = if (currentGlass.glassNumber % 2 == 0) {
                    updatedState.glasses.firstOrNull { it.glassNumber == currentGlass.glassNumber + 2 }
                } else {
                    updatedState.glasses.firstOrNull { it.glassNumber == currentGlass.glassNumber + 1 }
                }

                if (nextFirstGlass == null) {
                    return player.playerNumber
                }

                val nextSecondGlass =
                    updatedState.glassDetailsByIndex(nextPlayerNumber + 1)

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
        val currentPlayerIndex = _bridgeGameState.value.currentPlayer
        val isThePlayerTurn =
            _bridgeGameState.value.players.firstOrNull { it.playerNumber == currentPlayerIndex }?.isThePlayer == true
        if (isGameStarted && isThePlayerTurn) {
            takeTheStep(glassNumber)
        }
    }
}