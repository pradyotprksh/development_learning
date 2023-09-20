package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PastTypes(

    @SerialName("generation") val generation: NameUrl? = NameUrl(),
    @SerialName("types") val types: List<Types> = emptyList()

)