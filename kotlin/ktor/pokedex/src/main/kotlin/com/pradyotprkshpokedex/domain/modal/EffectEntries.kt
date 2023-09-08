package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EffectEntries(

    @SerialName("effect") var effect: String? = null,
    @SerialName("language") var language: Language? = Language()

)