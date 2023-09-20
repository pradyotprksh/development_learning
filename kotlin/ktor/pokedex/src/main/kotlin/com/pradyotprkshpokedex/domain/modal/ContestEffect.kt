package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContestEffect(

    @SerialName("id") val id: Int? = null,
    @SerialName("appeal") val appeal: Int? = null,
    @SerialName("jam") val jam: Int? = null,
    @SerialName("effect_entries") val effectEntries: List<EffectEntries> = emptyList(),
    @SerialName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntries> = emptyList(),
    @SerialName("url") val url: String? = null

)

