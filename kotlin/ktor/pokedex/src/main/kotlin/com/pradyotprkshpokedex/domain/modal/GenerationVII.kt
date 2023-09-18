package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationVII(

    @SerialName("icons") var icons: Icons? = Icons(),
    @SerialName("ultra-sun-ultra-moon") var ultraSunUltraMoon: UltraSunUltraMoon? = UltraSunUltraMoon()

)