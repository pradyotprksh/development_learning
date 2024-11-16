package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.components

import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.GlassState
import kotlin.random.Random

fun getNextGlassNumber(
    oldGlassNumber: Int,
    glasses: List<GlassState>,
): Int {
    val randomGlassNumber = if (oldGlassNumber % 2 == 0) {
        Random.nextInt(oldGlassNumber + 2, oldGlassNumber + 4)
    } else {
        Random.nextInt(oldGlassNumber + 1, oldGlassNumber + 3)
    }

    if (randomGlassNumber % 2 == 0) {
        val isFirstGlassBroken =
            glasses.firstOrNull { it.glassNumber == randomGlassNumber }?.isBroken == true

        return if (isFirstGlassBroken) {
            randomGlassNumber + 1
        } else {
            randomGlassNumber
        }
    } else {
        val isSecondGlassBroken =
            glasses.firstOrNull { it.glassNumber == randomGlassNumber }?.isBroken == true

        return if (isSecondGlassBroken) {
            randomGlassNumber - 1
        } else {
            randomGlassNumber
        }
    }
}