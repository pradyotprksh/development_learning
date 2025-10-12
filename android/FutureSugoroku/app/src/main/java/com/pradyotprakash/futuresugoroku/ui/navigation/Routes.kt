package com.pradyotprakash.futuresugoroku.ui.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object Home

    @Serializable
    data object Game
}