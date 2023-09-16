package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameIndices(

    @SerialName("game_index") var gameIndex: Int? = null,
    @SerialName("generation") var generation: Generation? = Generation()

)