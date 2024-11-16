package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.components

import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.GlassState
import com.pradyotprakash.glassbridgegame.utils.NUMBER_OF_GLASSES
import kotlin.random.Random

fun getGlasses(): List<GlassState> {
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