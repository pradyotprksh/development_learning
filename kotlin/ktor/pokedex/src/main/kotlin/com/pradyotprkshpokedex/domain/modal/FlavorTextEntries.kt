package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlavorTextEntries(

    @SerialName("flavor_text") var flavorText: String? = null,
    @SerialName("language") var language: Language? = Language()

)