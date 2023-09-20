package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationVII(

    @SerialName("icons") val icons: Icons? = Icons(),
    @SerialName("ultra-sun-ultra-moon") val ultraSunUltraMoon: UltraSunUltraMoon? = UltraSunUltraMoon()

)