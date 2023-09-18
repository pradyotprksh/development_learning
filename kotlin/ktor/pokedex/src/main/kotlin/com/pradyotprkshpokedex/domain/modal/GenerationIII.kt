package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationIII(

    @SerialName("emerald") var emerald: Emerald? = Emerald(),
    @SerialName("firered-leafgreen") var fireredLeafgreen: FireredLeafgreen? = FireredLeafgreen(),
    @SerialName("ruby-sapphire") var rubySapphire: RubySapphire? = RubySapphire()

)