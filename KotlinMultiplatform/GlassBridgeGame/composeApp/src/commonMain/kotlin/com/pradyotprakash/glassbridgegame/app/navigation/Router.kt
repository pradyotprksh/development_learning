package com.pradyotprakash.glassbridgegame.app.navigation

import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object Home

@Serializable
data class BridgeGame(
    val isOffline: Boolean,
)