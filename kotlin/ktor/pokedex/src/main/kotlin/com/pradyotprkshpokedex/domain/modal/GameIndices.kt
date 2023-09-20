package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameIndices(

    @SerialName("game_index") val gameIndex: Int? = null,
    @SerialName("generation") val generation: NameUrl? = NameUrl()

)