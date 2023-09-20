package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationIII(

    @SerialName("emerald") val emerald: Emerald? = Emerald(),
    @SerialName("firered-leafgreen") val fireredLeafgreen: FireredLeafgreen? = FireredLeafgreen(),
    @SerialName("ruby-sapphire") val rubySapphire: RubySapphire? = RubySapphire()

)