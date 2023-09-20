package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationVI(

    @SerialName("omegaruby-alphasapphire") val omegarubyAlphasapphire: OmegarubyAlphasapphire? = OmegarubyAlphasapphire(),
    @SerialName("x-y") val xy: XY? = XY()

)