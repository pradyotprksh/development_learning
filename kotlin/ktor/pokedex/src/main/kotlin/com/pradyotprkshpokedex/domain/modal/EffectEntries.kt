package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EffectEntries(

    @SerialName("effect") val effect: String? = null,
    @SerialName("short_effect") val shortEffect: String? = null,
    @SerialName("language") val language: NameUrl? = NameUrl()

)