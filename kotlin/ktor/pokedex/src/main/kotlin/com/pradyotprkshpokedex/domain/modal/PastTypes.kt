package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PastTypes(

    @SerialName("generation") var generation: Generation? = Generation(),
    @SerialName("types") var types: List<Types> = emptyList()

)