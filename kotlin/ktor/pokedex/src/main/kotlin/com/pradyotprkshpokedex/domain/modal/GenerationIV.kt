package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationIV(

    @SerialName("diamond-pearl") var diamondPearl: DiamondPearl? = DiamondPearl(),
    @SerialName("heartgold-soulsilver") var heartgoldSoulsilver: HeartgoldSoulsilver? = HeartgoldSoulsilver(),
    @SerialName("platinum") var platinum: Platinum? = Platinum()

)