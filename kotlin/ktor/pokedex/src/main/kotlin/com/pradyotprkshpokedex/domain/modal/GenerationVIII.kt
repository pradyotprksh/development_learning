package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationVIII(

    @SerialName("icons") var icons: Icons? = Icons()

)