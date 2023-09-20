package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationIV(

    @SerialName("diamond-pearl") val diamondPearl: DiamondPearl? = DiamondPearl(),
    @SerialName("heartgold-soulsilver") val heartgoldSoulsilver: HeartgoldSoulsilver? = HeartgoldSoulsilver(),
    @SerialName("platinum") val platinum: Platinum? = Platinum()

)