package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradyotprakash.glassbridgegame.app.navigation.BridgeGame
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.components.canJumpToTheSelectedGlass
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.components.getGlasses
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.components.getNextGlassNumber
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.components.getNextPlayerNumber
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.components.getOfflinePlayers
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.components.hasGameCompleted
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.BridgeGameState
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.GlassState
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.PlayerState
import com.pradyotprakash.glassbridgegame.utils.NUMBER_OF_GLASSES
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
        if (bridgeGameDetails.isOffline && !bridgeGameDetails.isOfflineInfinite) {
            createOfflineGame()
        } else if (bridgeGameDetails.isOffline && bridgeGameDetails.isOfflineInfinite) {
            createOfflineInfiniteGame()
        }
    }

    private fun createOfflineInfiniteGame() {
        val glasses = getInfiniteGlasses(isStart = true)

        val player = listOf(
            PlayerState(
                isBot = false,
                glassNumber = -1,
                isDead = false,
                name = "Pradyot",
                isThePlayer = true,
                playerNumber = 0,
            )
        )

        _bridgeGameState.update {
            it.copy(
                glasses = glasses,
                players = player,
                gameTimeValue = 0,
                showGameScore = true,
                isGameStarted = true,
                isInfiniteGame = true,
                isGameFinished = false,
                glassPageNumber = 2,
                gameScore = 0,
                currentPlayer = 0,
            )
        }

        takeTheStep()
    }

    private fun getInfiniteGlasses(isStart: Boolean): List<GlassState> {
        val currentPage = if (isStart) 1 else _bridgeGameState.value.glassPageNumber
        val startIndex =
            if (isStart) 0 else _bridgeGameState.value.glasses.maxByOrNull { it.glassNumber }?.glassNumber?.run { this + 1 }
                ?: 0

        val glasses = mutableListOf<GlassState>()

        for (i in startIndex..<NUMBER_OF_GLASSES * currentPage step 2) {
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

    private fun createOfflineGame() {
        val glasses = getGlasses()

        val players = getOfflinePlayers()

        _bridgeGameState.update {
            it.copy(
                glasses = glasses,
                players = players,
                gameTimeValue = 3 * 60,
                showGameTime = true,
                isGameFinished = false,
                currentPlayer = 0,
            )
        }

        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch(Dispatchers.Default) {
            delay(5000)
            takeTheStep()
            while (_bridgeGameState.value.gameTimeValue > 0 && !_bridgeGameState.value.isGameFinished) {
                _bridgeGameState.update {
                    val remainingTime = it.gameTimeValue - 1
                    val isGameFinished = remainingTime == 0 || hasGameCompleted(
                        newBridgeState = null,
                        currentState = _bridgeGameState.value,
                    )

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

        if (currentPlayerIndex < getPlayersNumbers()) {
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

            addNewGlassesForInfinite()
            calculateScore()
        }
    }

    private fun calculateScore() {
        if (_bridgeGameState.value.isInfiniteGame && !_bridgeGameState.value.isGameFinished) {
            val score =
                if (_bridgeGameState.value.isInfiniteGame && _bridgeGameState.value.currentPlayerDetails.glassNumber != -1) {
                    val currentPlayerGlass = _bridgeGameState.value.currentPlayerDetails.glassNumber
                    (currentPlayerGlass / 2) + 1
                } else {
                    0
                }

            _bridgeGameState.update {
                it.copy(
                    gameScore = score,
                )
            }
        }
    }

    private fun addNewGlassesForInfinite() {
        if (_bridgeGameState.value.isInfiniteGame && _bridgeGameState.value.isGameStarted && !_bridgeGameState.value.isGameFinished) {
            if (getGlassesNumbers() / 2 <= _bridgeGameState.value.currentPlayerDetails.glassNumber) {
                val newGlasses = getInfiniteGlasses(isStart = false)

                _bridgeGameState.update {
                    it.copy(
                        glasses = it.glasses + newGlasses,
                        glassPageNumber = it.glassPageNumber + 1,
                    )
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
                if (currentGlassDetails.glassNumber in getGlassesNumbers() - 2..<getGlassesNumbers()) {
                    _bridgeGameState.update { state ->
                        val updatedState = state.copy(
                            players = state.players.map { player ->
                                if (player.playerNumber == currentPlayerIndex && !player.isDead) {
                                    player.copy(
                                        glassNumber = getGlassesNumbers(),
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

                        val isGameFinished = hasGameCompleted(
                            newBridgeState = updatedState,
                            currentState = _bridgeGameState.value,
                        )

                        updatedState.copy(
                            currentPlayer = getNextPlayerNumber(
                                updatedState, false,
                            ),
                            isGameStarted = !isGameFinished,
                            isGameFinished = isGameFinished,
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
                val isGameFinished = hasGameCompleted(
                    newBridgeState = updatedState,
                    currentState = _bridgeGameState.value,
                )

                updatedState.copy(
                    currentPlayer = getNextPlayerNumber(
                        updatedState, selectedGlassDetails.isBreakable
                    ),
                    isGameStarted = !isGameFinished,
                    isGameFinished = isGameFinished,
                )
            }
        }

        takeTheStep()
    }

    private fun botPlay() {
        val botNumber = _bridgeGameState.value.currentPlayer
        val botDetails = _bridgeGameState.value.playerDetailsByIndex(botNumber)
        val oldGlassNumberForBot = botDetails.glassNumber
        val newGlassNumberForBot = getNextGlassNumber(
            oldGlassNumber = oldGlassNumberForBot,
            glasses = _bridgeGameState.value.glasses,
        )

        if (newGlassNumberForBot < getGlassesNumbers()) {
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
        val hasAllPlayerPlayed = hasGameCompleted(
            newBridgeState = null,
            currentState = _bridgeGameState.value,
        )

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
                            glassNumber = getGlassesNumbers(),
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
            val hasAllPlayerPlayed = hasGameCompleted(
                newBridgeState = updatedState,
                currentState = _bridgeGameState.value,
            )

            updatedState.copy(
                currentPlayer = currentPlayerIndex,
                isGameFinished = hasAllPlayerPlayed,
            )
        }
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

    private fun getPlayersNumbers() = _bridgeGameState.value.players.count()

    private fun getGlassesNumbers() = _bridgeGameState.value.glasses.count()
}